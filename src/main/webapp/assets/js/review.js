/*
 * Checks whether the password and password-confirm fields match
 */

function deleteReview(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_review').attr('href', $(this).data('href'));
    $('.modal_excluir_review').modal();
}

function deleteReviews(e) {
    e.preventDefault();
    $('.form_excluir_reviews').submit();
}

function readReview(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var review = JSON.parse(data);
        var $modal = $('.modal-visualizar-review');

        $modal.find('.p_num_placa_carro').html('<strong>Placa do carro: </strong>' + review.num_placa_carro);
        $modal.find(".p_cpf_locador").html('<strong>CPF do locador: </strong>' + review.cpf_locador);
        $modal.find('.p_cpf_locatario').html('<strong>CPF do locatario: </strong>' + review.cpf_locatario);
        $modal.find('.p_descricao').html('<strong>Descrição: </strong>' + review.descricao);
        $modal.find('.p_nota').html('<strong>Nota: </strong>' + review.nota);
        $modal.find('.p_data_review').html('<strong>Data: </strong>' + review.data_review);
        $modal.modal();
    });
}

$(document).ready(function () {
    $(document).on('click', '.link_excluir_review', deleteReview);
    $(document).on('click', '.button_confirmacao_excluir_reviews', deleteReviews);
    $(document).on('click', '.link_visualizar_review', readReview);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});