import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import { login } from "../../api/authService";
import { useCookies } from 'react-cookie';

export default function SignIn() {

  const [successful, setSuccessful] = useState(false);
  const [cookies, setCookie] = useCookies(['Authorization']);

  const handleSubmit = (event) => {
    event.preventDefault();
    login(event, setSuccessful,setCookie);
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
          <Avatar sx={{ m: 1, bgcolor: "gray" }}>
            <LockOutlinedIcon />
          </Avatar>{" "}
          <Typography component="h1" variant="h5" color="secondary">
            Sign in
          </Typography>{" "}
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              color="success"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              color="success"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="success" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="success"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In{" "}
            </Button>{" "}
            <Grid container>
              <Grid item xs>
                <Link href="/forgotPassword" color="secondary" variant="body2">
                  Forgot Password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="signup" color="secondary" variant="body2">
                  {" "}
                  {"SignUp"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>{" "}
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
            Vous êtes connectés
          </div>
        </Box>
      )}
      </Container>

  );
}
