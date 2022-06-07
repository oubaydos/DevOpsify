import axios from "axios";
import configData from "../config.json";
import { getCookie} from "../utils/utils";

const endpoint = configData.SERVER_URL + "/maven";

const getArchetypes = (setArchetypes) => {
  axios
    .get(`${endpoint}/archetype`, {
      headers: {
        Authorization: getCookie("Authorization"),
      },
    })
    .then((res) => {
      setArchetypes(res.data);
    });
};

export default getArchetypes;
