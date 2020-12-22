import React from 'react';
import SettingsLayout from '../components/SettingsLayout';
import CloudDownload from '@jahia/moonstone/dist/icons/CloudDownload';
import MyNavItem from '../components/MyNavItem';
import CustomAction from '../components/CustomAction';
import CustomPicker from '../components/custom-picker/CustomPicker';

export const registerRoute = registry => {
    registry.add('adminRoute', 'training-settings', {
        targets: ['administration-sites:100'],
        label: 'training-components:settings.label',
        isSelectable: true,
        render: () => <SettingsLayout/>
    });
};

export const registerAdditional = registry => {
    registry.add('adminRoute', 'training-additional', {
        targets: ['jcontent:100'],
        label: 'training-components:additional.label',
        isSelectable: true,
        render: () => <SettingsLayout/>
    });
};

export const registerItem = registry => {
    registry.add('primary-nav-item', 'training-item', {
        targets: ['nav-root-top:100'],
        render: () => <MyNavItem/>
    });
};

export const registerAction = registry => {
    registry.add('action', 'training-action', {
        buttonIcon: <CloudDownload/>,
        buttonLabel: 'training-components:action.label',
        targets: ['contentActions:10'],
        onClick: context => CustomAction(context)
    });
};

export const registerSelectorType = registry => {
    registry.add('selectorType', 'custom-picker', {
        cmp: CustomPicker,
        supportMultiple: false,
    });
};
