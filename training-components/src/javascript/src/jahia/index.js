import {registry} from '@jahia/ui-extender';
import i18next from 'i18next';
import {registerAction, registerAdditional, registerItem, registerRoute, registerSelectorType} from './registrations';

registry.add('callback', 'training-components', {
    targets: ['jahiaApp-init:50'],
    callback: async () => {
        await i18next.loadNamespaces('training-components');
        registerRoute(registry);
        registerAction(registry);
        registerItem(registry);
        registerAdditional(registry);
        registerSelectorType(registry);
    }
});
