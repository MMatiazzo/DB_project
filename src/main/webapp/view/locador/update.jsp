<%-- 
    Document   : update
    Created on : 11 de set. de 2020, 15:04:36
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>--%>
<%--<session:my_user context="${pageContext.servletContext.contextPath}"/>--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Locadores: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição do locador <c:out value="${locador.cpf_pessoa}"/></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/locador/update"
                enctype="multipart/form-data"
                method="POST">
                

                <input type="hidden" name="cpf_pessoa" value="${locador.cpf_pessoa}">


                <div class="form-group">
                    <label for="locador-doc-carro" class="control-label">Documento do carro</label>
                    <input id="locador-doc-carro" class="form-control" type="text" name="doc_carro" value="${locador.doc_carro}" required/>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/locador.js"></script>
    </body>
</html>