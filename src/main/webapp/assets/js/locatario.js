

function deleteLocatario(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_locatario').attr('href', $(this).data('href'));
    $('.modal_excluir_locatario').modal();
}

function deleteLocatarios(e) {
    e.preventDefault();
    $('.form_excluir_locatarios').submit();
}

function readLocatario(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var locatario = JSON.parse(data);
        var comp_renda = 'default_avatar.png';
        var $modal = $('.modal-visualizar-locatario');

        $modal.find('.p_cpf').html('<strong>CPF: </strong>' + locatario.cpf_pessoa);
        $modal.find(".p_habilitacao").html('<strong>Habilitacao: </strong>' + locatario.habilitacao);
        $modal.find(".p_endereco").html('<strong>Endereco: </strong>' + locatario.endereco);
        if (locatario.comp_renda) {
            comp_renda = locatario.comp_renda;
        }
        $modal.find('.locatario-img').prop('src', $.url("//img/" + comp_renda));
        
        $modal.modal();
    });
}

$(document).ready(function () {
    $(document).on('click', '.link_excluir_locatario', deleteLocatario);
    $(document).on('click', '.button_confirmacao_excluir_locatarios', deleteLocatarios);
    $(document).on('click', '.link_visualizar_locatario', readLocatario);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});