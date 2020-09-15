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
        <title>[BD 2020] Pagamentos</title>
    </head>
    <body>

        <div class="container">
            
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/pagamento/create">
                    Inserir novo Pagamentos
                </a>
            </div>

            <form class="form_excluir_pagamentos" action="${pageContext.servletContext.contextPath}/pagamento/delete" method="POST">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-2 h4">Num Placa</th>
                            <th class="col-lg-5 h4">Data Pag</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pagamento" items="${requestScope.pagamentoList}">
                            <tr>
                                <td>
                                    <span class="h4"><c:out value="${pagamento.num_placa_carro}"/></span>
                                </td>
                                <td>
                                    <a class="link_visualizar_pagamento" href="#" data-href="${pageContext.servletContext.contextPath}/pagamento/read?data_pagamento=${pagamento.data_pagamento}&num_placa_carro=${pagamento.num_placa_carro}&cpf_locador=${pagamento.cpf_locador}&cpf_locatario=${pagamento.cpf_locatario}&valor=${pagamento.valor}&data_entrega=${pagamento.data_entrega}&data_devolucao=${pagamento.data_devolucao}">
                                        <span class="h4"><c:out value="${pagamento.data_pagamento}"/></span>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-default"
                                       href="${pageContext.servletContext.contextPath}/pagamento/update?data_pagamento=${pagamento.data_pagamento}&num_placa_carro=${pagamento.num_placa_carro}&cpf_locador=${pagamento.cpf_locador}&cpf_locatario=${pagamento.cpf_locatario}&valor=${pagamento.valor}&data_entrega=${pagamento.data_entrega}&data_devolucao=${pagamento.data_devolucao}"
                                       data-toggle="tooltip"
                                       data-original-title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_pagamento"
                                       href="#"
                                       data-href="${pageContext.servletContext.contextPath}/pagamento/delete?data_pagamento=${pagamento.data_pagamento}&num_placa_carro=${pagamento.num_placa_carro}&cpf_locador=${pagamento.cpf_locador}&cpf_locatario=${pagamento.cpf_locatario}"
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
                
            <div class="modal fade modal_excluir_pagamento">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir este pagamento?</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger link_confirmacao_excluir_pagamento">Sim</a>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>                

            <div class="modal fade modal_excluir_pagamentos">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmação</h4>
                            <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Tem certeza de que deseja excluir os pagamentos selecionados?</p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-danger button_confirmacao_excluir_pagamentos" type="button">Sim</button>
                            <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal modal-visualizar-pagamento">
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
                                        <p class="p_num_placa_carro"></p>
                                        <p class="p_data_pagamento"></p>
                                        <p class="p_cpf_locador"></p>
                                        <p class="p_cpf_locatario"></p>
                                        <p class="p_valor"></p>
                                        <p class="p_data_entrega"></p>
                                        <p class="p_data_devolucao"></p>
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
        <script src="${pageContext.servletContext.contextPath}/assets/js/pagamento.js"></script>
    </body>
</html>