package fr.sample.jahia.training.components.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.jahia.services.SpringContextSingleton;
import org.jahia.services.cache.ehcache.EhCacheProvider;
import org.jahia.services.usermanager.ldap.cache.LDAPCacheManager;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * Short description of the class
 *
 * @author tleclere
 */
@Component(service = LDAPCacheManager.class, immediate = true)
public class LDAPCacheManagerNotEternal extends LDAPCacheManager {
    private Cache userCache;

    @Activate
    @Override
    protected void start() {
        super.start();
        EhCacheProvider cacheProvider = (EhCacheProvider) SpringContextSingleton.getInstance().getContext().getBean("ehCacheProvider");
        final CacheManager cacheManager = cacheProvider.getCacheManager();
        userCache = cacheManager.getCache(LDAP_USER_CACHE);
        if (userCache != null) {
            userCache.getCacheConfiguration().setTimeToLiveSeconds(100);
        }
    }
}
