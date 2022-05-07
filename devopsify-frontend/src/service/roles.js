import {notNull, getCookie} from "../utils/utils";
import configData from "../config.json";

function isContributor() {
    return getCookie("isContributor") === "true"
        && notNull(getCookie("Authorization"+configData.COOKIE_SUFFIX))
}


function isAdmin() {
    return getCookie("isAdmin") === "true"
        && notNull(getCookie("Authorization"+configData.COOKIE_SUFFIX));
}

function isGuest() {
    // TODO : shouldn't it be else ? without checking anything
    return !notNull(getCookie("Authorization"+configData.COOKIE_SUFFIX));
}


export {isContributor, isGuest, isAdmin}