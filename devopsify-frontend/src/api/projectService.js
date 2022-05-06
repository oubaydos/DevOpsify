import axios from "axios";
import configData from "../config.json";

const endpoint = configData.SERVER_URL+"/project";

export function createNewProject(event) {
    const data = new FormData(event.currentTarget);
    for (let i of data.entries())
        console.log(i);
    console.log(data)


    axios.post(`${endpoint}`, data
        , {
            headers: {
                "Authorization": `${localStorage.getItem("currentUser")}`
            }
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