import React from "react";
import {useTranslation} from "react-i18next";
import {PrimaryNavItem} from "@jahia/moonstone";
import Setting from "@jahia/moonstone/dist/icons/Setting";

export default () => <PrimaryNavItem key="/training-item"
                                     role="training-item"
                                     isSelected={false}
                                     label={useTranslation().t('training-components:item.label')}
                                     icon={<Setting/>}
                                     url={"/tools"}/>;
