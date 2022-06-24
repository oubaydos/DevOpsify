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
import {
  handleFormCheckBoxChange,
  handleFormInputChange,
} from "../../utils/form";

const jenkinsfileArguments = [
  { name: "imageName", label: "Image Name" },
  { name: "dockerhubUsername", label: "Dockerhub Username" },
  // {
  //   name: "githubRepositoryUrl",
  //   label: "Github Repository Url",
  // },
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

const JenkinsForm = ({ formValues, setFormValues, styles }) => {
  const [connectionStatus, setConnectionStatus] = React.useState("");
  const [jenkins, setJenkins] = React.useState(formValues.jenkins);
  const [server, setServer] = React.useState(formValues.jenkins.server);
  const [jenkinsfile, setJenkinsfile] = React.useState(
    formValues.jenkins.jenkinsfile
  );

  React.useEffect(() => {
    setJenkins({ ...jenkins, server: server, jenkinsfile: jenkinsfile });
    setFormValues({ ...formValues, jenkins: jenkins });
  }, [jenkins, jenkinsfile, server]);

  const getFormControl = (arg) => {
    let formControl;
    formControl = (
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label={arg.label}
          control={
            <TextField
              disabled={arg.deployment && !jenkinsfile.withDeployment}
              name={arg.name}
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              onChange={(e) =>
                handleFormInputChange(e, jenkinsfile, setJenkinsfile)
              }
              value={jenkinsfile[arg.name]}
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
        result = (
          <Typography sx={{ my: "auto", ml: 2 }} color="green">
            Success
          </Typography>
        );
        break;
      case "failed":
        result = (
          <Typography sx={{ my: "auto", ml: 2 }} color="error">
            Failed
          </Typography>
        );
        break;
      case "connecting":
        result = (
          <Typography sx={{ my: "auto", ml: 2 }} color="grey">
            Connecting ...
          </Typography>
        );
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
              autoComplete={true}
              value={server.url}
              onChange={(e) => handleFormInputChange(e, server, setServer)}
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
                autoComplete={true}
                value={server.username}
                onChange={(e) => handleFormInputChange(e, server, setServer)}
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
                autoComplete={true}
                id="password"
                color="secondary"
                size="small"
                value={server.password}
                onChange={(e) => handleFormInputChange(e, server, setServer)}
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
            {getStatus()}
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
              onChange={(e) => handleFormCheckBoxChange(e, jenkins, setJenkins)}
              checked={jenkins.generateJenkinsfile}
            />
          }
          labelPlacement="end"
        />
      </Grid>
      {jenkins.generateJenkinsfile && (
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="With Deployment"
            control={
              <Checkbox
                name="withDeployment"
                color="success"
                onChange={(e) => {
                  handleFormCheckBoxChange(e, jenkinsfile, setJenkinsfile);
                }}
                checked={jenkinsfile.withDeployment}
              />
            }
            labelPlacement="end"
          />
        </Grid>
      )}
      {jenkins.generateJenkinsfile &&
        jenkinsfileArguments.map((arg) => getFormControl(arg))}
    </Box>
  );
};

export default JenkinsForm;
