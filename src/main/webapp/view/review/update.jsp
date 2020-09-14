<%-- 
    Document   : update
    Created on : 14 de set. de 2020, 17:52:59
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Review: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição da review</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/review/update"
                enctype="multipart/form-data"
                method="POST">
                

                <input type="hidden" name="num_placa_carro" value="${review.num_placa_carro}">
                <input type="hidden" name="cpf_locador" value="${review.cpf_locador}">
                <input type="hidden" name="cpf_locatario" value="${review.cpf_locatario}">


                <div class="form-group">
                    <label for="review-descricao" class="control-label">Descrição</label>
                    <input id="review-descricao" class="form-control" type="text" name="descricao" value="${review.descricao}" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-nota" class="control-label">Nota</label>
                    <input id="review-nota" class="form-control" type="text" name="nota" value="${review.nota}" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-data-review" class="control-label">Data da review</label>
                    <input id="review-data-review" class="form-control datepicker" type="date" name="data_review"
                           placeholder="dd/mm/yyyy" value="${review.data_review}"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
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