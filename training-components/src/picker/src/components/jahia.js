import React from "react";
import ReactDOM from "react-dom";
import Main from "./Main";

window.customPickerReactRender = (context, data, targetId) => {
    ReactDOM.render(<React.StrictMode>
        <Main context={context} data={data}/>
    </React.StrictMode>, document.getElementById(targetId));
};
