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

const backendDockerFileArguments = [
  {
    name: "baseBuildImageName",
    label: "Base build image name",
    type: "TextField",
    build: true
  },
  {
    name: "baseBuildImageVersion",
    label: "Base build image version",
    type: "TextField",
    build: true
  },
  { name: "baseBuildJdkType", label: "Base build JDK Type", type: "TextField" , build: true},
  { name: "workdir", label: "Workdir", type: "TextField" ,build:true},

  {
    name: "jdkImageName",
    label: "JDK Image Name",
    type: "TextField",

  },
  { name: "jdkVersion", label: "JDK Version", type: "TextField" },
  {
    name: "jdkBaseOsName",
    label: "JDK Base Os Name",
    type: "TextField"
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

const DockerForm = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  styles,
  setFormValues,
}) => {
  const [dockerizeDB, setDockerizeDB] = React.useState(
    formValues.docker.dockerizeDB
  );
  const [customBackendDockerfile, setCustomBackendDockerfile] =
    React.useState(false);
  const [customDBDockerfile, setCustomDBDockerfile] = React.useState(false);
  const [dockerizeBackend, setDockerizeBackend] = React.useState(
    formValues.docker.dockerizeBackend
  );
  const [buildOnly, setBuildOnly] = React.useState(
    formValues.docker.dockerBackend.buildOnly
  );

  const getFormControl = (arg, target) => {
    //target possible values : 'dockerBackend' for backend and 'dockerDB' for database
    let formControl;
    //console.log(formValues.docker);
    switch (arg.type) {
      case "TextField":
        formControl = (
          <Grid item style={styles.formItem}>
            <FormControlLabel
              label={arg.label}
              control={
                <TextField
                  disabled={!arg.build && buildOnly}
                  name={arg.name}
                  style={styles.labeled}
                  required
                  color="secondary"
                  size="small"
                  onChange={handleInputChange}
                  value={formValues.docker[target][arg.name]}
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
                  onChange={handleCheckboxChange}
                  checked={formValues.docker[target][arg.name]}
                />
              }
              labelPlacement="start"
            />
          </Grid>
        );
    }
    return formControl;
  };

  React.useEffect(() => {
    getDockerfileDefaultValues(formValues, setFormValues);
  }, []);

  const handleRadioChange = (e) => {
    const { name } = e.target;

    if (name === "defaultDockerBackend") {
      setCustomBackendDockerfile(!customBackendDockerfile);
    } else {
      setCustomDBDockerfile(!customDBDockerfile);
    }

    let docker = formValues.docker;

    const newValue = !docker[name];

    docker = { ...docker, [name]: newValue };

    setFormValues({
      ...formValues,
      docker: docker,
    });
    console.log(formValues.docker)
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
                setDockerizeBackend(!dockerizeBackend);

                handleCheckboxChange(e);
              }}
              checked={formValues.docker.dockerizeBackend}
            />
          }
          label="Dockerize backend ?"
        />
      </Grid>
      {dockerizeBackend && (
        <Grid>
          <RadioGroup
            row
            aria-labelledby="demo-radio-buttons-group-label"
            defaultValue={customBackendDockerfile ? "custom" : "default"}
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
      {dockerizeBackend && customBackendDockerfile && (
        <Grid item style={styles.formItem}>
          <FormControlLabel
            label="Build Only"
            control={
              <Checkbox
                name="buildOnly"
                color="success"
                onChange={(e) => {
                  setBuildOnly(!buildOnly);
                  handleCheckboxChange(e);
                }}
                checked={formValues.docker.dockerBackend.buildOnly}
              />
            }
            labelPlacement="start"
          />
        </Grid>
      )}
      {dockerizeBackend &&
        customBackendDockerfile &&
        backendDockerFileArguments.map((arg) => {
          return getFormControl(arg, "dockerBackend");
        })}

      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={
            <Checkbox
              name="dockerizeDB"
              color="success"
              onChange={(e) => {
                setDockerizeDB(!dockerizeDB);
                handleCheckboxChange(e);
              }}
              checked={formValues.docker.dockerizeDB}
            />
          }
          label="Dockerize database ?"
        />
      </Grid>
      {dockerizeDB && (
        <Grid>
          <RadioGroup
            row
            aria-labelledby="demo-radio-buttons-group-label"
            defaultValue={customDBDockerfile ? "custom" : "default"}
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
      {dockerizeDB &&
        customDBDockerfile &&
        databaseDockerFileArguments.map((arg) => {
          return getFormControl(arg, "dockerDB");
        })}
    </Box>
  );
};

export default DockerForm;
