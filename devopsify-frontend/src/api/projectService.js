import axios from "axios";
import configData from "../config.json";

const endpoint = configData.SERVER_URL+"/project";

export function createNewProject(formValues) {

    console.log(formValues)


    axios.post(`${endpoint}`, formValues
        , {
            // headers: {
            //     "Authorization": `${localStorage.getItem("currentUser")}`
            // }
        }
    ).then(
        (res) => {

            // setSuccessful(true);
            console.log(res);
        }
        ,
        (err) => {
            alert(err.response.data.error);
            console.error(err);
        }
    );
}

export function listProjects(setProjects,projects){

    axios.get(`${endpoint}`
        , {
            // headers: {
            //     "Authorization": `${localStorage.getItem("currentUser")}`
            // }
        }
    ).then(
        (res) => {
            console.log(res.data)
            setProjects(res.data);
        }
    );
}