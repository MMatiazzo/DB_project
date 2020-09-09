<%-- 
    Document   : welcome
    Created on : Sep. 8, 2020, 10:24:11 a.m.
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"  %>
        <title>[BD 2020] Início</title>
    </head>
    <body>
        
        <div class="container">
            
            <div class="jumbotron">
                <h1>Bem-vindo,
                <c:out value="${usuario.nome}"/>!</h1>
                <p>Este é um exemplo de cadastro de usuários para o trabalho da disciplina Bancos de Dados A.</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/user">
                        Cadastro de usuários
                    </a>                 
                    <a class="btn btn-default"
                       href="${pageContext.servletContext.contextPath}/logout"
                       data-toggle="tooltip"
                       data-original-title="Logout">
                        <i class="fa fa-sign-out"></i>
                    </a>
                </p>
            </div>
        </div>

        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/user.js"></script>        
    </body>
</html>