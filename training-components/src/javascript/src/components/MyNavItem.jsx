import React from 'react';
import {useTranslation} from 'react-i18next';
import {PrimaryNavItem} from '@jahia/moonstone';
import Bug from '@jahia/moonstone/dist/icons/Bug';

export default () => <PrimaryNavItem key="/training-item" role="training-item" isSelected={false}
                                     label={useTranslation().t('training-components:item.tools.label')} icon={<Bug/>}
                                     url={"/tools"}/>;
