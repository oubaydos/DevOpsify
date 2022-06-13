import {Alert, Grid} from "@mui/material";
import React from "react";


export default function TokenInformation({token,url,onClose}) {
    return (
            <Alert variant="filled" severity="info" onClose={onClose}>
                the project is successfully created : <br/>
                please consider adding this token as a secret to the webHook generated in your repository
                token : {token}
                add it here : {url}
            </Alert>
    );
}