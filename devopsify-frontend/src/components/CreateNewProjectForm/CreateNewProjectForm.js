import React, {useState} from "react";
import {createNewProject} from "../../api/githubApi"
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import licenseTemplates from "../../utils/licenseTemplates.json";
import gitIgnoreTemplates from "../../utils/gitIgnoreTemplates.json";
import {
    TextField,
    FormControlLabel,
    Grid,
    Checkbox,
    Button,
} from "@mui/material";
import Error from "../shared/Error";
import Success from "../shared/Success";

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

const CreateNewProjectForm = () => {
    const [formValues, setFormValues] = useState(defaultValues);
    const [successful, setSuccessful] = useState(false);
    const [error, setError] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        createNewProject(formValues, setSuccessful, setError);
        console.log(formValues)
    };

    const handleCheckboxChange = (e) => {

        const {name, checked} = e.target;
        alert(checked)
        setFormValues({
            ...formValues,
            [name]: checked,
        });
    };

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormValues({
            ...formValues,
            [name]: value,
        });
    };


    return (
        <form onSubmit={handleSubmit}>
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
                <Grid item style={styles.formItem}>
                    <FormControlLabel
                        label="License"
                        control={

                            <Select
                                labelId="licenses-select-label"
                                id="licenses-select"
                                style={{minWidth: "150px"}}
                                name={"licenseTemplate"}
                                onChange={handleInputChange}

                            >
                                {licenseTemplates.map((v) => <MenuItem value={v.value}>{v.label}</MenuItem>)}
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
                                labelId="gitignore-select-label"
                                id="gitignore-select"
                                style={{minWidth: "150px"}}
                                name="gitIgnoreTemplate"
                                onChange={handleInputChange}

                            >
                                {gitIgnoreTemplates.map((v) => <MenuItem value={v}>{v}</MenuItem>)}
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
                            />
                        }
                        label="the repository is private"
                    />
                </Grid>
                <Grid item style={styles.formItem}>
                    <Button variant="contained" color="secondary" type="submit">
                        Create
                    </Button>
                </Grid>
                <Grid>
                    {(successful && error === false) && (
                        <Success/>
                    )}{" "}
                </Grid>
                <Grid>
                    {error !== false && (
                        <Error error={error}/>
                    )}{" "}
                </Grid>
            </Grid>
        </form>
    );
};

export default CreateNewProjectForm;
