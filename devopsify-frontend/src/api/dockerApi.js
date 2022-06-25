import axios from "axios";
import configData from "../config.json";
import { getCookie, goto } from "../utils/utils";

const ENDPOINT = configData.SERVER_URL + "/docker";

export const getDockerfileDefaultValues = (docker, setDocker) => {
  axios
    .get(`${ENDPOINT}/default-values`, {
      headers: {
        Authorization: getCookie("Authorization"),
      },
    })
    .then((res) => {
      const backend = res.data.backend;
      const db = res.data.db;
      setDocker({
        ...docker,
        dockerBackend: {
          baseBuildImageName: backend["base-image-name"],
          baseBuildImageVersion: backend["image-version"],
          baseBuildJdkType: backend["jdk-type"],
          jdkImageName: backend["jdk-image-name"],
          jdkVersion: backend["jdk-version"],
          jdkBaseOsName: backend["jdk-base-os-name"],
          workdir: backend["workdir"],
          port: backend["exposed-port"],
          jarName: backend["output-jar-name"],
          buildOnly: false,
        },
        dockerDB: {
          imageName: db["db-name"],
          imageVersion: db["db-version"],
          imageBaseOS: db["db-os"],
          dbInitQueriesFilename: db["db-init-queries-filename"],
        },
      });
    });
};
