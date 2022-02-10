/**
 * 
 */
 window.addEventListener("load", init);

function init() {
	var form = document.getElementById("formBorrarCritica");
	
 	form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro que quiere borrar la crítica?")){
            evnt.preventDefault();
        }
    }, false);
}