import i18next from 'i18next';

const CustomAction = context => {
    console.log(context);
    alert(i18next.t('training-components:action.description'));
};
export default CustomAction;
