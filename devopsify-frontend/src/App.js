import "./css/App.css";
import "./css/fonts.css";
import {
  createTheme,
  ThemeProvider,
  experimental_sx as sx,
} from "@mui/material/styles";
import NavBar from "./components/NavBar/NavBar";
import Sidebar from "./components/Sidebar/Sidebar";
import "@fontsource/inter";
import Footer from "./components/shared/footer/Footer";
import CreateNewProjectForm from "./components/CreateNewProjectForm/CreateNewProjectForm";
import SignIn from "./components/loginForm/SignIn";
import { BrowserRouter as Router, useRoutes } from "react-router-dom";
import { Helmet } from "react-helmet";
import { useCookies, CookiesProvider } from "react-cookie";
import { getAuthenticatedUser } from "./api/authService";
import { useState, useEffect } from "react";
import colors from "./utils/colors.json";
import configData from "./config.json";
import getRoutes from "./routes";

const theme = createTheme({
  typography: {
    fontFamily: ["Inter"].join(","),
    fontSize: 15,
    button: {
      textTransform: "none",
    },
  },
  palette: {
    primary: {
      main: colors.PRIMARY,
    },
    secondary: {
      main: colors.SECONDARY,
    },
    grey: {
      main: colors.GREY,
    },
  },
  components: {
    MuiAvatar: {
      styleOverrides: {
        root: sx({
          bgcolor: colors.SECONDARY,
        }),
      },
    },
  },
});

function App() {
  const [authCookies] = useCookies(["Authorization"+configData.COOKIE_SUFFIX]);

  const authenticatedUser = () => {
    getAuthenticatedUser(authCookies.Authorization_devopsify, setCurrentUser);
  };
  const [currentUser, setCurrentUser] = useState({
    username: "",
    role: "",
  });

  useEffect(() => {
    authenticatedUser();
  }, []);

  return (
    <CookiesProvider>
      <ThemeProvider theme={theme}>
        <div className="App">
          <Helmet>
            <title>DevOpsify</title>
          </Helmet>

          <Router>
            <div className="body">
              <NavBar />
              {getRoutes(currentUser.role)}
              <Footer />
            </div>
          </Router>
        </div>
      </ThemeProvider>
    </CookiesProvider>
  );
}

export default App;
