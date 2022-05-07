import {notNull, getCookie} from "../utils/utils";

function isContributor() {
    return getCookie("isContributor") === "true"
        && notNull(getCookie("currentUser"))
}


function isAdmin() {
    return getCookie("isAdmin") === "true"
        && notNull(getCookie("currentUser"));
}

function isGuest() {
    // TODO : shouldn't it be else ? without checking anything
    return !notNull(getCookie("currentUser"));
}


export {isContributor, isGuest, isAdmin}