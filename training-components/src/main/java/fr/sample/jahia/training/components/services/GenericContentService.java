package fr.sample.jahia.training.components.services;

import fr.sample.jahia.training.components.beans.GenericContent;
import org.apache.commons.lang.StringUtils;
import org.jahia.api.Constants;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.content.decorator.JCRUserNode;
import org.jahia.services.usermanager.JahiaUserManagerService;
import org.jahia.services.usermanager.ldap.cache.LDAPCacheManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.ItemNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Service to deal about generic content
 * GenericContentService is an OSGI component
 *
 * @author tleclere
 */
@Component(service = GenericContentService.class, immediate = true)
public class GenericContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericContentService.class);

    private LDAPCacheManager ldapCacheManager;

    @Reference(service = LDAPCacheManager.class)
    public void setLdapCacheManager(LDAPCacheManager ldapCacheManager) {
        this.ldapCacheManager = ldapCacheManager;
    }

    /**
     * Interceptor sample: update content description when the component is saved
     *
     * @param node
     * @param language
     * @return node updated
     * @throws RepositoryException
     */
    public JCRNodeWrapper updateDescription(JCRNodeWrapper node, String language) throws RepositoryException {
        node.setProperty(GenericContent.PROPERTY_CODE, String.format("Content in %s", language));
        return node;
    }

    private void populateGroups(Set<String> groups, JCRNodeWrapper principal) throws RepositoryException {
        PropertyIterator weakReferences = principal.getWeakReferences("j:member");
        while (weakReferences.hasNext()) {
            try {
                Property property = weakReferences.nextProperty();
                if (property != null && property.getPath() != null && property.getPath().contains("/j:members/")) {
                    JCRNodeWrapper group = (JCRNodeWrapper) property.getSession().getNode(StringUtils.substringBefore(property.getPath(), "/j:members/"));
                    if (group.isNodeType(Constants.JAHIANT_GROUP) && groups.add(group.getPath())) {
                        // recurse on the found group only we have not done it yet
                        populateGroups(groups, group);
                    }
                }
            } catch (ItemNotFoundException e) {
                LOGGER.warn("Cannot find group for {}", principal.getPath(), e);
            }
        }
    }

    public List<String> getMembers(String username) {
        Set<String> groups = new LinkedHashSet<>();
        try {
            JCRUserNode userNode;
            JCRSessionWrapper jcrSession = JCRSessionFactory.getInstance().getCurrentUserSession(Constants.LIVE_WORKSPACE);
            if (StringUtils.isNotEmpty(username)) {
                userNode = JahiaUserManagerService.getInstance().lookupUser(username, jcrSession);
            } else {
                userNode = jcrSession.getUserNode();
            }
            ldapCacheManager.clearUserCacheEntryByName(StringUtils.substringBefore(userNode.getProviderName(), ".users"), userNode.getName());
            populateGroups(groups, userNode);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return new LinkedList<>(groups);
    }
}
