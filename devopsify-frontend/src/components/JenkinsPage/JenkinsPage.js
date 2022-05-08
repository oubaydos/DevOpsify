import jenkinsLogo from "../../res/images/logo-jenkins.png";
import {
  Alert,
  Button,
  CssBaseline,
  TextField,
  Box,
  Container,
  FormControlLabel,
  Grid,
} from "@mui/material";

import { React,useState } from "react";

import {updateJenkinsServer} from "../../api/jenkinsApi";

const styles = {
  labeled: {
    marginLeft: 20,
  },
  formItem: {
    marginBottom: 10,
    marginTop: 10,
  },
};

const JenkinsPage = () => {
  const projectId = 3;

  const [server, setServer] = useState({
    url: "",
    username: "",
    password: "",
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    updateJenkinsServer(projectId,server);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setServer({
      ...server,
      [name]: value,
    });
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Box
          component="img"
          sx={{
            maxHeight: { xs: 200, md: 150 },
            maxWidth: { xs: 200, md: 150 },
            my: 2,
          }}
          alt="jenkins logo"
          src={jenkinsLogo}
        />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            size="small"
            required
            fullWidth
            id="jenkins-server"
            label="Jenkins Url"
            name="url"
            color="success"
            autoFocus
            onChange={handleInputChange}
          />
          <Grid item style={styles.formItem}>
            <FormControlLabel
              label="username"
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
              label="password"
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
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="success"
            sx={{ mt: 3, mb: 2 }}
          >
            Connect{" "}
          </Button>{" "}
        </Box>
      </Box>{" "}
    </Container>
  );
};

export default JenkinsPage;
