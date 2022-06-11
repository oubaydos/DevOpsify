import React, { useState, Component } from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider, useTheme } from "@mui/material/styles";
import { signup } from "../../api/signupApi";
// import {signUpService} from "../../service/signing";

export default function SignUp() {
  const theme = useTheme();

    const [successful, setSuccessful] = useState(false);
  const [value, setValue] = useState("");
  
  const handleChange = (e) => {
    setValue(e.target.value);
  };

  const handleSubmit = (event) => {
    console.log("heeeere")
    event.preventDefault();
    signup(event, setSuccessful);
  };

  return (
    <ThemeProvider theme={theme}>
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
          <Avatar sx={{ m: 1 }}>
            <LockOutlinedIcon />
          </Avatar>{" "}
          <Typography component="h1" variant="h5" color="#2e7d32">
            Sign up{" "}
          </Typography>{" "}
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12} sm={12}></Grid>
              <Grid item xs={12} sm={12}>
                <TextField
                  required
                  fullWidth
                  id="username"
                  label="username"
                  name="username"
                  autoComplete="username"
                  color="secondary"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  color="secondary"
                />
              </Grid>{" "}
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  color="secondary"
                />
              </Grid>
            </Grid>{" "}
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="secondary"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up{" "}
            </Button>{" "}
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/login" color="#2e7d32" variant="body2">
                  Already have an account ? Sign in
                </Link>{" "}
              </Grid>
            </Grid>{" "}
          </Box>{" "}
        </Box>{" "}
      </Container>
      {successful && (
        <Box mt={5}>
          <div
            style={{
              padding: "10px",
              marginBottom: "-20px",
              borderRadius: "3px 3px 3px 3px",
              color: "#270",
              backgroundColor: "#DFF2BF",
            }}
          >
            Succès
          </div>
        </Box>
      )}{" "}
    </ThemeProvider>
  );
}
