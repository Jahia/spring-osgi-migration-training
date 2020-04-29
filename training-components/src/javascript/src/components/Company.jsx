import React from 'react';
import * as PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import CardActions from "@material-ui/core/CardActions";
import Card from '@material-ui/core/Card';
import Typography from '@material-ui/core/Typography';
import CallMutation from "./CallMutation";

const styles = {
    card: {
        maxWidth: 300,
        maxHeight: 350
    },
    media: {
        height: '120px'
    }
};

const Company = ({classes, title, description, image}) => {
    return <Card className={classes.card}>
        {image ? <CardMedia className={classes.media} image={image} title="Company"/> : ''}
        <CardContent>
            <Typography variant="h4">
                {title}
            </Typography>
            <br/>
            <Typography component="div">
                {/* eslint-disable-next-line react/no-danger */}
                <p dangerouslySetInnerHTML={{__html: description.length > 150 ? `${description.substr(0, 100)}...` : description}}/>
            </Typography>
        </CardContent>
        <CardActions>
            <CallMutation pathOrId={"/sites/digitall/test"}/>
        </CardActions>
    </Card>;
};

Company.propTypes = {
    classes: PropTypes.object.isRequired,
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    image: PropTypes.object
};

export default withStyles(styles)(Company);
