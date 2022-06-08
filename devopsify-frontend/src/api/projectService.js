import axios from "axios";
import configData from "../config.json";
import {getCookie, reload} from "../utils/utils"

const endpoint = configData.SERVER_URL+"/project";
const CREATE_PROJECT_ENDPOINT = endpoint + "/init";


export function listProjects(setProjects){

    axios.get(`${endpoint}`
        , {
            headers: {
                "Authorization": getCookie('Authorization'),
            }
        }
    ).then(
        (res) => {
            setProjects(res.data);
        }
    );
}

export function createNewProject(formValues,setSuccess,setError) {

    console.log(formValues)


    axios.post(`${CREATE_PROJECT_ENDPOINT}`, formValues
        , {
            headers: {
                "Authorization": getCookie('Authorization'),
            }
        }
    ).then(
        (res) => {
            console.log(res);
            setSuccess(true);
            setTimeout(reload, 1000);
        }
        ,
        (err) => {
            console.error(err);
            setError(err);
        }
    );
}
