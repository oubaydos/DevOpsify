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
import nexusLogo from "../../res/images/logo-nexus.png";
import { testConnection } from "../../api/jenkinsApi";

const NexusForm = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  setFormValues,
  styles,
}) => {

  const handleNexusServerInputChange = (e) => {
    const { name, value } = e.target;
    let nexus = formValues.nexus;
    let server = formValues.nexus.server;
    server = { ...server, [name]: value };

    nexus = { ...nexus, server };
    setFormValues({
      ...formValues,
      nexus: nexus,
    });
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
          alt="nexus logo"
          src={nexusLogo}
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
              id="nexus-server"
              name="url"
              color="success"
              onChange={handleNexusServerInputChange}
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
                onChange={handleNexusServerInputChange}

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
                onChange={handleNexusServerInputChange}
              />
            }
            labelPlacement="start"
          />
        </Grid>
      </Grid>
     
    </Box>
  );
};

export default NexusForm;
