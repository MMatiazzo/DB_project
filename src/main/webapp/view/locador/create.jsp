<%-- 
    Document   : create
    Created on : 11 de set. de 2020, 15:00:42
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Locadores: cadastro</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Inserção de um novo locador</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/locador/create"
                enctype="multipart/form-data"
                method="POST">

                <div class="form-group">
                    <label class="control-label" for="locador-cpf-pessoa">CPF</label>
                    <input id="locador-cpf-pessoa" class="form-control" type="text" name="cpf_pessoa" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <div class="form-group">
                    <label for="locador-doc-carro" class="control-label">Documento do carro</label>
                    <input id="locador-doc-carro" class="form-control" type="text" name="doc_carro" required/>
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
