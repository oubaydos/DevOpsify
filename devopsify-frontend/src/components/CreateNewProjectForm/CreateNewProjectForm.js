import React from "react";
import {
  TextField,
  FormControlLabel,
  Grid,
  Checkbox,
  Button,
} from "@mui/material";

const styles = {
  labeled: {
    marginLeft: 20,
  },
  formItem: {
    marginBottom: 10,
    marginTop: 10,
  },
};

const CreateNewProjectForm = () => (
  <form>
    <Grid container direction="column">
      <Grid item style={styles.formItem}>
        <FormControlLabel
          fullWidth
          label="Project name"
          control={
            <TextField
              style={styles.labeled}
              required
              id="create-new-project-name"
              defaultValue="hello-world"
              color="secondary"
              size="small"
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Project Location"
          control={
            <TextField
              fullWidth
              style={styles.labeled}
              required
              id="create-new-project-location"
              defaultValue=""
              color="secondary"
              size="small"
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={<Checkbox defaultChecked color="success" />}
          label="Create Git repository"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Build System"
          control={
            <TextField
              style={styles.labeled}
              required
              disabled
              id="create-new-project-build-system"
              defaultValue="maven"
              color="secondary"
              size="small"
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Language"
          control={
            <TextField
              style={styles.labeled}
              required
              disabled
              id="create-new-project-langauge"
              defaultValue="Java"
              color="secondary"
              size="small"
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="JDK"
          control={
            <TextField
              style={styles.labeled}
              disabled
              required
              id="create-new-project-jdk"
              defaultValue="17"
              color="secondary"
              size="small"
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <Button variant="contained" color="secondary">
          Create
        </Button>
      </Grid>
    </Grid>
  </form>
);

CreateNewProjectForm.propTypes = {};

CreateNewProjectForm.defaultProps = {};

export default CreateNewProjectForm;
