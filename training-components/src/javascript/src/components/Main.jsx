import React from 'react';
import {createGenerateClassName, StylesProvider} from '@material-ui/core';
import {ApolloProvider} from '@apollo/react-hooks';
import ApolloClient from 'apollo-boost';
import {InMemoryCache} from 'apollo-cache-inmemory';
import CompanyList from './CompanyList.container';
import {ToastProvider} from 'react-toast-notifications';

const Main = ({config}) => <StylesProvider
    generateClassName={createGenerateClassName({productionPrefix: 'training-'})}>
    <ApolloProvider client={new ApolloClient(Object.assign({
        uri: '/modules/graphql',
        cache: new InMemoryCache()
    }, config))}>
        <ToastProvider>
            <CompanyList/>
        </ToastProvider>
    </ApolloProvider>
</StylesProvider>;
export default Main;
