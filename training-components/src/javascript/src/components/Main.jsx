import React from 'react';
import {createGenerateClassName, StylesProvider} from '@material-ui/core';
import {ApolloProvider} from '@apollo/react-hooks';
import ApolloClient from 'apollo-boost';
import {InMemoryCache} from 'apollo-cache-inmemory';
import Grid from '@material-ui/core/Grid/Grid';
import Paper from '@material-ui/core/Paper/Paper';
import CompanyList from './CompanyList.container';
import {ToastProvider} from 'react-toast-notifications';

export default () => {
    const JWTDXToken = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vamFoaWEuY29tIiwic3ViIjoiYXBpIHZlcmlmaWNhdGlvbiIsImlzcyI6ImR4Iiwic2NvcGVzIjpbInRyYWluaW5nIl0sImlhdCI6MTU4ODA2MzA0NywianRpIjoiOTExM2FiZTgtZmExOS00YmE2LWFlYWEtZGYwNTZlZTRlNTJlIn0.y0cZp0oOTDIuyIJteLbPG0GqSqkxEZGTBVn8MFCvgio';
    const client = new ApolloClient({
        uri: 'http://localhost:8080/modules/graphql',
        headers: {Authorization: `Bearer ${JWTDXToken}`},
        cache: new InMemoryCache()
    });

    console.log('%c [Training] Main component', 'color: #15b08a');

    return <StylesProvider generateClassName={createGenerateClassName({productionPrefix: 'training-'})}>
        <ApolloProvider client={client}>
            <ToastProvider>
                <Grid container>
                    <Grid item xs={12}>
                        <Paper>
                            <CompanyList/>
                        </Paper>
                    </Grid>
                </Grid>
            </ToastProvider>
        </ApolloProvider>
    </StylesProvider>;
};
