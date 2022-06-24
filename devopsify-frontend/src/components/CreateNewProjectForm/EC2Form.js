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
import { handleFormCheckBoxChange,handleFormInputChange } from "../../utils/form";

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
  formValues,
  setFormValues,
  styles,
}) => {
  const [connectionStatus, setConnectionStatus] = React.useState("");
  const [generateJenkinsfile, setGenerateJenkinsfile] = React.useState(
    formValues.jenkins.generateJenkinsfile
  );
  const [ec2, setEc2] = React.useState(formValues.ec2);
  const [server, setServer] = React.useState(formValues.ec2.server);


  React.useEffect(()=>{
    setEc2({...ec2,server:server})
    setFormValues({...formValues,ec2:ec2})
  },[ec2,server])


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
              id="ec2-server"
              name="url"
              color="success"
              onChange={(e)=>handleFormInputChange(e,server,setServer)}
              value={server.url}

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
                onChange={(e)=>handleFormInputChange(e,server,setServer)}
                value={server.username}

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
                onChange={(e)=>handleFormInputChange(e,server,setServer)}
                value={server.password}
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
