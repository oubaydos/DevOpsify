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
import jenkinsLogo from "../../res/images/logo-jenkins.png";
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

const JenkinsForm = ({
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

  const handleJenkinsfileInputChange = (e) => {
    const { name, value } = e.target;
    let values = formValues.jenkins;
    let jenkinsfileValues = formValues.jenkins.jenkinsfile;
    jenkinsfileValues = { ...jenkinsfileValues, [name]: value };

    values = { ...values, jenkinsfile: jenkinsfileValues };
    setFormValues({
      ...formValues,
      jenkins: values,
    });
  };
  const handleJenkinsServerInputChange = (e) => {
    const { name, value } = e.target;
    let values = formValues.jenkins;
    let server = formValues.jenkins.server;
    server = { ...server, [name]: value };

    values = { ...values, server };
    setFormValues({
      ...formValues,
      jenkins: values,
    });
  };

  const getFormControl = (arg) => {
    let formControl;
    formControl = (
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label={arg.label}
          control={
            <TextField
              disabled={arg.deployment && !withDeployment}
              name={arg.name}
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              onChange={handleJenkinsfileInputChange}
              value={formValues.jenkins.jenkinsfile[arg.name]}
            />
          }
          labelPlacement="start"
        />
      </Grid>
    );
    return formControl;
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
    testConnection(formValues.jenkins.server, setConnectionStatus);
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
          alt="jenkins logo"
          src={jenkinsLogo}
        />
      </Grid>
      <Divider sx={{ my: 2 }} />
      <Typography variant="h6">Connect Server</Typography>
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
              value={formValues.jenkins.server.url}
              onChange={handleJenkinsServerInputChange}
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
                value={formValues.jenkins.server.username}
                onChange={handleJenkinsServerInputChange}
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
                value={formValues.jenkins.server.password}
                onChange={handleJenkinsServerInputChange}
                password
              />
            }
            labelPlacement="start"
          />
        </Grid>
        <Grid item style={styles.formItem}>
          <Box sx={{ display: "flex" }}>
            <Button
              type="submit"
              variant="contained"
              color="success"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleTestConncetionButtonClick}
            >
              Test Connection
            </Button>
            <Typography sx={{ mt: 3, mb: 2 }}>{getStatus()}</Typography>
          </Box>
        </Grid>
      </Grid>
      <Divider sx={{ my: 2 }} />

      <Typography variant="h6">Jenkinsfile</Typography>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Generate a jenkinsfile"
          control={
            <Checkbox
              name="generateJenkinsfile"
              color="success"
              onChange={(e) => {
                setGenerateJenkinsfile(!generateJenkinsfile);
                handleCheckboxChange(e);
              }}
              checked={generateJenkinsfile}
            />
          }
          labelPlacement="end"
        />
      </Grid>
      {generateJenkinsfile && (
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="With Deployment"
            control={
              <Checkbox
                name="withDeployment"
                color="success"
                onChange={(e) => {
                  setWithDeployment(!withDeployment);
                  handleCheckboxChange(e);
                }}
                checked={withDeployment}
              />
            }
            labelPlacement="end"
          />
        </Grid>
      )}
      {generateJenkinsfile &&
        jenkinsfileArguments.map((arg) => getFormControl(arg))}
    </Box>
  );
};

export default JenkinsForm;
