import githubLogo from "../../res/images/logo-github.png";
import {
    Alert,
    Button,
    CssBaseline,
    TextField,
    Box,
    Container, Grid,
} from "@mui/material";

import {React, useState} from "react";
import {connectToGithub} from "../../api/githubApi";
import Success from "../shared/Success";
import Error from "../shared/Error";


const ConnectToGithub = () => {

    const [successful, setSuccessful] = useState(false);
    const [error, setError] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        connectToGithub(event, setSuccessful,setError);
    };

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <Box
                sx={{
                    marginTop: 12,
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                }}
            >
                <Box
                    component="img"
                    sx={{
                        height: 200,
                        width: 200,
                        maxHeight: {xs: 200, md: 100},
                        maxWidth: {xs: 200, md: 100},
                        my: 2,
                    }}
                    alt="github logo"
                    src={githubLogo}
                />
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                    <TextField
                        margin="normal"
                        size="small"
                        required
                        fullWidth
                        id="username"
                        label="Github Username"
                        name="username"
                        autoComplete="username"
                        color="success"
                        autoFocus
                    />
                    <TextField
                        margin="normal"
                        size="small"
                        required
                        fullWidth
                        id="token"
                        label="Github token"
                        name="token"
                        autoComplete="token"
                        color="success"
                        autoFocus
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="success"
                        sx={{mt: 3, mb: 2}}
                    >
                        Connect{" "}
                    </Button>{" "}
                </Box>
            </Box>{" "}
            <Grid>
                {(successful && error === false) && (
                    <Success message={"successfully connected to github"}/>
                )}{" "}
            </Grid>
            <Grid>
                {error !== false && (
                    <Error error={error}/>
                )}{" "}
            </Grid>
        </Container>

    );
};

export default ConnectToGithub;
