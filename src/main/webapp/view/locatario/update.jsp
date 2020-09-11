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
        <title> Locatario: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição da Locatario /></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/locatario/update"
                enctype="multipart/form-data"
                method="POST">
                
                <input type="hidden" name="cpf_pessoa" value="${locatario.cpf_pessoa}">
                
                <div class="form-group">
                    <label class="control-label" for="locatario-login">Habilitação</label>
                    <input id="locatario-habilitacao" class="form-control" type="text" name="habilitacao" value="${locatario.habilitacao}" data-value="${locatario.habilitacao}" required autofocus/>

                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="locatario-endereco" class="control-label">Endereco</label>
                    <input id="locatario-endereco" class="form-control" type="text" name="endereco" value="${locatario.endereco}" required/>
                </div>


                <div class="form-group">
                    <label for="locatario-comp_renda">Foto do perfil</label>
                    <input type="file"
                           class="form-control" id="locatario-comp-renda"
                           name="comp_renda"
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