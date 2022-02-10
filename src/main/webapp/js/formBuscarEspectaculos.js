/**
 * 
 */
window.addEventListener("load", init);

function init() {
    var buscar = document.getElementById("titulo");

	var form = document.getElementById("formBuscarEspectaculos");
    
	form.addEventListener("submit", function (evnt) {	//Ejecutar la función cuando se pulse el submit
    
    	if( buscar.checked )
    	{
			var titulo = document.getElementById("clave");
		
			var message = document.getElementById("message");

			if( titulo.value == "" ) 
			{
				evnt.preventDefault();//Evito que el el formulario se ejecute
				message.innerHTML="Introduzca un título";
			}
		}
    }, false);
}