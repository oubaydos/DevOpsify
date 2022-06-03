import { Container, Grid, Typography,Box } from "@mui/material";
import React from "react";
import { useParams } from "react-router-dom";
import AnalyseCard from "./AnalyseCard";
import githubLogo from "../../res/images/logo-github.png";
import jenkinsLogo from "../../res/images/logo-jenkins.png";
import ProjectInformations from "./ProjectInformations";

const ProjectDetails = () => {
  const { id } = useParams({});

  return (
    <Box sx={{ml:40}}>
      <ProjectInformations />
      <Typography variant="h6" align="left" sx={{mb:2,p:1}}>DevOps Gate</Typography>
      <Grid container spacing={4}>
        <Grid item xs={7}>
          <AnalyseCard
            logo={githubLogo}
            name="Github Repository"
            analyseResults={{ status: "good" }}
          />
        </Grid>
        <Grid item xs={7}>
          <AnalyseCard
            logo={jenkinsLogo}
            name="Jenkins"
            analyseResults={{ status: "bad" }}
          />
        </Grid>
      </Grid>
    </Box>
  );
};

export default ProjectDetails;
