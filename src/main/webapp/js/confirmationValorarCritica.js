/**
 * 
 */
 window.addEventListener("load", init);

function init() {
	var form = document.getElementById("formValorarCritica");
	
 	form.addEventListener("submit", function (evnt) {
        if(!confirm("¿Está seguro que quiere valorar la crítica?")){
            evnt.preventDefault();
        }
    }, false);
}