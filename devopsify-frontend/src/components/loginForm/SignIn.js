import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider, useTheme} from '@mui/material/styles';
import {useState} from "react";
import {login} from "../../api/loginApi";


// const theme = createTheme({
//     palette: {
//         primary:{
//             main:"#EDEDF0",
//         },
//         secondary:{
//             main:"#000000",
//         },
//         success: {
//             main: "#EDEDF0",
//         }
//     },
// });

export default function SignIn() {
    const theme = useTheme();

    const [successful, setSuccessful] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        // alert(event.currentTarget)
        login(event,setSuccessful);


    };
    return (<
        ThemeProvider theme={theme}>
        <
            Container component="main"
                      maxWidth="xs">
            <
                CssBaseline / >
                <
                    Box sx={
                    {
                        marginTop: 12,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }
                }>
                    <
                        Avatar sx={
                        {m: 1}
                    }>
                        < LockOutlinedIcon/>
                    <
        /Avatar> <
                    Typography component="h1"
                               variant="h5"
                               color="secondary">
                    Sign in
                <
        /Typography> <
                    Box component="form"
                        onSubmit={handleSubmit}
                        noValidate sx={
                    {mt: 1}
                }>
                    <
                        TextField margin="normal"
                                  required fullWidth id="username"
                                  label="username"
                                  name="username"
                                  autoComplete="username"
                                  color="secondary"
                                  autoFocus /
                    >
                        <
                            TextField margin="normal"
                                      required fullWidth name="password"
                                      label="Password"
                                      type="password"
                                      color="secondary"
                                      id="password"
                                      autoComplete="current-password" /
                        >
                            <
                                FormControlLabel control={< Checkbox value="remember"
                                                                     color="secondary" / >
                                }
                                label = "Remember me" /
                                >
                                <
                                    Button type="submit"
                                           fullWidth variant="contained"
                                           color="secondary"
                                           sx={
                                               {mt: 3, mb: 2}
                                           }>
                                    Sign In <
        /Button> <
                                Grid container>
                                <
                                    Grid item xs>
                                    <
                                        Link href="/forgotPassword"
                                             color="secondary"
                                             variant="body2">
                                        Forgot Password?
                                    <
        /Link>
                                </Grid>
                                <Grid item>
                                    <
                                        Link href="signup"
                                             color="secondary"
                                             variant="body2"> {"SignUp"}
                                    </Link>
                                < /Grid>
                            </Grid>
                            </Box>
                                </Box>  < /
                                Container >
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
                                <
                /ThemeProvider>

                                );
                            }