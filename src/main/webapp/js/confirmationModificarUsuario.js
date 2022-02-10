/**
 * 
 */
 window.addEventListener("load", init);

function init() {
	var form = document.getElementById("formModificarUsuario");
	
 	form.addEventListener("submit", function (evnt) {
		var password = document.getElementById("password");
		var message = document.getElementById("message");

		if( password.value.length > 0 && password.value.length < 4 ) 
		{
			evnt.preventDefault();//Evito que el el formulario se ejecute
			message.innerHTML="Contraseña demasiado corta";
		}
		
        if(!confirm("¿Está seguro que quiere modificar su usuario?")){
            evnt.preventDefault();
        }
    }, false);
}