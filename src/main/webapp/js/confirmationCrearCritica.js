/**
 * 
 */
 window.addEventListener("load", init);

function init() {
	var form = document.getElementById("formCrearCritica");
	
 	form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro que quiere crear una crítica?")){
            evnt.preventDefault();
        }
    }, false);
}