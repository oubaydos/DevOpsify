import axios from "axios";
import configData from "../config.json";
import {goto} from "../utils/utils";


export function login(event,setOpen,setCookie, setSuccessful, setError) {

    const endpoint = configData.SERVER_URL+"/login";


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
            setCookie("Authorization",res.headers["authorization"])
            setOpen(true);
            setSuccessful(true);
            goto("/")
        }
        ,
        (err) => {
            // alert("erreur lors de l'authentification, veuillez reentrer vos données, en cas de besoin contacter l'admin");
            console.error(err);
            alert(err.response.data.exception.message)
            setError(err);
        }
    );

}

export function getAuthenticatedUser(authCookie,setCurrentUser){

    const endpoint = configData.SERVER_URL+"/user";

    if(authCookie===undefined) {
        setCurrentUser({
            role:"GUEST",
            name :""
        })
        return;
    }

    axios.get(`${endpoint}`,  {
        headers: {
            'Authorization': authCookie,
        }
    }).then(
        (res) => {
            setCurrentUser({
                username : res.data["username"],
                role : res.data["role"]
            })
        },
        (err)=>{
            setCurrentUser({
                role:"GUEST",
                name :""
            })

        }
    );
}



