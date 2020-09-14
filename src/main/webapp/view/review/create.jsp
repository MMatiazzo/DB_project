<%-- 
    Document   : index
    Created on : 14 de set. de 2020, 10:54:56
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Reviews cadastro</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Inserção de uma nova review</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/review/create"
                enctype="multipart/form-data"
                method="POST">

                <div class="form-group">
                    <label class="control-label" for="review-num-placa-carro">Placa do carro</label>
                    <input id="review-num-placa-carro" class="form-control" type="text" name="num_placa_carro" required autofocus/>

                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="review-cpf-locador" class="control-label">CPF do Locador</label>
                    <input id="review-cpf-locador" class="form-control" type="text" name="cpf_locador" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-cpf-locatario" class="control-label">CPF do Locatario</label>
                    <input id="review-cpf-locatario" class="form-control" type="text" name="cpf_locatario" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-descricao" class="control-label">Descricao</label>
                    <input id="review-descricao" class="form-control" type="text" name="descricao" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-nota" class="control-label">Nota</label>
                    <input id="review-nota" class="form-control" type="text" name="nota" required/>
                </div>
                
                <div class="form-group">
                    <label for="review-data-review" class="control-label">Data da review</label>
                    <input id="review-data-review" class="form-control datepicker" type="date" name="data_review"
                           placeholder="dd/mm/yyyy"
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