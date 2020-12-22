import React from "react";
import {Button, Grid, Modal, Paper} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const Main = ({context, data}) => {
    console.log(`Context: ${JSON.stringify(context)}`);
    console.log(`Data: ${JSON.stringify(data)}`);

    const rand = () => Math.round(Math.random() * 20) - 10;
    const getModalStyle = () => {
        const top = 50 + rand();
        const left = 50 + rand();

        return {
            top: `${top}%`,
            left: `${left}%`,
            transform: `translate(-${top}%, -${left}%)`,
        };
    };

    const useStyles = makeStyles(theme => ({
        paper: {
            position: 'absolute',
            width: 400,
            backgroundColor: theme.palette.background.paper,
            border: '2px solid #000',
            boxShadow: theme.shadows[5],
            padding: theme.spacing(2, 4, 3),
        },
    }));

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => {
        console.log(`Data O1: ${JSON.stringify(data)}`);
        setOpen(true);
        data.data = ['/sites/digitall/home/footer-1'];
        console.log(`Data O2: ${JSON.stringify(data)}`);
    };
    const handleClose = () => {
        console.log(`Data C1: ${JSON.stringify(data)}`);
        setOpen(false);
        console.log(`Data C2: ${JSON.stringify(data)}`);
    };

    const classes = useStyles();
    // getModalStyle is not a pure function, we roll the style only on the first render
    const [modalStyle] = React.useState(getModalStyle);

    const body = <div style={modalStyle} className={classes.paper}>
        <h2 id="simple-modal-title">Text in a modal</h2>
        <p id="simple-modal-description">
            Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
        </p>
    </div>;

    return <Grid container>
        <Grid item xs={12}>
            <Paper>
                <Button type="button" onClick={handleOpen}>
                    Open Modal
                </Button>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description">
                    {body}
                </Modal>
            </Paper>
        </Grid>
    </Grid>;
};
export default Main;
