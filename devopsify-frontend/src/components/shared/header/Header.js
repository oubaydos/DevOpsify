import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import GlobalStyles from '@mui/material/GlobalStyles';
import LogoutIcon from '@mui/icons-material/Logout';

export default function Header(props) {
    return (
        <React.Fragment>
            <GlobalStyles styles={{ul: {margin: 0, padding: 0, listStyle: 'none'}}}/>
            <CssBaseline/>
            <AppBar
                position="static"
                color="default"
                elevation={0}
                sx={{borderBottom: (theme) => `1px solid ${theme.palette.divider}`}}
            >
                <Toolbar sx={{flexWrap: 'wrap'}}>
                    <Typography variant="h6" color="inherit" noWrap sx={{flexGrow: 1}} style={{
                        color: "#1a1a1e",
                        fontFamily: "Inter",
                        fontWeight: 600,
                        fontSize: "25px"
                    }}>
                        {props.title || "Devopsify"}
                    </Typography>
                    <nav style={{margin:"0 auto"}}>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="#"
                            sx={{my: 1, mx: 1.5}}
                            underline = "none"
                        >
                            {props.firstValue || "Projects"}
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="#"
                            sx={{my: 1, mx: 1.5}}
                            underline = "none"
                        >
                            {props.secondValue || "Settings"}
                        </Link>

                    </nav>
                    <Button href="#" startIcon={props.buttonIcon || <LogoutIcon />} variant="outlined" sx={{my: 1, mx: 1.5}}>
                        {props.button || "Logout"}
                    </Button>
                </Toolbar>
            </AppBar>
        </React.Fragment>
    );
}