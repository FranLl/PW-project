/**
 * Show the range value
 */

windows.addEventListener("load",updateValue);

function updateValue(){
	var elInput = document.getElementById('puntuacion');
	if (elInput) {
		let p_value = document.getElementById('value');
		if (p_value) {
		    p_value.textContent = elInput.value;
		}
	}
}
