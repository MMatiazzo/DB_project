<%-- 
    Document   : grid
    Created on : 7 de dez. de 2020, 14:34:22
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>
<session:my_user context="${pageContext.servletContext.contextPath}"/>
﻿<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Default Description">
<meta name="keywords" content="fashion, store, E-commerce">
<meta name="robots" content="*">
<link rel="icon" href="#" type="image/x-icon">
<link rel="shortcut icon" href="#" type="image/x-icon">

<!-- CSS Style -->
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/font-awesome.css" media="all">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/bootstrap-select.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/revslider.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/owl.theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/jquery.mobile-menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/style.css" media="all">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/stylesheet/responsive.css" media="all">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700,800' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Teko:300,400,500,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Saira+Condensed:300,400,500,600,700,800" rel="stylesheet">
</head>
<body>
<c:set var='cpf_p' value="${sessionScope.usuario.cpf}" />
<div id="page">
  <header>
    <div class="container">
      <head>
      <%@include file="/header_footer/head.jsp"%>
      <title>[BD 2020] Carros</title>
    </div>
  </header>
<div class="page-heading">
  <div class="breadcrumbs">
    <!--container--> 
  </div>
  <div class="page-title">
    <h2>PRODUCT LISTING</h2>
  </div>
</div>
<!--breadcrumbs--> 
<!-- BEGIN Main Container col2-left -->
<section class="main-container col2-left-layout bounceInUp animated"> 
  <!-- For version 1, 2, 3, 8 --> 
  <!-- For version 1, 2, 3 -->
  <div class="container">
    <div class="row">
      <div class="col-main col-sm-9 col-sm-push-3 product-grid">
      <div class="pro-coloumn">
        <article class="col-main">
          <div class="toolbar">
<!--            <div class="sorter">
              <div class="view-mode"> <span title="Grid" class="button button-active button-grid">&nbsp;</span><a href="list.html" title="List" class="button-list">&nbsp;</a> </div>
            </div>-->
            <div id="sort-by">
              <label class="left">Sort By: </label>
              <ul>
                <li><a href="#">Position<span class="right-arrow"></span></a>
                  <ul>
                    <li><a href="${pageContext.servletContext.contextPath}/fluxo/order_by?order_by=modelo&order=asc">Name</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/fluxo/order_by?order_by=preco&order=asc">Price</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/fluxo/order_by?order_by=ano&order=asc">Ano</a></li>
                  </ul>
                </li>
              </ul>
              <a class="button-asc left" href="#" title="Set Descending Direction"><span class="top_arrow"></span></a> 
            </div>
          </div>
        <div class="category-products">
          <ol class="products-list" id="products-list">
       <c:forEach var="carro" items="${requestScope.carList}">
                 <li class="item even">
            <div class="product-image"> <a href="${pageContext.servletContext.contextPath}/fluxo/detail?placa=${carro.placa}" title="HTC Rhyme Sense"> <img class="small-image" src="${pageContext.request.contextPath}/img/<c:if test="${carro.avatar == null}">default_avatar.png</c:if><c:if test="${carro.avatar != null}">${carro.avatar}</c:if>" alt="HTC Rhyme Sense"> </a> </div>
            <div class="product-shop">
              <h2 class="product-name"><a href="${pageContext.servletContext.contextPath}/fluxo/detail?placa=${carro.placa}" title="HTC Rhyme Sense">${carro.modelo} <br/> Ano: ${carro.ano}</a></h2>
              <div class="desc std">
              <c:if test="${carro.disponibilidade == false}"> <div class="new-label"> Used </div> </c:if>
                <br>
                <br>
                <p> ${carro.descricao} </p>
              </div>
              <div class="price-box">
                <p class="special-price"> <span class="price-label"></span> <span id="product-price-212" class="price"> $${carro.preco} </span> </p>
              </div>
              <div class="actions">
                <c:if test="${carro.disponibilidade == true}"> 
                    <form action="${pageContext.servletContext.contextPath}/carteira/alugar" method="post">
                        <input type="hidden" name="cpf_locador" value="${carro.cpf_locador}" />
                        <input type="hidden" name="cpf_locatario" value="${cpf_p}" />
                        <input type="hidden" name="placa" value="${carro.placa}" />
                        <input type="hidden" name="disponibilidade" value="${carro.disponibilidade}" />
                        <input type="hidden" name="modelo" value="${carro.modelo}" />
                        <input type="hidden" name="airbags" value="${carro.airbags}" />
                        <input type="hidden" name="cpf_locador" value="${carro.cpf_locador}" />
                        <input type="hidden" name="preco" value="${carro.preco}" />
                        <input type="hidden" name="descricao" value="${carro.descricao}" />
                        <input type="hidden" name="tipo" value="${carro.tipo}" />
                        <input type="hidden" name="ano" value="${carro.ano}" />
                        <input type="hidden" name="abss" value="${carro.abss}" />
                        <input type="hidden" name="ar_condicionado" value="${carro.ar_condicionado}" />
                        <input type="hidden" name="num_lugares" value="${carro.num_lugares}" />
                        <input class="button" title="Alugar" value="ALUGAR" type="submit" />
                    </form>
                    </c:if>
                <button class="button" title="Vizualizar-Locador" type="submit"><a href="${pageContext.servletContext.contextPath}/fluxo/locdetail?cpf_locador=${carro.cpf_locador}"> Visitar Perfil Locador</a></button>
            </div>
            </div>
          </li>
          </c:forEach>
        </ol>
          </div>
          
        </article>
        </div>
        <!--	///*///======    End article  ========= //*/// --> 
      </div>
      <aside class="col-left sidebar col-sm-3 col-xs-12 col-sm-pull-9 wow bounceInUp animated"> 
              <h2>Pesquisa Guiada</h2>
              <form class="b-filter" action="${pageContext.servletContext.contextPath}/fluxo/filterOption?${request.getParameter("modelo_s")}&${request.getParameter("ano_s")}&${request.getParameter("preco_s")}" method="get">
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98" name="modelo_s">
                    <option >Modelo</option>
                    <option >Gol</option>
                    <option  >Uno</option>
                    <option  >Palio</option>
                    <option  >Celta</option>
                    <option  >Voyage</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98" name="ano_s">
                    <option  >Ano</option>
                    <option  >2015</option>
                    <option  >2016</option>
                    <option  >2017</option>
                    <option  >2018</option>
                    <option  >2019</option>
                    <option  >2020</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98" name="preco_s">
                    <option  >Preço Até</option>
                    <option  >20</option>
                    <option  >10000</option>
                    <option  >20000</option>
                    <option  >30000</option>
                    <option  >40000</option>
                    <option  >80000</option>
                  </select>
                </div>
                  <div class="b-filter__btns">
                      <input class="button" type="submit" value="filtrar" />
                  </div>
              </form>     
      </aside>
      <!--col-right sidebar--> 
    </div>
    <!--row--> 
  </div>
  <!--container--> 
</section>
<!--main-container col2-left-layout--> 

 <footer> 
    <div class="container">
      <head>
      <%@include file="/header_footer/foot.jsp"%>
      <title>[BD 2020] Carros</title>
    </div>
  </footer>
  <!-- End For version 1,2,3,4,6 --> 
</div>
<%@include file="/view/include/scripts.jsp"%>
<script src="${pageContext.servletContext.contextPath}/assets/js/car.js"></script>
<script>
    const change_url = () => {
  var id = $('#record option:selected').val();
  var submit = $("#submit_url");
  if (id != '')
    submit.attr('href', "/formpostflight.html?flight_id=" + id);
  else
    submit.attr('href', "#");
};
</script>
<!-- JavaScript --> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script> 
<script src="${pageContext.servletContext.contextPath}/js/bootstrap-slider.min.js"></script> 
<script src="${pageContext.servletContext.contextPath}/js/bootstrap-select.min.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/parallax.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/revslider.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/common.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.bxslider.min.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/owl.carousel.min.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.mobile-menu.min.js"></script> 
<script src="${pageContext.servletContext.contextPath}/js/countdown.js"></script> 
<script>
        jQuery(document).ready(function(){
            jQuery('#rev_slider_4').show().revolution({
                dottedOverlay: 'none',
                delay: 5000,
                startwidth: 1170,
	            startheight:650,

                hideThumbs: 200,
                thumbWidth: 200,
                thumbHeight: 50,
                thumbAmount: 2,

                navigationType: 'thumb',
                navigationArrows: 'solo',
                navigationStyle: 'round',

                touchenabled: 'on',
                onHoverStop: 'on',
                
                swipe_velocity: 0.7,
                swipe_min_touches: 1,
                swipe_max_touches: 1,
                drag_block_vertical: false,
            
                spinner: 'spinner0',
                keyboardNavigation: 'off',

                navigationHAlign: 'center',
                navigationVAlign: 'bottom',
                navigationHOffset: 0,
                navigationVOffset: 20,

                soloArrowLeftHalign: 'left',
                soloArrowLeftValign: 'center',
                soloArrowLeftHOffset: 20,
                soloArrowLeftVOffset: 0,

                soloArrowRightHalign: 'right',
                soloArrowRightValign: 'center',
                soloArrowRightHOffset: 20,
                soloArrowRightVOffset: 0,

                shadow: 0,
                fullWidth: 'on',
                fullScreen: 'off',

                stopLoop: 'off',
                stopAfterLoops: -1,
                stopAtSlide: -1,

                shuffle: 'off',

                autoHeight: 'off',
                forceFullWidth: 'on',
                fullScreenAlignForce: 'off',
                minFullScreenHeight: 0,
                hideNavDelayOnMobile: 1500,
            
                hideThumbsOnMobile: 'off',
                hideBulletsOnMobile: 'off',
                hideArrowsOnMobile: 'off',
                hideThumbsUnderResolution: 0,

                hideSliderAtLimit: 0,
                hideCaptionAtLimit: 0,
                hideAllCaptionAtLilmit: 0,
                startWithSlide: 0,
                fullScreenOffsetContainer: ''
            });
        });
        </script> 
<script type="text/javascript">
    function HideMe()
    {
        jQuery('.popup1').hide();
        jQuery('#fade').hide();
    }
</script> 

</body>

</html>
