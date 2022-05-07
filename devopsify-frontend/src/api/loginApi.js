import axios from "axios";
import configData from "../config.json";
import {goto} from "../utils/utils";
import { setCookie, withCookies, Cookies } from "react-cookie";

const endpoint = configData.SERVER_URL+"/login";

export function login(event, setSuccessful) {
    console.log(event.currentTarget)
    const data = new FormData(event.currentTarget);
    // eslint-disable-next-line no-console
    console.log({
        username: data.get('email'),
        password: data.get('password'),
    });
    const dataToSend = {
        username: data.get('email'),
        password: data.get('password')
    }

    axios.post(`${endpoint}`, dataToSend, {
        headers: {
            'Content-Type': 'application/json',
        }
    }).then(
        (res) => {
            // TODO : setCookies
            console.log(res.headers["Authorization"])
            setCookie("Authorization",res.headers["Authorization"])
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