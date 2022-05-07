import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import { styled, useTheme } from "@mui/material/styles";

import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';

import MenuIcon from '@mui/icons-material/Menu';
import LogoutIcon from '@mui/icons-material/Logout';
import Sidebar from '../Sidebar/Sidebar';

const pages = ['Home', 'Projects', 'Settings','Profile'];
const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];


const ResponsiveAppBar = () => {
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);


  const [open,setOpen] = React.useState(false);

  const handleOpenNavMenu = () => {
    setOpen(true)
  };
  
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setOpen(false);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleClickUserMenu = (event) => {
    console.log(event.target)
    switch(event.target){
      case "Logout" : 
        console.log("logging out...")
        break; 
      default : 
        break;
    }
  }

  return (
    <AppBar position="fixed" className="nav-bar">
      <Sidebar open={open} handleCloseNavMenu={handleCloseNavMenu} />
      <Container maxWidth="xl" >
        <Toolbar disableGutters>
        <IconButton sx={{ flexGrow: 0, display: { xs: 'none', md: 'flex' }}}
              size="large"
              // aria-label="account of current user"
              // aria-controls="menu-appbar"
              // aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
            <MenuIcon />
          </IconButton>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: 30 , ml:2, display: { xs: 'none', md: 'flex' } }}
            fontWeight="550"
          >
            DevOpsify
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: 'block', md: 'none' },
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <Typography textAlign="center">{page}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow : 1, display: { xs: 'flex', md: 'none' } }}
            fontWeight="550"
          >
            DevOpsify
          </Typography>
          <Box sx={{ flexGrow: 2, display: { xs: 'none', md: 'flex' } }}>
            {pages.map((page) => (
              <Button
                color='grey'
                key={page}
                onClick={handleCloseNavMenu}
                sx={{ my: 2,mx:1,px:3, color: 'grey', display: 'block' }}
              >
                {page}
              </Button>
            ))}
          </Box>
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">

              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
              </IconButton>
            </Tooltip>

            <Menu
              sx={{ mt: '45px' }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem name={setting} key={setting} onClick={handleClickUserMenu}>
                  <Typography textAlign="center" >{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default ResponsiveAppBar;
