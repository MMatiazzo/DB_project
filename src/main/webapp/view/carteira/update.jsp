<%-- 
    Document   : update
    Created on : 12 de dez. de 2020, 11:28:59
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Carteira atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição da carteira</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/carteira/update"
                enctype="multipart/form-data"
                method="POST">
                

                <input type="hidden" name="cpf" value="${sessionScope.usuario.cpf}">

                <div class="form-group">
                    <label for="carteira-valor" class="control-label">Valor a ser creditado</label>
                    <input id="carteira-valor" class="form-control" type="number" name="valor" required/>
                </div>
                
                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/review.js"></script>
    </body>
</html>