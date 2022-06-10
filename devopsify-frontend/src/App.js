import "./css/App.css";
import "./css/fonts.css";
import { ThemeProvider } from "@mui/material/styles";
import NavBar from "./components/NavBar/NavBar";
import "@fontsource/inter";
import Footer from "./components/shared/footer/Footer";
import { BrowserRouter as Router } from "react-router-dom";
import { Helmet } from "react-helmet";
import { useCookies, CookiesProvider } from "react-cookie";
import { getAuthenticatedUser } from "./api/authService";
import { useState, useEffect } from "react";
import getRoutes from "./routes";
import myTheme from "./theme";
import * as React from "react"

export const AppContext = React.createContext();

const theme = myTheme;

function App() {
  const [authCookies] = useCookies(["Authorization"]);

  const authenticatedUser = () => {
    getAuthenticatedUser(authCookies.Authorization, setCurrentUser);
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
        <AppContext.Provider value={currentUser}>
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
        </AppContext.Provider>
      </ThemeProvider>
    </CookiesProvider>
  );
}

export default App;
