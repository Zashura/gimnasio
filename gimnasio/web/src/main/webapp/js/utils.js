function mostrarConfirmacion(mensaje) {
	return confirm(mensaje);
}

function mayusculas(obj) {
	obj.value = obj.value.toUpperCase();
}

function abrirURL(url, target, width, height) {
	var left = (screen.width / 2)-(width / 2);
	var top = (screen.height / 2)-(height / 2);
	var opciones = "width=" + width + ",height=" + height + ", top=" + top + ", left=" + left;
	window.open(url, target, opciones);
}

function soloNumeros(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
       return false;
    return true;	
}

function soloLetras(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) && charCode != 32) {
       return false;
    }
    return true;
}

function validarInicial(obj, txt) {
	var text = obj.value; 
	if(text.length < txt.length) {
		return;
	}
	if(text.substring(0, txt.length) == txt) {
		return;
	} else {
		obj.value = txt + obj.value;
		return;
	}
}

function validateQty(event) {
    var key = window.event ? event.keyCode : event.which;
	if (event.keyCode == 8 || event.keyCode == 46
	 || event.keyCode == 37 || event.keyCode == 39) {
	    return true;
	}
	else if ( key < 48 || key > 57 ) {
	    return false;
	}
	else return true;
	}
    