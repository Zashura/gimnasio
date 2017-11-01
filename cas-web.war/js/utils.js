function ocultarModal(modal, validacion) {
    if(!validacion.validationFailed) {
        modal.hide();
    }
    return false;
}
