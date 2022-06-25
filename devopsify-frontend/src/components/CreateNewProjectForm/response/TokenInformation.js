import {Alert, Grid} from "@mui/material";
import React from "react";

export default function TokenInformation({token, url, onClose}) {
    return (
        <Alert marg variant="filled" severity="warning" onClose={onClose}>
            {console.log({token, url})}
            the project is successfully created : <br/>
            please consider adding this token as a secret to the webHook generated in
            your repository <br/>token : {token}<br/>
            add it here : {url}
        </Alert>
    );
}
