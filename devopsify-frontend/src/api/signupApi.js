import axios from "axios";
import configData from "../config.json";
import {getCookie, goto} from "../utils/utils";

const endpoint = configData.SERVER_URL+"/user/signup";

export function signup(event, setSuccessful) {

    const data = new FormData(event.currentTarget);

    const dataToSend = {
        username: data.get('username'),
        email: data.get('email'),
        password: data.get('password'),
    }
    console.log(dataToSend)
    axios.post(`${endpoint}`, dataToSend, {
        headers: {
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
            //alert("erreur lors de l'authentification, veuillez reentrer vos données, en cas de besoin contacter l'admin");
            console.error(err);
        }
    );

}