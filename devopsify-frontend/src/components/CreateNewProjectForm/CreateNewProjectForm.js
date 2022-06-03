import {React,useState} from "react";
import {createNewProject} from "../../api/githubApi"
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

const defaultValues = {
  name: "hello-world",
  location: "",
  initGitRepository: true,
  language: "Java",
  buildSystem: "Maven",
  JDK: "17",
};

const CreateNewProjectForm = () => {
  const [formValues, setFormValues] = useState(defaultValues);

  const handleSubmit = (event) => {
    event.preventDefault();
    createNewProject(formValues);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };
  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target;
    setFormValues({
      ...formValues,
      [name]: checked,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid container direction="column">
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Project name"
            control={
              <TextField
                name="name"
                style={styles.labeled}
                required
                id="create-new-project-name"
                defaultValue={defaultValues.name}
                color="secondary"
                size="small"
                onChange={handleInputChange}
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
                name="location"
                style={styles.labeled}
                required
                id="create-new-project-location"
                defaultValue={defaultValues.location}
                color="secondary"
                size="small"
                onChange={handleInputChange}
              />
            }
            labelPlacement="start"
          />
        </Grid>
        <Grid item style={styles.formItem}>
          <FormControlLabel
            control={
              <Checkbox
                name="initGitRepository"
                defaultChecked={defaultValues.initGitRepository}
                color="success"
                onChange={handleCheckboxChange}
              />
            }
            label="Create Git repository"
          />
        </Grid>
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Build System"
            control={
              <TextField
                name="buildSystem"
                style={styles.labeled}
                required
                disabled
                id="create-new-project-build-system"
                defaultValue={defaultValues.buildSystem}
                color="secondary"
                size="small"
                onChange={handleInputChange}
              />
            }
            labelPlacement="start"
          />
        </Grid>
        <Grid item style={styles.formItem}>
          <FormControlLabel
            name="language"
            label="Language"
            control={
              <TextField
                style={styles.labeled}
                required
                disabled
                id="create-new-project-langauge"
                defaultValue={defaultValues.language}
                color="secondary"
                size="small"
                onChange={handleInputChange}
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
                name="jdk"
                style={styles.labeled}
                disabled
                required
                id="create-new-project-jdk"
                defaultValue={defaultValues.JDK}
                color="secondary"
                size="small"
              />
            }
            labelPlacement="start"
          />
        </Grid>
        <Grid item style={styles.formItem}>
          <Button variant="contained" color="secondary" type="submit">
            Create
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

export default CreateNewProjectForm;
