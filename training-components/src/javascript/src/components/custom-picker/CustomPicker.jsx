import React from "react";
import {Button} from "@material-ui/core";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import Content from "./Content";

const CustomPicker = () => {
    const [showPickerDialog, setShowPickerDialog] = React.useState(false);
    const showDialog = () => {
        setShowPickerDialog(true);
    };
    const hideDialog = () => {
        setShowPickerDialog(false);
    };

    return <>
        <Button onClick={showDialog}>Click me!</Button>
        <Dialog open={showPickerDialog} onClose={hideDialog}>
            <DialogTitle>Browse</DialogTitle>
            <DialogContent>
                <Content/>
            </DialogContent>
        </Dialog>
    </>;
};
export default CustomPicker;
