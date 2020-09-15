<%-- 
    Document   : index
    Created on : 14 de set. de 2020, 10:54:56
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2020] Reviews</title>
    </head>
    <body>

        <div class="container">
            
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/review/create">
                    Inserir nova Review
                </a>
            </div>

            <form class="form_excluir_reviews" action="${pageContext.servletContext.contextPath}/review/delete" method="POST">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-2 h4">Descrição</th>
                            <th class="col-lg-3 h4">Data</th>
                            <th class="col-lg-5 h4">Nota</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                            <th class="col-lg-1 h4 text-center">Excluir?</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="review" items="${requestScope.reviewList}">
                            <tr>
                                <td>
                                    <span class="h4"><c:out value="${review.descricao}"/></span>
                                </td>
                                <td>
                                    <span class="h4"><c:out value="${review.data_review}"/></span>
                                </td>
                                <td>
                                    <a class="link_visualizar_review" href="#" data-href="${pageContext.servletContext.contextPath}/review/read?num_placa_carro=${review.num_placa_carro}&cpf_locador=${review.cpf_locador}&cpf_locatario=${review.cpf_locatario}">
                                        <span class="h4"><c:out value="${review.nota}"/></span>
                                    </a>
                                </td>
                                
                                <td class="text-center">
                                    <a class="btn btn-default"
                                       href="${pageContext.servletContext.contextPath}/review/update?num_placa_carro=${review.num_placa_carro}&cpf_locador=${review.cpf_locador}&cpf_locatario=${review.cpf_locatario}"
                                       data-toggle="tooltip"
                                       data-original-title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_review"
                                       href="#"
                                       data-href="${pageContext.servletContext.contextPath}/review/delete?num_placa_carro=${review.num_placa_carro}&cpf_locador=${review.cpf_locador}&cpf_locatario=${review.cpf_locatario}"
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </td>
                                <!--CONSERTAR CHECK BOX-->
                                <td class="text-center">        
                                    <input class="checkbox-inline" type="checkbox" name="delete" value="${review.num_placa_carro}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
                <a class="btn btn-lg btn-primary" href="http://localhost:8080/bdapp">
                    Voltar para tela incial
                </a>
                
            <div class="modal fade modal_excluir_review">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir esta review?</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger link_confirmacao_excluir_review">Sim</a>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>                

            <div class="modal fade modal_excluir_reviews">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir as reviews selecionadas?</p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-danger button_confirmacao_excluir_reviews" type="button">Sim</button>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal modal-visualizar-review">
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
                                        <p class="p_data_review"></p>
                                        <p class="p_num_placa"></p>
                                        <p class="p_cpf_locador"></p>
                                        <p class="p_cpf_locatario"></p>
                                        <p class="p_descricao"></p>
                                    </div>
                                    <div class="col-md-4">
                                        <p class="p_nota"></p>
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
        <script src="${pageContext.servletContext.contextPath}/assets/js/review.js"></script>
    </body>
</html>