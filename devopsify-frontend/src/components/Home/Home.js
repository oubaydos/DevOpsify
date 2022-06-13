import { Typography, Box, Alert, Button, Link } from "@mui/material";
import * as React from "react";
import { AppContext } from "../../App";
import ProjectList from "../ProjectList/ProjectList";

const Home = () => {
  const currentUser = React.useContext(AppContext);
  const [showAlert, setShowAlert] = React.useState(true);

  return (
    <Box>
      {showAlert &&  !currentUser.githubUsername && (
        <Alert severity="warning" onClose={() => {setShowAlert(false)}}>
          You didn't link your account with github account.
          <Link sx={{ mx: 2, color: "#000" }} href="/github">
            Connect Now
          </Link>
        </Alert>
      )}
      <Typography
        variant="h5"
        noWrap
        component="div"
        sx={{ my: 4 }}
        fontWeight="550"
      >
        Github Contribution Chart
      </Typography>
      {currentUser.githubUsername !== undefined && (
        <img
          src={`http://ghchart.rshah.org/${currentUser.githubUsername}`}
          alt={`${currentUser.githubUsername}'s Github chart`}
        />
      )}
      <ProjectList/>
    </Box>
  );
};

export default Home;
