import axios from "axios";
import configData from "../config.json";
import {reload} from "../utils/utils"

const endpoint = configData.SERVER_URL+"/project";


export function listProjects(setProjects){

    axios.get(`${endpoint}`
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