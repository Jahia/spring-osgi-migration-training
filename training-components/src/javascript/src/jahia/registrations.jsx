import React from 'react';
import {registry} from '@jahia/ui-extender';
import SettingsLayout from '../components/SettingsLayout';
import CloudDownload from '@jahia/moonstone/dist/icons/CloudDownload';
import MyNavItem from '../components/MyNavItem';
import CustomAction from '../components/CustomAction';

export const registerRoute = () => {
    registry.add('adminRoute', 'training-settings', {
        targets: ['administration-sites:100'],
        label: 'training-components:settings.label',
        isSelectable: true,
        render: () => <SettingsLayout/>
    });
};

export const registerAdditional = () => {
    registry.add('adminRoute', 'training-additional', {
        targets: ['jcontent:100'],
        label: 'training-components:additional.label',
        isSelectable: true,
        render: () => <SettingsLayout/>
    });
};

export const registerItem = () => {
    registry.add('primary-nav-item', 'training-item', {
        targets: ['nav-root-top:100'],
        render: () => <MyNavItem/>
    });
};

export const registerAction = () => {
    registry.add('action', 'training-action', {
        buttonIcon: <CloudDownload/>,
        buttonLabel: 'training-components:action.label',
        targets: ['contentActions:10'],
        onClick: context => CustomAction(context)
    });
};
