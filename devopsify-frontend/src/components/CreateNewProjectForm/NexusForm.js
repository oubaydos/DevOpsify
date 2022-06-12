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

const NexusForm = ({
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
            >
              Test Connection
            </Button>
            <Typography sx={{ mt: 3, mb: 2 }}>{getStatus()}</Typography>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default NexusForm;
