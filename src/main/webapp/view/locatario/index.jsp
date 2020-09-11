<%-- 
    Document   : index
    Created on : Sep. 1, 2020, 10:40:35 a.m.
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>
<session:my_user context="${pageContext.servletContext.contextPath}"/>--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2020] Locatario</title>
    </head>
    <body>

        <div class="container">
            
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/locatario/create">
                    Inserir nova Locatario
                </a>

                <button class="btn btn-lg btn-warning" data-toggle="modal" data-target=".modal_excluir_locatarios">
                    Excluir múltiplos locatarios
                </button>
                <a class="btn btn-default"
                   href="${pageContext.servletContext.contextPath}/logout"
                   data-toggle="tooltip"
                   data-original-title="Logout">
                    <i class="fa fa-sign-out"></i>
                </a>
            </div>

            <form class="form_excluir_lcatarios" action="${pageContext.servletContext.contextPath}/locatario/delete" method="POST">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-2 h4">CPF</th>
                            <th class="col-lg-5 h4">Habilitacao</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                            <th class="col-lg-1 h4 text-center">Excluir?</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="locatario" items="${requestScope.locatarioList}">
                            <tr>
                                <td>
                                    <span class="h4"><c:out value="${locatario.cpf_pessoa}"/></span>
                                </td>
                                <td>
                                    <a class="link_visualizar_locatario" href="#" data-href="${pageContext.servletContext.contextPath}/locatario/read?cpf_pessoa=${locatario.cpf_pessoa}">
                                        <span class="h4"><c:out value="${locatario.habilitacao}"/></span>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-default"
                                       href="${pageContext.servletContext.contextPath}/locatario/update?cpf_pessoa=${locatario.cpf_pessoa}"
                                       data-toggle="tooltip"
                                       data-original-title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_locatario"
                                       href="#"
                                       data-href="${pageContext.servletContext.contextPath}/locatario/delete?cpf_pessoa=${locatario.cpf_pessoa}"
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <input class="checkbox-inline" type="checkbox" name="delete" value="${locatario.cpf_pessoa}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
                
            <div class="modal fade modal_excluir_locatario">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir este locatario?</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger link_confirmacao_excluir_locatario">Sim</a>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>                

            <div class="modal fade modal_excluir_locatarios">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir os locatarios selecionados?</p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-danger button_confirmacao_excluir_locatarios" type="button">Sim</button>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal modal-visualizar-locatario">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Detalhes</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-8">
                                        <p class="p_cpf"></p>
                                        <p class="p_habilitacao"></p>
                                        <p class="p_endereco"></p>
                                    </div>
                                    <div class="col-md-4">
                                        <a href="#" class="thumbnail">
                                            <img class="locatario-img"
                                                 src="${pageContext.request.contextPath}/img/default_avatar.png"
                                                 height="160" width="120"/>
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
                
        </div>
        
        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/locatario.js"></script>
    </body>
</html>