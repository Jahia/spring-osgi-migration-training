package fr.sample.jahia.training.components.graphql;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLName;
import org.jahia.modules.graphql.provider.dxm.DataFetchingException;
import org.jahia.modules.graphql.provider.dxm.node.GqlJcrMutation;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRSessionWrapper;

import javax.jcr.RepositoryException;
import java.util.Locale;

/**
 * GraphQL root object for UGC mutations
 *
 * @author tleclere
 */
@GraphQLName("UGCMutation")
@GraphQLDescription("UGC Mutations")
public class GqlUgcMutation extends GqlJcrMutation {
    private Locale locale;

    public GqlUgcMutation(String workspace, Locale locale) {
        super(workspace);
        this.locale = locale;
    }

    @Override
    public JCRSessionWrapper getSession() {
        try {
            return JCRSessionFactory.getInstance().getCurrentSystemSession(getWorkspace(), locale, locale);
        } catch (RepositoryException e) {
            throw new DataFetchingException(e);
        }
    }
}
