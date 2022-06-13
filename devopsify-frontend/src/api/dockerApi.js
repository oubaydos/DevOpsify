import axios from "axios";
import configData from "../config.json";
import { getCookie, goto } from "../utils/utils";

const ENDPOINT = configData.SERVER_URL + "/docker";

export const getDockerfileDefaultValues = (formValues, setFormValues) => {
  axios
    .get(`${ENDPOINT}/default-values`, {
      headers: {
        Authorization: getCookie("Authorization"),
      },
    })
    .then((res) => {
      console.log("here");
      console.log(res.data);
      const backend = res.data.backend;
      const db = res.data.db;
      let docker = formValues.docker;
      docker = {
        ...docker,
        dockerBackend: {
          baseBuildImageName: backend["image-name"],
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
      };
      console.log("docker");
      console.log(docker);
      setFormValues({
        ...formValues,
        docker: docker,
      });
    });
};
