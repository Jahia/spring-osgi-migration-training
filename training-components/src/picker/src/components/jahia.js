import React from "react";
import ReactDOM from "react-dom";
import Main from "./Main";

window.customPickerReactRender = (contextualData, targetId) => {
    ReactDOM.render(<React.StrictMode>
        <Main dxContext={contextualData}/>
    </React.StrictMode>, document.getElementById(targetId));
};
