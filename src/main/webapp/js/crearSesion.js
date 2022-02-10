/**
 * 
 */

console.log("Hola :)");
window.addEventListener("load", init);

function init() {
    var form = document.getElementById("formCrearSesion");
    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    
    var fecha = document.getElementById("fecha");
    fecha.min = now.toISOString().slice(0,16);
    fecha.value = now.toISOString().slice(0,16);

    form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro de que desea crear la sesión?")){
            evnt.preventDefault();
        }
    }, false);
}