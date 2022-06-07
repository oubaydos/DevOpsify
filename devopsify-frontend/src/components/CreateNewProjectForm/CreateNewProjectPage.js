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

const CreateNewProjectPage = () => {
  const [current, setCurrent] = React.useState(0);
  const [formValues, setFormValues] = React.useState({});
  const [successful, setSuccessful] = React.useState(false);
  const [error, setError] = React.useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    createNewProject(formValues, setSuccessful, setError);
    console.log(formValues);
  };

  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target;
    setFormValues({
      ...formValues,
      [name]: checked,
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };
  const formProperties = {
    handleCheckboxChange: handleCheckboxChange,
    handleInputChange: handleInputChange,
  };

  const parts = {
    General: <GeneralForm {...formProperties} />,
    Github: <GithubForm {...formProperties} />,
    Maven: <MavenForm {...formProperties} />,
    Docker: <DockerForm {...formProperties} />,
    Jenkins: <JenkinsForm {...formProperties} />,
    Nexus: <NexusForm {...formProperties} />,
  };

  const handleOnButtonClick = (e) => {
    if (current != Object.keys(parts).length - 1) {
      setCurrent(current + 1);
    } else {
      handleSubmit(e);
    }
  };

  const handleBackClick = ()=>{
    setCurrent(current-1);
  }

  return (
    <Box
      sx={{
        maxWidth: "60%",
        marginLeft: 40,
        borderRadius: 5,
        position: "relative",
        backgroundColor: "white",
      }}
    >
      <Box
        sx={{
          my: 2,
          py: 2,
        }}
      >
        <Grid container>
          {Object.keys(parts).map((name, index) => (
            <Grid item xs={2}>
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
            height: 250,
            my: 4,
          }}
        >
          {Object.values(parts)[current]}
        </Container>
        {current > 0 && (
          <Button
            variant="inline"
            sx={{
              position: "absolute",
              left:0,
              bottom: 0,
              m: 3,
              backgroundColor: "green",
              color: "white",
              ':hover': {
                //bgcolor: 'primary.main', // theme.palette.primary.main
                color: 'black',
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
            ':hover': {
              //bgcolor: 'primary.main', // theme.palette.primary.main
              color: 'black',
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
  );
};

export default CreateNewProjectPage;
