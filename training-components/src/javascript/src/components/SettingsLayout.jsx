import React from 'react';
import Grid from '@material-ui/core/Grid/Grid';
import Paper from '@material-ui/core/Paper/Paper';
import Main from './Main';

const SettingsLayout = () => <Grid container>
    <Grid item xs={12}>
        <Paper>
            <Main/>
        </Paper>
    </Grid>
</Grid>;
export default SettingsLayout;
