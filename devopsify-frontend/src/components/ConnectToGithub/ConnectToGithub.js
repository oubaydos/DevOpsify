import githubLogo from "../../res/images/logo-github.png";
import {
    Alert,
    Button,
    CssBaseline,
    TextField,
    Box,
    Container,
} from "@mui/material";

import {React, useState} from "react";
import {connectToGithub} from "../../api/githubApi";


const ConnectToGithub = () => {

    const [successful, setSuccessful] = useState(false);
    const handleSubmit = (event) => {
        event.preventDefault();
        connectToGithub(event, setSuccessful);
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
        </Container>
    );
};

export default ConnectToGithub;
