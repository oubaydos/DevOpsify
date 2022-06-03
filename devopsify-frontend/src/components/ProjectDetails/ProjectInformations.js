import projectImg from "../../res/images/project-img.svg";
import { Box, Typography } from "@mui/material";
import { Edit } from "@mui/icons-material";

const ProjectInformations = () => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        p: 1,
        maxWidth: "400px",
        marginBottom:2
      }}
    >
      <img
        src={projectImg}
        alt="projectImg"
        height="70px"
        width="70px"
        style={{ margin: "auto 0" }}
      />
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          p: 1,
          position: "relative",
          mx: 1,
        }}
      >
        <Typography variant="h5">Hello-World-App</Typography>
        <Typography variant="p" color="#787885">
          Hello world with java
        </Typography>
      </Box>
      <Edit sx={{ mt: 1, color: "#787885" }} />
    </Box>
  );
};

export default ProjectInformations;
