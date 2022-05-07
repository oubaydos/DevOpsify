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
export function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)===' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
