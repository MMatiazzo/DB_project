/*
 * Checks whether the password and password-confirm fields match
 */

function deleteCar(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_carro').attr('href', $(this).data('href'));
    $('.modal_excluir_carro').modal();
}

function deleteCars(e) {
    e.preventDefault();
    $('.form_excluir_carros').submit();
}

function readCar(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var carro = JSON.parse(data);
        var avatar = 'default_avatar.png';
        var $modal = $('.modal-visualizar-carro');

        $modal.find('.p_placa').html('<strong>Placa: </strong>' + carro.placa);
        $modal.find(".p_modelo").html('<strong>Modelo: </strong>' + carro.modelo);
        $modal.find('.p_tipo').html('<strong>Tipo: </strong>' + carro.tipo);
        $modal.find('.p_cpf_locador').html('<strong>CPF locador: </strong>' + carro.cpf_locador);
        if (carro.avatar) {
            avatar = carro.avatar;
        }
        $modal.find('.carro-img').prop('src', $.url("//img/" + avatar));
        
        $modal.modal();
    });
}

$(document).ready(function () {
    $(document).on('click', '.link_excluir_carro', deleteCar);
    $(document).on('click', '.button_confirmacao_excluir_carros', deleteCars);
    $(document).on('click', '.link_visualizar_carro', readCar);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});