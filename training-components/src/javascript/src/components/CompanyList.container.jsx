import React from 'react';
import {useQuery} from '@apollo/react-hooks';
import {gql} from 'apollo-boost';
import CompanyList from './CompanyList';

export default () => {
    const COMPANIES_QUERY = gql`query CompaniesListQuery($language: String) {
        jcr(workspace:LIVE) {
            nodesByQuery(query: "SELECT * FROM [jdnt:company] as results WHERE ISDESCENDANTNODE(results, '/sites/digitall/')", queryLanguage:SQL2) {
                nodes {
                    uuid
                    title: displayName(language : $language)
                    description : property(name : "overview", language : $language) {
                        value
                    }                    
                }
            }
        }
    }`;

    const generateURL = path => {
        return `http://localhost:8080/files/live${path}?t=thumbnail2`;
    };
    const {loading, data} = useQuery(COMPANIES_QUERY, {variables: {language: 'en'}});
    const companies = [];
    if (data && data.jcr && data.jcr.nodesByQuery) {
        // Build the company data as expected by the Company component
        data.jcr.nodesByQuery.nodes.forEach(node => {
            companies.push({
                id: node.uuid,
                title: node.title,
                description: node.description.value,
                image: node.thumbnail ? generateURL(node.thumbnail.url.path) : null
            });
        });
    }
    return <CompanyList loading={loading} companies={companies}/>;
};
