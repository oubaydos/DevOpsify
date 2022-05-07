import githubLogo from "../../res/images/logo-github.png";
import {
  Alert,
  Button,
  CssBaseline,
  TextField,
  Box,
  Container,
} from "@mui/material";

import {React } from "react";


const ConnectToGithub = () => {

  const handleSubmit = (event) => {
    event.preventDefault();
    //TODO test github connection
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
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
            maxHeight: { xs: 200, md: 100 },
            maxWidth: { xs: 200, md: 100 },
          }}
          alt="github logo"
          src={githubLogo}
        />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="github"
            label="Github token"
            name="github"
            autoComplete="github"
            color="success"
            autoFocus
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="success"
            sx={{ mt: 3, mb: 2 }}
          >
            Connect{" "}
          </Button>{" "}
        </Box>
      </Box>{" "}
    </Container>
  );
};

export default ConnectToGithub;
