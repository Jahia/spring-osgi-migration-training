import {registry} from '@jahia/ui-extender';
import i18next from 'i18next';
import {registerAction, registerAdditional, registerItem, registerRoute} from './registrations';

registry.add('callback', 'training-components', {
    targets: ['jahiaApp-init:50'],
    callback: async () => {
        await i18next.loadNamespaces('training-components');
        registerRoute();
        registerAction();
        registerItem();
        registerAdditional();
    }
});
