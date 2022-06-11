import axios from "axios";
import configData from "../config.json";
import {getCookie, goto} from "../utils/utils";

const ENDPOINT = configData.SERVER_URL+"/github";
const CONNECT_ENDPOINT = ENDPOINT+"/connect";

export function connectToGithub(event, setSuccessful, setError,next) {
    console.log(event.currentTarget)
    const data = new FormData(event.currentTarget);
    const dataToSend = {
        username:data.get("username"),
        personalAccessToken:data.get("token")
    }

    axios.post(`${CONNECT_ENDPOINT}`, dataToSend, {
        headers: {
            'Authorization':getCookie('Authorization'),
            'Content-Type': 'application/json',
        }
    }).then(
        (res) => {
            setSuccessful(true);
            console.log(res.data);
            
            if(next==="create-project"){
                setTimeout(()=>{goto("project/create");},500);
            }else{
                setTimeout(()=>{goto("/");},500);
            }
        }
        ,
        (err) => {
            console.error(err);
            setError(err);
        }
    );

}