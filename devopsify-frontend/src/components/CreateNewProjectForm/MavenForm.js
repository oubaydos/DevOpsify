import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  FormControlLabel,
  MenuItem,
  Select,
} from "@mui/material";

const MavenForm = ({
  handleInputChange,
  handleMavenArchetypeChange,
  archetypes,
  formValues,
  styles,
}) => {
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
              onChange={handleInputChange}
              value={formValues.maven.groupId}
              defaultValue={"com.example"}
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
              name="artifact id"
              style={styles.labeled}
              required
              color="secondary"
              size="small"
              label={"artifactId"}
              onChange={handleInputChange}
              value={formValues.maven.artifactId}
              defaultValue={"example"}
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
              onChange={handleInputChange}
              value={formValues.maven.version}
              defaultValue={"0.0.1-SNAPSHOT"}
            />
          }
          labelPlacement="start"
        />
      </Grid>
    </Box>
  );
};

export default MavenForm;
