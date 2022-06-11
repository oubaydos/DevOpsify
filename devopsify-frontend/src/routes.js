
import CreateNewProjectForm from "./components/CreateNewProjectForm/CreateNewProjectForm";
import SignIn from "./components/loginForm/SignIn";
import { useRoutes } from "react-router-dom";
import SignUp from "./components/signupForm/SignUp";
import ConnectToGithub from "./components/ConnectToGithub/ConnectToGithub";
import ProjectList from "./components/ProjectList/ProjectList"
import Home from "./components/Home/Home";
import Loading from "./components/Loading/Loading";
import JenkinsPage from "./components/JenkinsPage/JenkinsPage";
import ProjectDetails from "./components/ProjectDetails/ProjectDetails";
import CreateNewProjectPage from "./components/CreateNewProjectForm/CreateNewProjectPage";



const ContributorRoutes = () =>
  useRoutes([
    { path: "/project/create", element: <CreateNewProjectPage /> },
    { path: "/project", element: <ProjectList /> },
    { path: "/github", element: <ConnectToGithub /> },
    { path: "/jenkins", element: <JenkinsPage /> },
    { path: "/", element: <Home /> },
    { path: "/project/:id", element: <ProjectDetails /> },
    { path: "/github?next=create-project", element: <ConnectToGithub next="/project/create"/> },
  ]);
const AdminRoutes = () =>
  useRoutes([
    { path: "/project/create", element: <CreateNewProjectPage /> },
    { path: "/project", element: <ProjectList /> },
    { path: "/github", element: <ConnectToGithub /> },
    { path: "/jenkins", element: <JenkinsPage /> },
    { path: "/", element: <Home /> },
    { path: "/project/:id", element: <ProjectDetails /> },
    { path: "/github?next=create-project", element: <ConnectToGithub next="/project/create"/> },

  ]);
const GuestRoutes = () =>
  useRoutes([
    { path: "/project/create", element: <CreateNewProjectPage /> },
    { path: "/project", element: <SignIn /> },
    { path: "/github", element: <SignIn /> },
    { path: "/jenkins", element: <SignIn /> },
    { path: "/", element: <SignIn /> },
    { path: "/project/:id", element: <SignIn /> },
    { path: "/", element: <SignIn /> },
    { path: "/login", element: <SignIn /> },
    { path: "/signup", element: <SignUp /> },
  ]);

const getRoutes = (role) => {
  switch (role) {
    case "CONTRIBUTOR":
      return <ContributorRoutes />;
    case "ADMIN":
      return <AdminRoutes />;
    case "GUEST":
      return <GuestRoutes />;
    default:
      return <Loading />;
  }
};

export default getRoutes;