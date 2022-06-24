import { Box, Container, Typography, Grid, Button, Alert } from "@mui/material";
import * as React from "react";
import DockerForm from "./DockerForm";
import GeneralForm from "./GeneralForm";
import GithubForm from "./GithubForm";
import JenkinsForm from "./JenkinsForm";
import MavenForm from "./MavenForm";
import NexusForm from "./NexusForm";
import Error from "../shared/Error";
import Success from "../shared/Success";
import { createNewProject } from "../../api/projectService";
import getArchetypes from "../../api/mavenApi";
import DEFAULT_VALUES from "./FormDefaultValues.json";
import EC2Form from "./EC2Form";
import { useState } from "react";
import { notEmpty } from "../../utils/utils";
import TokenInformation from "./response/TokenInformation";

const styles = {
  labeled: {
    marginLeft: 20,
  },
  formItem: {
    marginBottom: 10,
    marginTop: 10,
  },
};

const CreateNewProjectPage = () => {
  const [current, setCurrent] = React.useState(0);

  const [successful, setSuccessful] = React.useState(false);

  const [error, setError] = React.useState(false);
  const [tokenInformation, setTokenInformation] = useState({
    token: "",
    url: "",
  });
  const [archetypes, setArchetypes] = React.useState([]);

  const [formValues, setFormValues] = React.useState(DEFAULT_VALUES);

  React.useEffect(() => {
    getArchetypes(setArchetypes);
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    createNewProject(formValues, setSuccessful, setError, setTokenInformation);
    console.log(formValues);
  };

  const handleNextClick = (e) => {
    console.log(formValues);
    if (current != Object.keys(parts).length - 1) {
      setCurrent(current + 1);
    } else {
      handleSubmit(e);
    }
  };

  const handleBackClick = () => {
    setCurrent(current - 1);
  };

  const formProperties = {
    formValues,
    setFormValues,
    styles,
  };

  const parts = {
    General: <GeneralForm {...formProperties} />,
    Github: <GithubForm {...formProperties} />,
    Maven: <MavenForm {...formProperties} archetypes={archetypes} />,
    Docker: <DockerForm {...formProperties} />,
    Nexus: <NexusForm {...formProperties} />,
    Jenkins: <JenkinsForm {...formProperties} />,
    EC2: <EC2Form {...formProperties} />,
  };

  return (
    <Box>
      {error && (
        <Error
          error={error}
          onClose={() => {
            setError(false);
          }}
        />
      )}
      {/* {showNotif && successful && error === false && <Success onClose={() => {setShowNotif(false)}}/>} */}
      {successful && !error && (
        <TokenInformation
          token={tokenInformation.token}
          url={tokenInformation.url}
          onClose={() => {
            setSuccessful(false);
          }}
        />
      )}
      <Typography
        variant="h5"
        noWrap
        component="div"
        sx={{ my: 4 }}
        fontWeight="550"
      >
        Create New Project
      </Typography>
      <Box
        sx={{
          maxWidth: "60%",
          marginLeft: 40,
          borderRadius: 5,
          position: "relative",
          backgroundColor: "white",
          boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
        }}
      >
        <Box
          sx={{
            py: 2,
          }}
        >
          <Grid container>
            {Object.keys(parts).map((name, index) => (
              <Grid item xs={1.7}>
                <Typography
                  variant="h7"
                  sx={{ py: 1, px: 2, borderRadius: 10 }}
                  style={
                    current === index
                      ? { backgroundColor: "grey", color: "white" }
                      : {}
                  }
                >
                  {name}
                </Typography>
              </Grid>
            ))}
          </Grid>
        </Box>
        <Box>
          <Container
            sx={{
              marginTop: 2,
              pb: 10,
            }}
          >
            <Grid
              container
              direction="column"
              alignItems="center"
              justify="center"
              spacing={0}
            >
              {Object.values(parts)[current]}
            </Grid>
          </Container>
          {current > 0 && (
            <Button
              variant="inline"
              sx={{
                position: "absolute",
                left: 0,
                bottom: 0,
                m: 3,
                backgroundColor: "green",
                color: "white",
                ":hover": {
                  //bgcolor: 'primary.main', // theme.palette.primary.main
                  color: "black",
                },
              }}
              onClick={handleBackClick}
            >
              back
            </Button>
          )}
          <Button
            variant="inline"
            sx={{
              position: "absolute",
              right: 0,
              bottom: 0,
              m: 3,
              backgroundColor: "green",
              color: "white",
              ":hover": {
                //bgcolor: 'primary.main', // theme.palette.primary.main
                color: "black",
              },
            }}
            onClick={handleNextClick}
          >
            {current != Object.values(parts).length - 1 ? "next" : "finish"}
          </Button>
        </Box>
      </Box>
    </Box>
  );
};

export default CreateNewProjectPage;
