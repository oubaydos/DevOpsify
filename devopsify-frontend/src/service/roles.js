import {notNull} from "../utils/utils";

// TODO : update to use cookies instead of localStorage
function isContributor() {
    return localStorage.getItem("isContributor") === "true" 
        && notNull(localStorage.getItem("currentUser"))
}


function isAdmin() {
    return localStorage.getItem("isAdmin") === "true" 
        && notNull(localStorage.getItem("currentUser"));
}

function isGuest() {
    return !notNull(localStorage.getItem("currentUser"));
}


export {isContributor, isGuest, isAdmin}