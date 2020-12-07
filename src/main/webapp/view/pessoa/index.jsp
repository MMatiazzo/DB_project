<%-- 
    Document   : index
    Created on : Sep. 1, 2020, 10:40:35 a.m.
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2020] Pessoas</title>
    </head>
    <body>

        <div class="container">
            
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/pessoa/create">
                    Inserir nova Pessoa
                </a>
            </div>

            <form class="form_excluir_pessoas" action="${pageContext.servletContext.contextPath}/pessoa/delete" method="POST">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-2 h4">ID</th>
                            <th class="col-lg-5 h4">Login</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                            <th class="col-lg-1 h4 text-center">Excluir?</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pessoa" items="${requestScope.pessoaList}">
                            <tr>
                                <td>
                                    <span class="h4"><c:out value="${pessoa.cpf}"/></span>
                                </td>
                                <td>
                                    <a class="link_visualizar_pessoa" href="#" data-href="${pageContext.servletContext.contextPath}/pessoa/read?cpf=${pessoa.cpf}">
                                        <span class="h4"><c:out value="${pessoa.login}"/></span>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-default"
                                       href="${pageContext.servletContext.contextPath}/pessoa/update?cpf=${pessoa.cpf}"
                                       data-toggle="tooltip"
                                       data-original-title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_pessoa"
                                       href="#"
                                       data-href="${pageContext.servletContext.contextPath}/pessoa/delete?cpf=${pessoa.cpf}"
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <input class="checkbox-inline" type="checkbox" name="delete" value="${pessoa.cpf}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
                <a class="btn btn-lg btn-primary" href="http://localhost:8080/bdapp">
                    Voltar para tela incial
                </a>
                
            <div class="modal fade modal_excluir_pessoa">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir este pessoa?</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger link_confirmacao_excluir_pessoa">Sim</a>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>                

            <div class="modal fade modal_excluir_pessoas">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir os pessoas selecionados?</p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-danger button_confirmacao_excluir_pessoas" type="button">Sim</button>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal modal-visualizar-pessoa">
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
                                        <p class="p_login"></p>
                                        <p class="p_nome"></p>
                                        <p class="p_nascimento"></p>
                                    </div>
                                    <div class="col-md-4">
                                        <a href="#" class="thumbnail">
                                            <img class="pessoa-img"
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
        <script src="${pageContext.servletContext.contextPath}/assets/js/pessoa.js"></script>
    </body>
</html>