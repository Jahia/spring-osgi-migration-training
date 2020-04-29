import React from 'react';
import {registry} from '@jahia/ui-extender';
import Main from '../components/Main';

export const registerRoute = () => {
    registry.add('adminRoute', 'settings/training-components', {
        targets: ['administration-sites:100'],
        label: 'training-components:menu.label',
        isSelectable: true,
        render: () => {
            console.log('%c Training component', 'color: #15b08a');
            return <Main/>;
        }
    });
};
