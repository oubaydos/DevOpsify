import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  FormControlLabel,
  Button,
  Typography,
} from "@mui/material";
import jenkinsLogo from "../../res/images/logo-jenkins.png";
import { testConnection } from "../../api/jenkinsApi";

const JenkinsForm = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  setFormValues,
  styles,
}) => {
  const [connectionStatus, setConnectionStatus] = React.useState("");

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
    testConnection(formValues.jenkins,setConnectionStatus);
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
              onChange={handleInputChange}
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
                onChange={handleInputChange}
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
                onChange={handleInputChange}
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
    </Box>
  );
};

export default JenkinsForm;
