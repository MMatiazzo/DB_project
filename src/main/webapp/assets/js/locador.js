
function deleteLocador(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_locador').attr('href', $(this).data('href'));
    $('.modal_excluir_locador').modal();
}

function deleteLocadores(e) {
    e.preventDefault();
    $('.form_excluir_locadores').submit();
}

function readLocador(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var locador = JSON.parse(data);
//        var avatar = 'default_avatar.png';
        var $modal = $('.modal-visualizar-locador');

        $modal.find('.p_cpf_pessoa').html('<strong>CPF: </strong>' + locador.cpf_pessoa);
        $modal.find(".p_doc_carro").html('<strong>DOC. CARRO: </strong>' + locador.doc_carro);
//        if (carro.avatar) {
//            avatar = carro.avatar;
//        }
//        $modal.find('.carro-img').prop('src', $.url("//img/" + avatar));
        
        $modal.modal();
    });
}



$(document).ready(function () {
    $(document).on('click', '.link_excluir_locador', deleteLocador);
    $(document).on('click', '.button_confirmacao_excluir_locadores', deleteLocadores);
    $(document).on('click', '.link_visualizar_locador', readLocador);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});