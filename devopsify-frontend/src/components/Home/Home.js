import { Typography, Box } from "@mui/material";
import * as React from "react";
import {AppContext} from "../../App";

const Home = () => {

  const currentUser = React.useContext(AppContext);

  return (
    <Box sx={{ my: 10 }}>
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
    </Box>
  );
};

export default Home;
