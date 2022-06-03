import axios from "axios";
import configData from "../config.json";
import {reload} from "../utils/utils"

const ENDPOINT = configData.SERVER_URL + "/project";
const CLONE_PROJECT_ENDPOINT = ENDPOINT + "/project";


export function cloneProject(data,setSuccess,setError) {

    axios.post(`${CLONE_PROJECT_ENDPOINT}`, data
        , {
            headers: {
                "Authorization": `${localStorage.getItem("currentUser")}`
            }
        }
    ).then(
        (res) => {
            setSuccess(true);
        }
    );
}

export function listProjects(setProjects) {

    axios.get(`${ENDPOINT}`
        , {
            // headers: {
            //     "Authorization": `${localStorage.getItem("currentUser")}`
            // }
        }
    ).then(
        (res) => {
            setProjects(res.data);
        }
    );
}