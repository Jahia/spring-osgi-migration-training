import React from "react";
import {Button, Grid, Input, Modal, Paper} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const Content = ({id, value, onChange}) => {
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
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    const classes = useStyles();
    // getModalStyle is not a pure function, we roll the style only on the first render
    const [modalStyle] = React.useState(getModalStyle);

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
                    <div style={modalStyle} className={classes.paper}>
                        <h2 id="simple-modal-title">Enter a value</h2>
                        <p id="simple-modal-description">
                            <Input id={id} type={"text"} value={value} onChange={evt => onChange(evt?.target?.value)} fullWidth={true}/>
                        </p>
                    </div>
                </Modal>
            </Paper>
        </Grid>
    </Grid>;
};
export default Content;
