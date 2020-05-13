import React from 'react';
import {useQuery} from '@apollo/react-hooks';
import {gql} from 'apollo-boost';
import CompanyList from './CompanyList';
import CircularProgress from "@material-ui/core/CircularProgress";

export default () => {
    const COMPANIES_QUERY = gql`query CompaniesListQuery($language: String) {
        companies(workspace: LIVE, language: $language, descendantPath: "/sites/digitall") {
            uuid
            title: displayName(language : $language)
            description : property(name : "overview", language : $language) {
                value
            }
        }
    }`;

    const {loading, data} = useQuery(COMPANIES_QUERY, {variables: {language: 'en'}});
    if (loading) {
        return <CircularProgress/>;
    }

    if (data && data.companies) {
        const companies = data.companies.map(node => {
            return {
                id: node.uuid,
                title: node.title,
                description: node.description.value,
                image: node.thumbnail ? `http://localhost:8080/files/live${node.thumbnail.url.path}?t=thumbnail2` : null
            };
        });
        return <CompanyList companies={companies}/>;
    }
    return <></>;
};
