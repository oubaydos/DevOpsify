import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  FormControlLabel,
  Button,
  Typography,
  Divider,
  Checkbox,
} from "@mui/material";
import ec2Logo from "../../res/images/logo-ec2.svg";
import { testConnection } from "../../api/jenkinsApi";

const jenkinsfileArguments = [
  { name: "imageName", label: "Image Name" },
  { name: "dockerhubUsername", label: "Dockerhub Username" },
  {
    name: "githubRepositoryUrl",
    label: "Github Repository Url",
  },
  {
    name: "ec2Username",
    label: "EC2 Username",
    deployment: true,
  },
  { name: "ec2Ip", label: "EC2 Ip", deployment: true },
  {
    name: "ec2ContainerPort",
    label: "EC2 Container Port",
    deployment: true,
  },
  {
    name: "ec2DeploymentPort",
    label: "EC2 Deployment Port",
    deployment: true,
  },
];

const EC2Form = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  setFormValues,
  styles,
}) => {
  const [connectionStatus, setConnectionStatus] = React.useState("");
  const [generateJenkinsfile, setGenerateJenkinsfile] = React.useState(
    formValues.jenkins.generateJenkinsfile
  );
  const [withDeployment, setWithDeployment] = React.useState(
    formValues.jenkins.jenkinsfile.withDeployment
  );

  const handleEc2ServerInputChange = (e) => {
    const { name, value } = e.target;
    let values = formValues.ec2;
    let server = formValues.ec2.server;
    server = { ...server, [name]: value };

    values = { ...values, server };
    setFormValues({
      ...formValues,
      ec2: values,
    });
  };

  const getStatus = () => {
    let result = "";
    switch (connectionStatus) {
      case "success":
        result = "success";
        break;
      case "failed":
        result = "failed";
        break;
      case "connecting":
        result = "connecting ...";
        break;
    }
    return result;
  };

  const handleTestConncetionButtonClick = () => {
    setConnectionStatus("connecting");
    testConnection(formValues.jenkins, setConnectionStatus);
  };

  return (
    <Box>
      <Grid item style={styles.formItem}>
        <Box
          component="img"
          sx={{
            maxHeight: { xs: 100, md: 80 },
            maxWidth: { xs: 100, md: 80 },
          }}
          alt="ec2 logo"
          src={ec2Logo}
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="URL"
          control={
            <TextField
              style={styles.labeled}
              margin="normal"
              size="small"
              required
              id="jenkins-server"
              name="url"
              color="success"
              onChange={handleEc2ServerInputChange}
            />
          }
          labelPlacement="start"
        />
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Username"
            control={
              <TextField
                name="username"
                style={styles.labeled}
                required
                id="username"
                color="secondary"
                size="small"
                onChange={handleEc2ServerInputChange}

              />
            }
            labelPlacement="start"
          />{" "}
        </Grid>
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Password"
            control={
              <TextField
                type="password"
                name="password"
                style={styles.labeled}
                required
                id="password"
                color="secondary"
                size="small"
                password
                onChange={handleEc2ServerInputChange}

              />
            }
            labelPlacement="start"
          />
        </Grid>
      </Grid>
    </Box>
  );
};

export default EC2Form;
