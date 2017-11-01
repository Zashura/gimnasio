function mostrarConfirmacion(mensaje) {
	return confirm(mensaje);
}

function abrirURL(url, target, width, height) {
	var left = (screen.width / 2)-(width / 2);
	var top = (screen.height / 2)-(height / 2);
	var opciones = "width=" + width + ",height=" + height + ", top=" + top + ", left=" + left;
	window.open(url, target, opciones);
}