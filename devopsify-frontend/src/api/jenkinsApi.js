import axios from "axios";
import configData from "../config.json";
import {getCookie, goto} from "../utils/utils";


export function updateJenkinsServer(projectId,server){
    
    const endpoint = configData.SERVER_URL+"/project/"+projectId+"/jenkins";

    console.log(projectId)
    console.log(server)

    axios.post(`${endpoint}`, server, {
        headers: {
            'Authorization':getCookie('Authorization'),
            'Content-Type': 'application/json',
        }
    }).then(
        (res) => {

        }
        ,
        (err) => {
            alert("error");
            console.error(err);
        }
    );
}

export function testConnection(server,setConnectionStatus){

    console.log(server);
    
    const endpoint = configData.SERVER_URL+"/jenkins";

    axios.post(`${endpoint}`, server, {
        headers: {
            'Authorization':getCookie('Authorization'),
            'Content-Type': 'application/json',
        }
    }).then(
        (res) => {
            setConnectionStatus("success")
        }
        ,
        (err) => {
            setConnectionStatus("failed")
        }
    );
}