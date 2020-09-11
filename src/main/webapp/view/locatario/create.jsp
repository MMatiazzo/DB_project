<%-- 
    Document   : create
    Created on : Sep. 1, 2020, 11:27:40 a.m.
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>Locatario: cadastro</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Inserção de um nova Locatario</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/locatario/create"
                enctype="multipart/form-data"
                method="POST">
                
                <div class="form-group">
                    <label for="locatario-nome" class="control-label">CPF</label>
                    <input id="locatario-nome" class="form-control" type="text" name="cpf_pessoa" required/>
                </div>

                <div class="form-group">
                    <label class="control-label" for="locatario-habilitacao">Habilitacao</label>
                    <input id="locatario-habilitacao" class="form-control" type="text" name="habilitacao" required autofocus/>

                    <p class="help-block"></p>
                </div>
                
                <div class="form-group">
                    <label class="control-label" for="locatario-endereco">Endereco</label>
                    <input id="locatario-endereco" class="form-control" type="text" name="endereco" required autofocus/>

                    <p class="help-block"></p>
                </div>
                
                <div class="form-group">
                    <label for="locatario-comp_renda">Comprovante de Renda</label>
                    <input type="file"
                           class="form-control" id="locatario-comp-residencia"
                           name="comp_renda"
                           accept="image/*"/>
                </div>


                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/locatario.js"></script>
    </body>
</html>