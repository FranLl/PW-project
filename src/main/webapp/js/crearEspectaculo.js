/**
 * 
 */

console.log("Hola :)");
window.addEventListener("load", init);

function init() {
    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    
    var fecha1 = document.getElementById("fecha1");
    var fecha2 = document.getElementById("fecha2");
    var fecha3 = document.getElementById("fecha3");

    fecha1.min = now.toISOString().slice(0,16);
    fecha2.min = now.toISOString().slice(0,16);
    fecha3.min = now.toISOString().slice(0,16);

    fecha1.value = now.toISOString().slice(0,16);
    fecha2.value = now.toISOString().slice(0,16);
    fecha3.value = now.toISOString().slice(0,16);

    var divFecha2 = document.getElementById("divFecha2");
    var divFecha3 = document.getElementById("divFecha3");

    divFecha2.style.display = "none";
    divFecha3.style.display = "none";

    var radioPuntual = document.getElementById("puntual");
    var radioMultiple = document.getElementById("multiple");
    var radioTemporada = document.getElementById("temporada");

    radioPuntual.addEventListener("click", clickHandler);
    radioMultiple.addEventListener("click", clickHandler);
    radioTemporada.addEventListener("click", clickHandler);

    var labelFecha1 = document.getElementById("labelFecha1");
    var labelFecha2 = document.getElementById("labelFecha2");
    var labelFecha3 = document.getElementById("labelFecha3");
    
    var form = document.getElementById("formCrearEspectaculo");

    form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro de que desea crear el evento?")){
            evnt.preventDefault();
        }
    }, false);

    function clickHandler() {
        if(radioPuntual.checked){
            divFecha2.style.display = "none";
            divFecha3.style.display = "none";

            labelFecha1.innerHTML = "Fecha"
        }
        else if (radioMultiple.checked){
            divFecha2.style.display = "block";
            divFecha3.style.display = "block";
            labelFecha1.innerHTML = "Fecha 1"
            labelFecha2.innerHTML = "Fecha 2"
            labelFecha3.innerHTML = "Fecha 3"
        }
        else if (radioTemporada.checked) {
            divFecha2.style.display = "block";
            divFecha3.style.display = "none";

            labelFecha1.innerHTML = "Fecha inicial"
            labelFecha2.innerHTML = "Fecha final"
        }
    };

    
}


