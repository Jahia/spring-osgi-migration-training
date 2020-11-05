package fr.sample.jahia.training.components.taglibs;

/**
 * Short description of the class
 *
 * @author tleclere
 */

import org.apache.taglibs.standard.tag.common.core.ParamParent;
import org.jahia.api.settings.SettingsBean;
import org.jahia.osgi.BundleUtils;
import org.jahia.taglibs.AbstractJahiaTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class PropertyTagUtils extends AbstractJahiaTag implements ParamParent {
    private String key;
    private String var;
    protected Map<String, String> params;

    public void setKey(String key) {
        this.key = key;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public PropertyTagUtils() {
        this.params = new HashMap<>();
    }

    @Override
    public int doStartTag() {
        params.clear();
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public void addParameter(String name, String value) {
        params.put(name, value);
    }

    @Override
    public int doEndTag() throws JspException {
        String propertyValue = key;

        SettingsBean settingsBean = BundleUtils.getOsgiService(SettingsBean.class, null);
        if (settingsBean != null) {
            try {
                if (params.isEmpty()) {
                    propertyValue = settingsBean.getString(key, key);
                } else {
                    propertyValue = MessageFormat.format(settingsBean.getString(key, key), params.values().toArray(new String[0]));
                }
            } catch (NoSuchElementException e) {
                propertyValue = key;
            }
        }

        if (this.var != null) {
            this.pageContext.setAttribute(this.var, propertyValue);
        } else {
            try {
                this.pageContext.getOut().print(propertyValue);
            } catch (IOException e) {
                throw new JspTagException(e.toString(), e);
            }
        }

        return EVAL_PAGE;
    }
}
