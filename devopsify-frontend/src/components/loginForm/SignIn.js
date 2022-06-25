import * as React from "react";
import {
  Avatar,
  Alert,
  Button,
  CssBaseline,
  TextField,
  Typography,
  FormControlLabel,
  Checkbox,
  Link,
  Grid,
  Box,
  Container,
} from "@mui/material";

import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import { login } from "../../api/authService";
import { useCookies } from "react-cookie";
import Success from "../shared/Success";
import Error from "../shared/Error";
import {useState} from "react";

export default function SignIn() {
  const [open, setOpen] = React.useState(false);
  const [, setCookie] = useCookies(["Authorization"]);
  const [successful, setSuccessful] = useState(false);
  const [error, setError] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    login(event, setOpen,setCookie, setSuccessful, setError);

  };

  return (
    <Container component="main" maxWidth="xs">
      {open && <Alert severity="success" onClose={() => {setOpen(false)}}>successfuly logged in</Alert>}
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
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Username"
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
            <Grid>
              {(successful && error === false) && (
                  <Success message="successfully signed in"/>
              )}{" "}
            </Grid>
            <Grid>
              {error !== false && (
                  <Error error={error}/>
              )}{" "}
            </Grid>
          </Grid>

        </Box>
      </Box>{" "}
    </Container>
  );
}
