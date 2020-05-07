import ReactDOM from "react-dom";
import React from "react";
import Main from "../components/Main";

window.renderMyApp = ({target, config}) => {
    ReactDOM.render(
        <React.StrictMode>
            <Main config={config}/>
        </React.StrictMode>,
        document.getElementById(target)
    );
};
