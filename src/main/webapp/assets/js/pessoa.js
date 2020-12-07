/*
 * Checks whether the password and password-confirm fields match
 */
$(document).on('focusout', '.password-input,.password-confirm', function(e) {
    var $form = $(this).closest("form");
    var $password = $form.find(".password-input");
    var $passwordConfirm = $form.find(".password-confirm");

    if ($password.val().trim() === '') {
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

$(document).on('focusout', '#pessoa-login', function (e) {
    var $input = $(this);
    if ($("#pessoa-login").val() === $(this).data('value')) {
        var $formGroup = $input.parents(".form-group").first();
        if ($formGroup.hasClass("has-error")) {
            $formGroup.removeClass("has-error");
        }
        $input.next("p").html("");
    }
    else {
        $.post($.url("//pessoa/checkLogin"), { login: $("#pessoa-login").val() }, function(data) {
            var $formGroup = $input.parents(".form-group").first();
            if (data.status === "USADO") {
                if (!$formGroup.hasClass("has-error")) {
                    $formGroup.addClass("has-error");
                }
                $input.next("p").html("O login escolhido existe. Por favor, tente outro.");
            } else {
                if ($formGroup.hasClass("has-error")) {
                    $formGroup.removeClass("has-error");
                }
                $input.next("p").html("");
            }
        });
    }
});



$(document).ready(function () {
    $(document).on('click', '.link_excluir_pessoa', deletePessoa);
    $(document).on('click', '.button_confirmacao_excluir_pessoas', deletePessoas);
    $(document).on('click', '.link_visualizar_pessoa', readPessoa);    
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});