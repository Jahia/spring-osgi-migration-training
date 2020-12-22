import React from 'react';
import logo from './logo.svg';
import Grid from '@material-ui/core/Grid/Grid';
import Paper from '@material-ui/core/Paper/Paper';
import './App.css';
import Main from './components/Main';

const App = () => <div className="App">
    <header className="App-header">
        <img src={logo} className="App-logo" alt="logo"/>
        <p>
            Edit <code>src/App.js</code> and save to reload.
        </p>
        <a className="App-link" href="https://reactjs.org" target="_blank" rel="noopener noreferrer">Learn
            React</a>
    </header>
    <Grid container>
        <Grid item xs={12}>
            <Paper>
                <Main config={{
                    uri: "http://localhost:8080/modules/graphql",
                    headers: {Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vamFoaWEuY29tIiwic3ViIjoiYXBpIHZlcmlmaWNhdGlvbiIsImlzcyI6ImR4Iiwic2NvcGVzIjpbInRyYWluaW5nIl0sImlhdCI6MTU4ODA2MzA0NywianRpIjoiOTExM2FiZTgtZmExOS00YmE2LWFlYWEtZGYwNTZlZTRlNTJlIn0.y0cZp0oOTDIuyIJteLbPG0GqSqkxEZGTBVn8MFCvgio"}
                }}/>
            </Paper>
        </Grid>
    </Grid>
</div>;
export default App;
