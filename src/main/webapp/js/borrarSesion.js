/**
 * 
 */

console.log("Hola :)");
window.addEventListener("load", init);

function init() {
    //var form = document.getElementById("formModificarSesion");

    document.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro de que desea borrar la sesión?")){
            evnt.preventDefault();
        }
    }, false);
}