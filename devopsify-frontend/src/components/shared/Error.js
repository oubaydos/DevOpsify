import {Alert, Grid} from "@mui/material";
import React from "react";


export default function Error({error,onClose}) {
    return (
            <Alert variant="filled" severity="error" onClose={onClose}>
                Error:
                <br/>
                {error.response.data.exception.message}
            </Alert>
    );
}