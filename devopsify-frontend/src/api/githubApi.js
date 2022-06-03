import axios from "axios";
import configData from "../config.json";
import {getCookie, goto, reload} from "../utils/utils";

const ENDPOINT = configData.SERVER_URL+"/github";
const CONNECT_ENDPOINT = ENDPOINT+"/connect";
const CREATE_REPOSITORY_ENDPOINT = ENDPOINT+"/create/repository";

export function connectToGithub(event, setSuccessful) {
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
            goto("/");
        }
        ,
        (err) => {
            //TODO : handle error with customized stuff
            alert("erreur lors de l'authentification, veuillez reentrer vos données, en cas de besoin contacter l'admin");
            console.error(err);
        }
    );

}
export function createNewProject(formValues) {

    console.log(formValues)


    axios.post(`${CREATE_REPOSITORY_ENDPOINT}`, formValues
        , {
            headers: {
                "Authorization": getCookie('Authorization'),
            }
        }
    ).then(
        (res) => {

            // setSuccessful(true);
            console.log(res);
            reload()

        }
        ,
        (err) => {
            alert(err.response.data.error);
            console.error(err);
        }
    );
}
