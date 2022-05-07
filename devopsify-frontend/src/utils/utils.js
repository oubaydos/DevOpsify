export function goto(url){
    window.location.href = url;
}
export function notNull(str){
    return str !== undefined && str != null;
}
export function logOut() {
    localStorage.removeItem("currentUser");
    localStorage.removeItem("isContributor");
    localStorage.removeItem("isAdmin");
    goto("/");
}
