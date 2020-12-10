<%-- 
    Document   : update
    Created on : Sep. 10, 2020, 4:58:45 p.m.
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
        <title>[BD 2020] Carros: atualização</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Edição do carro <c:out value="${carro.modelo}"/></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/car/update"
                enctype="multipart/form-data"
                method="POST">
                

                <input type="hidden" name="placa" value="${carro.placa}">


                <div class="form-group">
                    <label for="carro-modelo" class="control-label">Modelo</label>
                    <input id="carro-modelo" class="form-control" type="text" name="modelo" value="${carro.modelo}" required/>
                </div>
                
                 <div class="form-group">
                    <label for="carro-tipo" class="control-label">Tipo</label>
                    <input id="carro-tipo" class="form-control" type="text" name="tipo" value="${carro.tipo}" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-abs" class="control-label">ABS</label>
                    <input id="carro-abs" class="form-control" type="checkbox" name="abss" checkbox="${carro.abss}"/>
                </div>
                
                <div class="form-group">
                    <label for="carro-ar-condicionado" class="control-label">Ar Condicionado</label>
                    <input id="carro-ar-condicionado" class="form-control" type="checkbox" name="ar_condicionado" checked="${carro.ar_condicionado}"/>
                </div>
                
                <div class="form-group">
                    <label for="carro-num-lugares" class="control-label">Numero de lugares</label>
                    <input id="carro-num-lugares" class="form-control" type="text" name="num_lugares" value="${carro.num_lugares}" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-descricao" class="control-label">Descrição</label>
                    <input id="carro-descricao" class="form-control" type="text" name="descricao" value="${carro.descricao}" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-disponibilidade" class="control-label">Disponibilidade</label>
                    <input id="carro-disponibilidade" class="form-control" type="checkbox" name="disponibilidade" checked="${carro.disponibilidade}"/>
                </div>
                
                
                <div class="form-group">
                    <label for="carro-preco" class="control-label">Preco</label>
                    <input id="carro-preco" class="form-control" type="text" name="preco" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-ano" class="control-label">Ano</label>
                    <input id="carro-ano" class="form-control" type="text" name="ano" required/>
                </div>
                
                <div class="form-group">
                    <input type="hidden"  id="carro-cpf-locador" class="form-control" type="text" name="cpf_locador" value="${carro.cpf_locador}" required/>
                </div>



                <div class="form-group">
                    <label for="carro-avatar">Foto do carro</label>
                    <input type="file"
                           class="form-control" id="carro-avatar"
                           name="avatar"
                           accept="image/*"/>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/js/car.js"></script>
    </body>
</html>