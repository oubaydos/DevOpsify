import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  Checkbox,
  FormControlLabel,
  Radio,
  RadioGroup,
} from "@mui/material";

import { getDockerfileDefaultValues } from "../../api/dockerApi";
import {
  handleFormInputChange,
  handleFormCheckBoxChange,
} from "../../utils/form";


const backendDockerFileArguments = [
  {
    name: "baseBuildImageName",
    label: "Base build image name",
    type: "TextField",
    build: true,
  },
  {
    name: "baseBuildImageVersion",
    label: "Base build image version",
    type: "TextField",
    build: true,
  },
  {
    name: "baseBuildJdkType",
    label: "Base build JDK Type",
    type: "TextField",
    build: true,
  },
  { name: "workdir", label: "Workdir", type: "TextField", build: true },

  {
    name: "jdkImageName",
    label: "JDK Image Name",
    type: "TextField",
  },
  { name: "jdkVersion", label: "JDK Version", type: "TextField" },
  {
    name: "jdkBaseOsName",
    label: "JDK Base Os Name",
    type: "TextField",
  },
  { name: "port", label: "Port", type: "TextField" },
  { name: "jarName", label: "Jar Name", type: "TextField" },
];
const databaseDockerFileArguments = [
  { name: "imageName", label: "Image Name", type: "TextField" },
  { name: "imageVersion", label: "Image Version", type: "TextField" },
  { name: "imageBaseOS", label: "Image Base OS", type: "TextField" },
  {
    name: "dbInitQueriesFilename",
    label: "Database Init Queries File Name",
    type: "TextField",
  },
];

const DockerForm = ({ formValues, setFormValues, styles }) => {
  const [docker, setDocker] = React.useState(formValues.docker);
  const [dockerBackend, setDockerBackend] = React.useState(
    formValues.docker.dockerBackend
  );
  const [dockerDB, setDockerDB] = React.useState(formValues.docker.dockerDB);


  React.useEffect(() => {
    setDocker({ ...docker, dockerBackend:dockerBackend, dockerDB:dockerDB });
    setFormValues({ ...formValues, docker:docker });
  }, [dockerBackend,dockerDB,docker]);

  React.useEffect(() => {
    getDockerfileDefaultValues(docker, setDocker);
  }, []);

  const getFormControl = (arg, obj, setObj) => {
    //target possible values : 'dockerBackend' for backend and 'dockerDB' for database
    let formControl;
    switch (arg.type) {
      case "TextField":
        formControl = (
          <Grid item style={styles.formItem}>
            <FormControlLabel
              label={arg.label}
              control={
                <TextField
                  disabled={!arg.build && dockerBackend.buildOnly}
                  name={arg.name}
                  style={styles.labeled}
                  required
                  color="secondary"
                  size="small"
                  onChange={(e) => handleFormInputChange(e, obj, setObj)}
                  value={obj[arg.name]}
                />
              }
              labelPlacement="start"
            />
          </Grid>
        );
        break;
      case "CheckBox":
        formControl = (
          <Grid item style={styles.formItem}>
            <FormControlLabel
              label={arg.label}
              control={
                <Checkbox
                  name={arg.name}
                  color="success"
                  onChange={(e) => handleFormCheckBoxChange(e, obj, setObj)}
                  checked={obj[arg.name]}
                />
              }
              labelPlacement="start"
            />
          </Grid>
        );
    }
    return formControl;
  };

  const handleRadioChange = (e) => {
    const { name } = e.target;
    if (name === "defaultDockerBackend") {
      setDocker({
        ...docker,
        defaultDockerBackend: !docker.defaultDockerBackend,
      });
    } else {
      setDocker({ ...docker, defaultDBBackend: !docker.defaultDBBackend });
    }
  };

  return (
    <Box>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={
            <Checkbox
              name="dockerizeBackend"
              color="success"
              onChange={(e) => {
                handleFormCheckBoxChange(e, docker, setDocker);
              }}
              checked={docker.dockerizeBackend}
            />
          }
          label="Dockerize backend ?"
        />
      </Grid>
      {docker.dockerizeBackend && (
        <Grid>
          <RadioGroup
            row
            aria-labelledby="demo-radio-buttons-group-label"
            defaultValue={docker.defaultDockerBackend ? "default" : "custom"}
            name="defaultDockerBackend"
            onChange={handleRadioChange}
          >
            <FormControlLabel
              value="default"
              control={<Radio color="success" />}
              label="default dockerfile"
            />
            <FormControlLabel
              value="custom"
              control={<Radio color="success" />}
              label="custom dockerfile"
            />
          </RadioGroup>
        </Grid>
      )}
      {docker.dockerizeBackend && !docker.defaultDockerBackend && (
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Build Only"
            control={
              <Checkbox
                name="buildOnly"
                color="success"
                onChange={(e) => {
                  handleFormCheckBoxChange(e, dockerBackend, setDockerBackend);
                }}
                checked={dockerBackend.buildOnly}
              />
            }
            labelPlacement="start"
          />
        </Grid>
      )}
      {docker.dockerizeBackend &&
        !docker.defaultDockerBackend &&
        backendDockerFileArguments.map((arg) => {
          return getFormControl(arg, dockerBackend, setDockerBackend);
        })}

      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={
            <Checkbox
              name="dockerizeDB"
              color="success"
              onChange={(e) => {
                handleFormCheckBoxChange(e, docker, setDocker);
              }}
              checked={docker.dockerizeDB}
            />
          }
          label="Dockerize database ?"
        />
      </Grid>
      {docker.dockerizeDB && (
        <Grid>
          <RadioGroup
            row
            aria-labelledby="demo-radio-buttons-group-label"
            defaultValue={docker.defaultDockerDB ? "default" : "custom"}
            name="defaultDockerDB"
            onChange={handleRadioChange}
          >
            <FormControlLabel
              value="default"
              control={<Radio color="success" />}
              label="default dockerfile"
            />
            <FormControlLabel
              value="custom"
              control={<Radio color="success" />}
              label="custom dockerfile"
            />
          </RadioGroup>
        </Grid>
      )}
      {docker.dockerizeDB &&
        !docker.defaultDockerDB &&
        databaseDockerFileArguments.map((arg) => {
          return getFormControl(arg, dockerDB, setDockerDB);
        })}
    </Box>
  );
};

export default DockerForm;
