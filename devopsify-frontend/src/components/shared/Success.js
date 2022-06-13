import {Alert, Grid} from "@mui/material";
import React from "react";


export default function Success({message,onClose}) {
    return (
        <Grid>
            <Alert variant="filled" severity="success" onClose={onClose}>
                {message || "the repository has been successfully created"}
            </Alert>
        </Grid>
    );
}