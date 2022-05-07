import "./css/App.css";
import './css/fonts.css';
import { createTheme, ThemeProvider } from "@mui/material/styles";
import NavBar from "./components/NavBar/NavBar";
import "@fontsource/inter";
import Footer from "./components/shared/footer/Footer"
import CreateNewProjectForm from "./components/CreateNewProjectForm/CreateNewProjectForm"
import SignIn from "./components/loginForm/SignIn";
import {BrowserRouter as Router, useRoutes} from "react-router-dom";
import {Helmet} from "react-helmet";
import {isContributor, isGuest, isAdmin} from "./service/roles";
import { useCookies } from 'react-cookie'

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



const ContributorRoutes = () => useRoutes([

  {path: "/", element: <CreateNewProjectForm/>},
  // {path: "/profil", element: <CoachProfil/>},
  // {path:"/add_offer",element:<AddOffer/>},
  // {path:"/offers",element:<ConsultOffer/>},
  // {path:"/offers/clients",element:<MyClient/>},
  // {path: "/changePassword", element: <ChangePassword/>}
]);
const AdminRoutes = () => useRoutes([
  {path: "/", element: <CreateNewProjectForm/>},
  // {path: "/profil", element: <AdminProfil/>},
  // {path: "/changePassword", element: <ChangePassword/>},
  // {path: "/admin/coaches_documents", element: <CoachList/>},
  // {path: "/NewPassword/:userId", element: <NewPassword/>}
]);
const GuestRoutes = () => useRoutes([
  {path: "/", element: <SignIn/>},
  // {path: "/profil", element: <Home/>},
  {path: "/signin", element: <SignIn/>},
  // {path: "/signup", element: <SignUp/>},
  // {path: "/forgotPassword", element: <Forgot/>},
  // {path: "/EmailSent", element: <EmailSent/>},
  // {path: "/NewPassword/:userId", element: <NewPassword/>},

]);
function App() {
  const [isContributorCookie, setIsContributorCookie, removeIsContributorCookie] = useCookies(['isContributor']);
  const [isAdminCookie, setIsAdminCookie, removeIsAdminCookie] = useCookies(['isAdmin']);
  const [currentUserCookie, setCurrentUserCookie, removeCurrentUserCookie] = useCookies(['currentUser']);

  return (
    <ThemeProvider theme={theme}>
      <div className="App">
        <Helmet>
          <title>DevOpsify</title>
        </Helmet>

        <Router>
          <div className="body">
            <NavBar/>
            {isContributor() &&  <ContributorRoutes/>}
            {isAdmin() && <AdminRoutes/>}
            {isGuest() && <GuestRoutes/>}
            <Footer/>
          </div>
        </Router>
      </div>
    </ThemeProvider>
  );
}

export default App;
