/*
 * Checks whether the password and password-confirm fields match
 */
$(document).on('focusout', '.password-input,.password-confirm', function(e) {
    var $form = $(this).closest("form");
    var $password = $form.find(".password-input");
    var $passwordConfirm = $form.find(".password-confirm");

    if ($password.val().trim() == '') {
        return false;
    }

    if ($password.val() !== $passwordConfirm.val()) {
        $passwordConfirm.closest('.form-group').addClass('has-error');
        $password.closest('.form-group').addClass('has-error');
        $passwordConfirm.next('p.help-block').html('<strong>Erro</strong>: as senhas não coincidem!');
        $form.find("button,input[type='submit']").prop('disabled', true);
    } else {
        $passwordConfirm.closest('.form-group').removeClass('has-error').addClass('has-success');
        $password.closest('.form-group').removeClass('has-error').addClass('has-success');
        $passwordConfirm.next('p.help-block').html('');
        $form.find("button,input[type='submit']").prop('disabled', false);
    }
});

function deletePessoa(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_pessoa').attr('href', $(this).data('href'));
    $('.modal_excluir_pessoa').modal();
}

function deletePessoas(e) {
    e.preventDefault();
    $('.form_excluir_pessoas').submit();
}

function readPessoa(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var pessoa = JSON.parse(data);
        var avatar = 'default_avatar.png';
        var $modal = $('.modal-visualizar-pessoa');

        $modal.find('.p_cpf').html('<strong>CPF: </strong>' + pessoa.cpf);
        $modal.find(".p_login").html('<strong>Login: </strong>' + pessoa.login);
        $modal.find('.p_nome').html('<strong>Nome: </strong>' + pessoa.nome);
        $modal.find('.p_nascimento').html('<strong>Data de nascimento: </strong>' + pessoa.nascimento);
        if (pessoa.avatar) {
            avatar = pessoa.avatar;
        }
        $modal.find('.pessoa-img').prop('src', $.url("//img/" + avatar));
        
        $modal.modal();
    });
}

$(document).ready(function () {
    $(document).on('click', '.link_excluir_pessoa', deletePessoa);
    $(document).on('click', '.button_confirmacao_excluir_pessoas', deletePessoas);
    $(document).on('click', '.link_visualizar_pessoa', readPessoa);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});