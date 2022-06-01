import { Container, Grid, Typography } from "@mui/material";
import React from "react";
import { useParams } from "react-router-dom";
import AnalyseCard from "./AnalyseCard";
import githubLogo from "../../res/images/logo-github.png";
import jenkinsLogo from "../../res/images/logo-jenkins.png";

const ProjectDetails = () => {
  const { id } = useParams({});

  return (
    <Container>
      <Typography variant="h4">DevOps Gate</Typography>
      <Grid container spacing={4}>
        <Grid item xs={12}>
          <AnalyseCard
            logo={githubLogo}
            name="Github Repository"
            analyseResults={{ status: "good" }}
          />
        </Grid>
        <Grid item xs={12}>
          <AnalyseCard
            logo={jenkinsLogo}
            name="Jenkins"
            analyseResults={{ status: "bad" }}
          />
        </Grid>
      </Grid>
    </Container>
  );
};

export default ProjectDetails;
