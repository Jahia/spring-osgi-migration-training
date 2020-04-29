import React from 'react';
import * as PropTypes from 'prop-types';
import {gql} from 'apollo-boost';
import {useMutation} from '@apollo/react-hooks';
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import {useToasts} from 'react-toast-notifications'

const CallMutation = variables => {
    const DELETENODE_MUTATION = gql`mutation DeleteNode($pathOrId: String!) {
        jcr(workspace: EDIT) {
            mutateNode(pathOrId: $pathOrId) {
                delete
            }
        }
    }`;

    const {addToast} = useToasts();

    const [mutation, {loading}] = useMutation(DELETENODE_MUTATION, {variables});
    if (loading) {
        return <CircularProgress/>;
    }
    return <Button variant={"contained"} onClick={() => {
        mutation().then(() => {
            addToast('Node deleted successfully', {appearance: 'info', autoDismiss: true});
        }).catch(error => {
            addToast(error.message, {appearance: 'error', autoDismiss: true});
        })
    }}>Submit</Button>;
};

CallMutation.propTypes = {
    pathOrId: PropTypes.string.isRequired
};

export default CallMutation;
