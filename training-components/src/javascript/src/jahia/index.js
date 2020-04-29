import {registry} from '@jahia/ui-extender';
import i18next from 'i18next';
import {registerRoute} from './routes';

registry.add('callback', 'training-components', {
    targets: ['jahiaApp-init:50'],
    callback: async () => {
        await i18next.loadNamespaces('training-components');
        registerRoute();
        console.debug('%c Training routes have been registered', 'color: #15b08a');
    }
});
