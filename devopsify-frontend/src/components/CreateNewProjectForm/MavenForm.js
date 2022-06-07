import * as React from "react";
import {
  TextField,
  Grid,
  Box,
  FormControlLabel,
  MenuItem,
  Select,
} from "@mui/material";
import getArchetypes from "../../api/mavenApi";

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
              value={0}
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
              value={formValues.general.name}
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
              value={formValues.general.name}
              defaultValue={"example"}
            />
          }
          labelPlacement="start"
        />
      </Grid>
    </Box>
  );
};

export default MavenForm;
