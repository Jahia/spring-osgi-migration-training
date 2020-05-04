import React from "react";
import {useTranslation} from "react-i18next";
import {PrimaryNavItem} from "@jahia/moonstone";
import Setting from "@jahia/moonstone/dist/icons/Setting";

export default () => {
    const {t} = useTranslation();
    console.log('%c [Training] Register item', 'color: #15b08a');
    return <PrimaryNavItem key="/training-item"
                           role="training-item"
                           isSelected={false}
                           label={t('training-components:item.label')}
                           icon={<Setting/>}
                           url={"/tools"}/>
};
