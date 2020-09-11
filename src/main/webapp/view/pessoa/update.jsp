<%-- 
    Document   : update
    Created on : Sep. 2, 2020, 10:28:54 a.m.
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>--%>
<%--<session:my_user context="${pageContext.servletContext.contextPath}"/>--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title> Pessoa: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição da pessoa <c:out value="${pessoa.nome}"/></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/pessoa/update"
                enctype="multipart/form-data"
                method="POST">
                
                <input type="hidden" name="cpf" value="${pessoa.cpf}">
                
                <div class="form-group">
                    <label class="control-label" for="pessoa-login">Login</label>
                    <input id="pessoa-login" class="form-control" type="text" name="login" value="${pessoa.login}" data-value="${pessoa.login}" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <div class="form-group">
                    <label class="control-label">Senha</label>
                    <input class="form-control password-input"
                           type="password" name="senha"
                           pattern=".{4,}" title="Pelo menos 4 caracteres."/>
                </div>

                <div class="form-group pwd-confirm">
                    <label class="control-label">Confirmar senha</label>
                    <input class="form-control password-confirm"
                           type="password" name="senha-confirmacao"
                           pattern=".{4,}" title="Pelo menos 4 caracteres."/>
                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="pessoa-nome" class="control-label">Nome</label>
                    <input id="pessoa-nome" class="form-control" type="text" name="nome" value="${pessoa.nome}" required/>
                </div>


                <div class="form-group">
                    <label for="pessoa-nasc" class="control-label">Data de nascimento</label>
                    <input id="pessoa-nasc" class="form-control datepicker" type="date" name="nascimento"
                           placeholder="dd/mm/yyyy" value="${pessoa.nascimento}"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>

                <div class="form-group">
                    <label for="pessoa-avatar">Foto do perfil</label>
                    <input type="file"
                           class="form-control" id="pessoa-avatar"
                           name="avatar"
                           accept="image/*"/>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/pessoa.js"></script>
    </body>
</html>