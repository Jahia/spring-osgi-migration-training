package fr.sample.jahia.training.components.taglibs;

import org.apache.commons.lang3.StringUtils;
import org.jahia.services.render.RenderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class SetTACCookieVars extends TagSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetTACCookieVars.class);

    private static final long serialVersionUID = 2107428517031013537L;

    public enum TacServiceNames {
        ABTASTY("abtasty"),
        ADOBE_ANALYTICS("adobeAnalytics"),
        CONTENT_SQUARE("contentSquare"),
        GTM("googletagmanager");

        private final String name;

        TacServiceNames(String name) {
            this.name = name;
        }

        public String getTacServiceName() {
            return name;
        }
    }

    private static final String IS_TAC_CONSENT_OK = "isTACConsentGranted";
    public static final String NOM_COOKIE_TAC = "tarteaucitron";

    public static String getCookieValue(HttpServletRequest httpServletRequest) {
        Cookie tacCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (NOM_COOKIE_TAC.equals(cookie.getName())) {
                    tacCookie = cookie;
                }
            }
        }
        if (tacCookie != null) {
            return tacCookie.getValue();
        }
        return null;
    }

    @Override
    public int doStartTag() throws JspException {
        // Récupération du cookie TAC s'il existe
        String tacCookieVal = getCookieValue(((RenderContext) pageContext.getAttribute("renderContext", PageContext.REQUEST_SCOPE)).getRequest());
        if (StringUtils.isNotBlank(tacCookieVal)) {
            LOGGER.debug("Cookie {} existe avec valeur {}", NOM_COOKIE_TAC, tacCookieVal);
            // TAC place une valeur "wait" qu'on peut obtenir
            // si le navigateur internaute est rapide après un changement de place
            // => cela valide le consentement
            if (StringUtils.contains(tacCookieVal, "wait")) {
                // Cookie tarteaucitron à "wait"
                pageContext.setAttribute(IS_TAC_CONSENT_OK, false);
            } else {
                // Cookie tarteaucitron déjà présent
                pageContext.setAttribute(IS_TAC_CONSENT_OK, true);
                // On défini les services à inclure
                pageContext.setAttribute("isABTasty", getConsentValueForService(tacCookieVal, TacServiceNames.ABTASTY.getTacServiceName()));
                pageContext.setAttribute("isAdobe", getConsentValueForService(tacCookieVal, TacServiceNames.ADOBE_ANALYTICS.getTacServiceName()));
                pageContext.setAttribute("isContentSquare", getConsentValueForService(tacCookieVal, TacServiceNames.CONTENT_SQUARE.getTacServiceName()));
                pageContext.setAttribute("isGTM", getConsentValueForService(tacCookieVal, TacServiceNames.GTM.getTacServiceName()));
            }
        } else {
            pageContext.setAttribute(IS_TAC_CONSENT_OK, false);
        }
        return super.doStartTag();
    }

    private Boolean getConsentValueForService(String cookieValue, String service) {
        return "true".equals(StringUtils.substringBefore(StringUtils.substringAfter(cookieValue, service + "="), "!"));
    }
}
