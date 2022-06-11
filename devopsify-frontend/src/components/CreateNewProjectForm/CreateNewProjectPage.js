import { Box, Container, Typography, Grid, Button } from "@mui/material";
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
import DEFAULT_VALUES from "./FormDefaultValues.json"
import EC2Form from "./EC2Form";

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

  const [archetypes, setArchetypes] = React.useState([]);

  const [formValues, setFormValues] = React.useState(DEFAULT_VALUES);

  React.useEffect(() => {
    getArchetypes(setArchetypes);
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    createNewProject(formValues, setSuccessful, setError);
    console.log(formValues);
  };

  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target;
    const currentFormPart = Object.keys(parts)[current].toLowerCase();
    let values = formValues[currentFormPart];
    values = { ...values, [name]: checked };

    setFormValues({
      ...formValues,
      [currentFormPart]: values,
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    const currentFormPart = Object.keys(parts)[current].toLowerCase();
    let values = formValues[currentFormPart];
    values = { ...values, [name]: value };
    setFormValues({
      ...formValues,
      [currentFormPart]: values,
    });
  };

  const handleOnButtonClick = (e) => {
    if (current != Object.keys(parts).length - 1) {
      setCurrent(current + 1);
    } else {
      handleSubmit(e);
    }
  };

  const handleBackClick = () => {
    setCurrent(current - 1);
  };

  const handleMavenArchetypeChange = (e) => {
    const { value } = e.target;

    const currentFormPart = "maven";

    const archetypeGroupId = archetypes[value].groupId;

    const archetypeArtifactId = archetypes[value].artifactId;

    let values = formValues[currentFormPart];

    values = {
      ...values,
      archetypeGroupId: archetypeGroupId,
      archetypeArtifactId: archetypeArtifactId,
    };

    setFormValues({
      ...formValues,
      [currentFormPart]: values,
    });
    console.log(formValues);
  };

  const formProperties = {
    handleCheckboxChange: handleCheckboxChange,
    handleInputChange: handleInputChange,
    formValues: formValues,
    setFormValues,
    styles: styles,
  };

  const parts = {
    General: <GeneralForm {...formProperties} />,
    Github: <GithubForm {...formProperties} />,
    Maven: (
      <MavenForm
        {...formProperties}
        handleMavenArchetypeChange={handleMavenArchetypeChange}
        archetypes={archetypes}
      />
    ),
    Docker: <DockerForm {...formProperties} />,
    Jenkins: <JenkinsForm {...formProperties} />,
    Nexus: <NexusForm {...formProperties} />,
    EC2:<EC2Form {...formProperties}/>
  };

  return (
    <Box>
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
              marginBottom: 10,
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
            onClick={handleOnButtonClick}
          >
            {current != Object.values(parts).length - 1 ? "next" : "finish"}
          </Button>
        </Box>
        <Grid container>
          <Grid item>{successful && error === false && <Success />} </Grid>
          <Grid item>{error !== false && <Error error={error} />} </Grid>
        </Grid>
      </Box>
    </Box>
  );
};

export default CreateNewProjectPage;
