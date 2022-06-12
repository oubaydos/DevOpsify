import * as React from "react";
import {
  TextField,
  Box,
  FormControlLabel,
  Grid,
  Checkbox,
  Button,
} from "@mui/material";

import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import licenseTemplates from "../../utils/licenseTemplates.json";
import gitIgnoreTemplates from "../../utils/gitIgnoreTemplates.json";

const defaultValues = {
  autoInit: true,
  private_: false,
};

const GithubForm = ({
  handleInputChange,
  handleCheckboxChange,
  formValues,
  styles,
}) => {
  return (
    <Box>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="License"
          control={
            <Select
              size="small"
              labelId="licenses-select-label"
              id="licenses-select"
              style={{ width: "300px" }}
              sx={{ mx: 2 }}
              name={"licenseTemplate"}
              onChange={handleInputChange}
            >
              {licenseTemplates.map((v) => (
                <MenuItem value={v.value}>{v.label}</MenuItem>
              ))}
            </Select>
          }
          labelPlacement="start"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          label="gitignore template"
          control={
            <Select
              size="small"
              labelId="gitignore-select-label"
              id="gitignore-select"
              style={{ width: "300px" }}
              sx={{ mx: 2 }}
              name="gitIgnoreTemplate"
              onChange={handleInputChange}
            >
              {gitIgnoreTemplates.map((v) => (
                <MenuItem value={v}>{v}</MenuItem>
              ))}
            </Select>
          }
          labelPlacement="start"
        />
      </Grid>

      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={
            <Checkbox
              name="autoInit"
              defaultChecked={defaultValues.autoInit}
              color="success"
              onChange={handleCheckboxChange}
            />
          }
          label="create a ReadMe"
        />
      </Grid>
      <Grid item style={styles.formItem}>
        <FormControlLabel
          control={
            <Checkbox
              name="private_"
              defaultChecked={defaultValues.private_}
              color="success"
              onChange={handleCheckboxChange}
              disabled={true}
            />
          }
          label="the repository is private"
        />
      </Grid>
    </Box>
  );
};

export default GithubForm;
