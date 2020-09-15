/*
 * Checks whether the password and password-confirm fields match
 */

function deletePagamento(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_pagamento').attr('href', $(this).data('href'));
    $('.modal_excluir_pagamento').modal();
}

function deletePagamentos(e) {
    e.preventDefault();
    $('.form_excluir_pagamentos').submit();
}

function readPagamento(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var pagamento = JSON.parse(data);
        var $modal = $('.modal-visualizar-pagamento');

        $modal.find('.p_num_placa_carro').html('<strong>Placa: </strong>' + pagamento.num_placa_carro);
        $modal.find(".p_data_pagamento").html('<strong>Data Pagamento: </strong>' + pagamento.data_pagamento);
        $modal.find('.p_cpf_locador').html('<strong>CPF Locador: </strong>' + pagamento.cpf_locador);
        $modal.find('.p_cpf_locatario').html('<strong>CPF Locatario: </strong>' + pagamento.cpf_locatario);
        $modal.find('.p_valor').html('<strong>Valor: </strong>' + pagamento.valor);
        $modal.find('.p_data_entrega').html('<strong>Data entrega: </strong>' + pagamento.data_entrega);
        $modal.find('.p_data_devolucao').html('<strong>Data Devolucao: </strong>' + pagamento.data_devolucao);
        
        $modal.modal();
    });
}

$(document).ready(function () {
    $(document).on('click', '.link_excluir_pagamento', deletePagamento);
    $(document).on('click', '.button_confirmacao_excluir_pagamentos', deletePagamentos);
    $(document).on('click', '.link_visualizar_pagamento', readPagamento);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});