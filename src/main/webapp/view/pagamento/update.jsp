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
        <title> Pagamento: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição do pagamento <c:out value="${pagamento.data_pagamento}"/></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/pagamento/update"
                enctype="multipart/form-data"
                method="POST">
                
                <input type="hidden" name="data_pagamento" value="${pagamento.data_pagamento}">
                <input type="hidden" name="num_placa_carro" value="${pagamento.num_placa_carro}">
                <input type="hidden" name="cpf_locador" value="${pagamento.cpf_locador}">
                <input type="hidden" name="cpf_locatario" value="${pagamento.cpf_locatario}">
                
                <div class="form-group">
                    <label class="control-label" for="valor">Valor</label>
                    <input id="valor" class="form-control" type="text" name="valor" value="${pagamento.valor}" data-value="${pagamento.valor}" required autofocus/>

                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="data-entrega" class="control-label">Data de entrega</label>
                    <input id="data-entrega" class="form-control datepicker" type="date" name="data_entrega"
                           placeholder="dd/mm/yyyy" value="${pagamento.data_entrega}"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>
                           
                <div class="form-group">
                    <label for="data-devolucao" class="control-label">Data de devolucao</label>
                    <input id="data-devolucao" class="form-control datepicker" type="date" name="data_devolucao"
                           placeholder="dd/mm/yyyy" value="${pagamento.data_devolucao}"
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