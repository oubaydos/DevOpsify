import "./css/App.css";
import './css/fonts.css';
import { createTheme, ThemeProvider } from "@mui/material/styles";
import NavBar from "./components/NavBar/NavBar";
import "@fontsource/inter";
import Footer from "./components/shared/footer/Footer"

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
      main: "#fff",
    },
    secondary: {
      main: "#4A8951",
    },
    grey :{
      main:'#787885',
    }
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div className="App">
        <NavBar />
        <Footer />
      </div>
    </ThemeProvider>
  );
}

export default App;
