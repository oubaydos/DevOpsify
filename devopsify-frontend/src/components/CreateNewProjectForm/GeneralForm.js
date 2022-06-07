import * as React from "react";
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
    autoInit: true,
    private_: true
};

const GeneralForm = ({handleInputChange,handleCheckboxChange}) => {

    return (
            <Grid container direction="column" alignItems="center"
                  justify="center" spacing={0}>
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
                    />
                </Grid>
                <Grid item style={styles.formItem}>
                    <TextField
                        name="description"
                        style={styles.labeled}
                        multiline
                        maxRows={6}
                        id="create-new-project-description"
                        defaultValue={defaultValues.description}
                        color="secondary"
                        size="small"
                        label={"project description"}
                        onChange={handleInputChange}
                    />
                </Grid>
            </Grid>
    );
};

export default GeneralForm;
