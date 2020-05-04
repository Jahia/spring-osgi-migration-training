import React from 'react';
import {registry} from '@jahia/ui-extender';
import CloudDownload from '@jahia/moonstone/dist/icons/CloudDownload';
import Main from '../components/Main';
import MyNavItem from "../components/MyNavItem";

export const registerRoute = () => {
    registry.add('adminRoute', 'training-settings', {
        targets: ['administration-sites:100'],
        label: 'training-components:menu.label',
        isSelectable: true,
        render: () => {
            console.log('%c [Training] Register route', 'color: #15b08a');
            return <Main/>;
        }
    });
};

export const registerAdditional = () => {
    registry.add('adminRoute', 'training-additional', {
        targets: ['jcontent:100'],
        label: 'training-components:additional.label',
        isSelectable: true,
        render: () => {
            console.log('%c [Training] Register additional', 'color: #15b08a');
            return <Main/>;
        }
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
        onClick: context => {
            console.log('%c [Training] Register action', 'color: #15b08a', context);
        }
    });
};
