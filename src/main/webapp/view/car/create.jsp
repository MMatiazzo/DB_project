<%-- 
    Document   : create
    Created on : 10 de set. de 2020, 11:03:44
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp" %>
        <title>[BD 2020] Carros: cadastro</title>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center">Inserção de um novo carro</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/car/create"
                enctype="multipart/form-data"
                method="POST">

                <div class="form-group">
                    <label class="control-label" for="carro-placa">Placa</label>
                    <input id="carro-placa" class="form-control" type="text" name="placa" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <div class="form-group">
                    <label for="carro-modelo" class="control-label">Modelo</label>
                    <input id="carro-modelo" class="form-control" type="text" name="modelo" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-tipo" class="control-label">Tipo</label>
                    <input id="carro-tipo" class="form-control" type="text" name="tipo" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-abs" class="control-label">ABS</label>
                    <input id="carro-abs" class="form-control" type="checkbox" name="abss"/>
                </div>
                
                <div class="form-group">
                    <label for="carro-ar-condicionado" class="control-label">Ar Condicionado</label>
                    <input id="carro-ar-condicionado" class="form-control" type="checkbox" name="ar_condicionado"/>
                </div>
                
                <div class="form-group">
                    <label for="carro-num-lugares" class="control-label">Numero de lugares</label>
                    <input id="carro-num-lugares" class="form-control" type="text" name="num_lugares" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-descricao" class="control-label">Descrição</label>
                    <input id="carro-descricao" class="form-control" type="text" name="descricao" required/>
                </div>
                
                <div class="form-group">
                    <label for="carro-disponibilidade" class="control-label">Disponibilidade</label>
                    <input id="carro-disponibilidade" class="form-control" type="checkbox" name="disponibilidade"/>
                </div>
                
                <div class="form-group">
                    <label for="carro-cpf-locador" class="control-label">CPF do Locador</label>
                    <input id="carro-cpf-locador" class="form-control" type="text" name="cpf_locador" required/>
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