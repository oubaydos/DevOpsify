import * as React from "react";
import { TextField, Grid ,Box} from "@mui/material";

const NexusForm = ({
    handleInputChange,
    handleCheckboxChange,
    formValues,
    styles,
  }
) => {
  return (
    <Box>
      <Grid item style={styles.formItem}>
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
      </Grid>
      <Grid item style={styles.formItem}>
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
      </Grid>
    </Box>
  );
};

export default NexusForm;
