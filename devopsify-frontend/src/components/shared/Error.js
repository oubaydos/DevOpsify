import {Alert, Grid} from "@mui/material";
import React from "react";


export default function Error({error}) {
    return (
        <Grid>
            <Alert variant="filled" severity="error">
                Error:
                <br/>
                {error.response.data.exception.message}
            </Alert>
        </Grid>
    );
}