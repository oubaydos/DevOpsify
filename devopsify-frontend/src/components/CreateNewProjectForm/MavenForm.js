import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  FormControlLabel,
  MenuItem,
  Select,
} from "@mui/material";

import { handleFormInputChange,handleFormCheckBoxChange } from "../../utils/form";

const MavenForm = ({
  archetypes,
  formValues,
  setFormValues,
  styles,
}) => {

  const handleMavenArchetypeChange = (e) => {
    //TODO: this needs to be changed in the backend ; send only the archtype id
    //TODO: fix archtype returns to default value when you get back to the form
    const { value } = e.target;
    const archetypeGroupId = archetypes[value].groupId;
    const archetypeArtifactId = archetypes[value].artifactId;
    setMaven({
      ...maven,
      archetypeGroupId: archetypeGroupId,
      archetypeArtifactId: archetypeArtifactId,
    });
  };

  const [maven,setMaven] = React.useState(formValues.maven);

  React.useEffect(() => {
    setFormValues({ ...formValues, maven: maven });
  }, [maven]);

  return (
    <Box>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Maven project template: "
          control={
            <Select
              defaultValue={0}
              size="small"
              labelId="maven-archetype-select-label"
              id="maven-archetype-select"
              style={{ width: "300px" }}
              sx={{ mx: 2 }}
              name="maven-archetype"
              required
              onChange={handleMavenArchetypeChange}
            >
              {archetypes.map((v, index) => (
                <MenuItem value={index}>{v.description}</MenuItem>
              ))}
            </Select>
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Project group id: "
          control={
            <TextField
              name="groupId"
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              label={"groupId"}
              onChange={(e)=>handleFormInputChange(e,maven,setMaven)}
              value={maven.groupId}
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Project artifact id: "
          control={
            <TextField
              name="artifactId"
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              label={"artifactId"}
              onChange={(e)=>handleFormInputChange(e,maven,setMaven)}
              value={maven.artifactId}
            />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="Version "
          control={
            <TextField
              name="version"
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              label={"version"}
              onChange={(e)=>handleFormInputChange(e,maven,setMaven)}
              value={maven.version}
            />
          }
          labelPlacement="start"
        />
      </Grid>
    </Box>
  );
};

export default MavenForm;
