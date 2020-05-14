package fr.sample.jahia.training.components.initializers;

import org.apache.commons.lang3.LocaleUtils;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;
import org.jahia.services.content.nodetypes.initializers.ChoiceListValue;
import org.jahia.services.content.nodetypes.initializers.ModuleChoiceListInitializer;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Custom choicelist initializer for edit mode
 * Display list of locales available in JVM
 * ModuleChoiceListInitializer is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = ModuleChoiceListInitializer.class, immediate = true)
public class LocaleChoiceListInitializer implements ModuleChoiceListInitializer {
    /**
     * Choicelist initializer key
     */
    private static final String INITIALIZER_KEY = "locale";
    private String key;

    /**
     * Do nothing: key is set with constant LocaleChoiceListInitializer.KEY
     *
     * @param key
     */
    @Override
    public void setKey(String key) {
        this.key = INITIALIZER_KEY;
    }

    /**
     * @return choicelist initializer key
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * @param extendedPropertyDefinition
     * @param param
     * @param values                     existing choicelist values or null
     * @param locale
     * @param context
     * @return list of locales available in JVM
     */
    @Override
    public List<ChoiceListValue> getChoiceListValues(ExtendedPropertyDefinition extendedPropertyDefinition, String param, List<ChoiceListValue> values, Locale locale, Map<String, Object> context) {
        return LocaleUtils.availableLocaleList().stream().map(availableLocale -> new ChoiceListValue(availableLocale.getDisplayName(locale), availableLocale.getLanguage())).collect(Collectors.toList());
    }
}
