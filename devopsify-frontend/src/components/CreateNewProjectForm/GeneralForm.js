import * as React from "react";
import { TextField, Grid ,Box,FormControlLabel} from "@mui/material";

const GeneralForm = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  styles,
}) => {
  return (
    <Box>
      <Grid item style={styles.formItem}>
      <FormControlLabel
          label="Name"
          control={
            <TextField
            name="name"
            style={styles.labeled}
            required
            id="create-new-project-name"
            color="secondary"
            size="small"
            label={"project name"}
            onChange={handleInputChange}
            value={formValues.general.name}
          />
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
      <FormControlLabel
          label="Description "
          control={
            <TextField
            name="description"
            style={styles.labeled}
            multiline
            maxRows={6}
            id="create-new-project-description"
            color="secondary"
            size="small"
            label={"project description"}
            onChange={handleInputChange}
            value={formValues.general.description}
          />
          }
          labelPlacement="start"
        />

      </Grid>
    </Box>
  );
};

export default GeneralForm;
