import i18next from 'i18next';

export default context => {
    console.log(context);
    alert(i18next.t('training-components:action.description'));
};
