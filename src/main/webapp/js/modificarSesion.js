/**
 * 
 */

console.log("Hola :)");
window.addEventListener("load", init);

function init() {
    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    
    var fecha = document.getElementById("fecha");
    fecha.min = now.toISOString().slice(0,16);

    var form = document.getElementById("formModificarSesion");

    form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro de que desea modificar la sesión?")){
            evnt.preventDefault();
        }
    }, false);
}