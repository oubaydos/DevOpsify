import * as React from "react";
import { TextField, Grid, Box, FormControlLabel } from "@mui/material";
import { handleFormInputChange } from "../../utils/form";

const GeneralForm = ({ formValues, setFormValues, styles }) => {
  const [general, setGeneral] = React.useState(formValues.general);

  React.useEffect(()=>{
    setFormValues({...formValues,general:general})
  },[general])

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
              onChange={(e) => {
                handleFormInputChange(e, general, setGeneral);
              }}
              value={general.name}
              onMouseEnter={()=>console.log(formValues.general.name)}
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
              onChange={(e) => handleFormInputChange(e, general, setGeneral)}
              value={general.description}
            />
          }
          labelPlacement="start"
        />
      </Grid>
    </Box>
  );
};

export default GeneralForm;
