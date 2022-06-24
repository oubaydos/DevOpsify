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
import { handleFormInputChange,handleFormCheckBoxChange } from "../../utils/form";

const NexusForm = ({
  formValues,
  setFormValues,
  styles,
}) => {

  const [nexus, setNexus] = React.useState(formValues.nexus);
  const [server, setServer] = React.useState(formValues.nexus.server);


  React.useEffect(()=>{
    setNexus({...nexus,server:server})
    setFormValues({...formValues,nexus:nexus})
  },[nexus,server])


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

export default NexusForm;
