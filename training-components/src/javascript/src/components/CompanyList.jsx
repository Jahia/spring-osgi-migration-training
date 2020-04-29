import React from 'react';
import * as PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core';
import GridList from '@material-ui/core/GridList';
import GridListTile from '@material-ui/core/GridListTile';
import Company from './Company';

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        overflow: 'hidden',
        backgroundColor: theme.palette.background.paper
    },
    gridList: {
        paddingTop: '12px',
        width: 1020,
        height: 660
    }
});

const CompanyList = ({classes, companies}) => {
    return <div className={classes.root}>
        <GridList className={classes.gridList} justify="center" cellHeight={300} cols={3} spacing={32}>
            {companies.map(company => (
                <GridListTile key={company.id}>
                    <Company title={company.title} description={company.description} image={company.image}/>
                </GridListTile>
            ))}
        </GridList>
    </div>;
};

CompanyList.propTypes = {
    classes: PropTypes.object.isRequired,
    companies: PropTypes.array.isRequired
};

export default withStyles(styles)(CompanyList);
