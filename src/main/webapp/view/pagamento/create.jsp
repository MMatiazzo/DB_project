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
        <title>Pagamento: cadastro</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Inserção de um novo pagamento</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/pagamento/create"
                enctype="multipart/form-data"
                method="POST">
                
                 <div class="form-group">
                    <label for="data_pagamento" class="control-label">Data Pagamento</label>
                    <input id="data-pagamento" class="form-control datepicker" type="date" name="data_pagamento"
                           placeholder="dd/mm/yyyy"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>
                
                <div class="form-group">
                    <label for="num_placa_carro" class="control-label">Numero Placa Carro</label>
                    <input id="num_placa_carro" class="form-control" type="text" name="num_placa_carro" required/>
                </div>

                <div class="form-group">
                    <label class="control-label" for="cpf_locador">CPF Locador</label>
                    <input id="cpf_locador" class="form-control" type="text" name="cpf_locador" required autofocus/>

                    <p class="help-block"></p>
                </div>
                
                <div class="form-group">
                    <label class="control-label" for="cpf_locatario">CPF Locatario</label>
                    <input id="cpf_locatario" class="form-control" type="text" name="cpf_locatario" required autofocus/>

                    <p class="help-block"></p>
                </div>
                
                <div class="form-group">
                    <label class="control-label" for="valor">Valor</label>
                    <input id="valor" class="form-control" type="text" name="valor" required autofocus/>

                    <p class="help-block"></p>
                </div>
                
                <div class="form-group">
                    <label for="data_entrega" class="control-label">Data Entrega</label>
                    <input id="data_entrega" class="form-control datepicker" type="date" name="data_entrega"
                           placeholder="dd/mm/yyyy"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>
                
                <div class="form-group">
                    <label for="data_devolucao" class="control-label">Data Devolucao</label>
                    <input id="data_devolucao" class="form-control datepicker" type="date" name="data_devolucao"
                           placeholder="dd/mm/yyyy"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/pagamento.js"></script>
    </body>
</html>