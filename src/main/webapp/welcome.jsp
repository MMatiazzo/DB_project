<%-- 
    Document   : newjsp
    Created on : 10 de set. de 2020, 13:30:21
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>
<session:my_user context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        .myDivElement{
            text-align:center;
        }
        .myDivElement ul li{
            display:inline;
        }
    </style>
    </head>
    <body>
        <div class="myDivElement">
        <nav aria-label="..." display="inline" text-align="center">
            <ul class="pagination pagination-lg">
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/pessoa">Pessoa</a></li>
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/car">Carro</a></li>
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/locador">Locador</a></li>
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/locatario">Locatario</a></li>
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/pagamento">Pagamento</a></li>
                <li class="page-item"><a class="page-link" href="http://localhost:8080/bdapp/review">Review</a></li>
                
            </ul>
        </nav>
        </div>
    </body>
</html>
