/**
 * 
 */
window.addEventListener("load", init);

const validateEmail = (email) => {
  return email.match(
    /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  );
}

function init() {
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var message = document.getElementById("message");

    var form = document.getElementById("form");

    form.addEventListener("submit", function (evnt) {	//Ejecutar la función cuando se pulse el submit
		if( !validateEmail( email.value ) )
		{
			evnt.preventDefault();//Evito que el el formulario se ejecute
			message.innerHTML="Formato del correo incorrecto";
		}
		else if( password.value.length < 4 ) 
		{
			evnt.preventDefault();//Evito que el el formulario se ejecute
			message.innerHTML="Contraseña demasiado corta";
		}
    }, false);
}