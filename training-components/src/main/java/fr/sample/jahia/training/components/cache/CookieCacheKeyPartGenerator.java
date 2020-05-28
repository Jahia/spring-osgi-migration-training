package fr.sample.jahia.training.components.cache;

import fr.sample.jahia.training.components.taglibs.SetTACCookieVars;
import org.apache.commons.lang.StringUtils;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.filter.cache.CacheKeyPartGenerator;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Component(service = CacheKeyPartGenerator.class, immediate = true)
public class CookieCacheKeyPartGenerator implements CacheKeyPartGenerator {
    @Override
    public String getKey() {
        return SetTACCookieVars.NOM_COOKIE_TAC;
    }

    @Override
    public String getValue(Resource resource, RenderContext renderContext, Properties properties) {
        return getKey();
    }

    @Override
    public String replacePlaceholders(RenderContext renderContext, String keyPart) {
        HttpServletRequest httpServletRequest = renderContext.getRequest();
        String jSessionId = httpServletRequest.getSession().getId();
        String cookie = SetTACCookieVars.getCookieValue(httpServletRequest);
        if (StringUtils.isBlank(cookie)) {
            return jSessionId;
        }
        return jSessionId + cookie;
    }
}
