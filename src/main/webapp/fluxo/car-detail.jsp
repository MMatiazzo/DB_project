<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/session" prefix="session"%>
<session:my_user context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Harrier Car Detail Page</title>
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
<div id="page">
  <header>
    <div class="container">
      <head>
      <%@include file="../header_footer/head.jsp"%>
      <title>[BD 2020] Carros</title>
      </head>
    </div>
  </header>
   <c:set var='cpf_p' value="${sessionScope.usuario.cpf}" />
    <c:forEach var="carro" items="${requestScope.carList}">
    <c:if test="${carro.placa == param.placa}">
    <c:set var='placa' value="${carro.placa}" />
    <c:set var='valor' value="${carro.preco}" />
    <c:set var='abs' value="${carro.abss}" />
    <c:set var='modelo' value="${carro.modelo}" />
    <c:set var='ar_c' value="${carro.ar_condicionado}" />
    <c:set var='airbags' value="${carro.airbags}" />
    <c:set var='num_lugares' value="${carro.num_lugares}" />
    <c:set var='descricao' value="${carro.descricao}" />
    <c:set var='avatar' value="${carro.avatar}" />
    <c:set var='cpf_c' value="${carro.cpf_locador}" />
    </c:if>
    </c:forEach>
  <div class="page-heading">
    <div class="breadcrumbs">
      <div class="container">
        <!--row--> 
      </div>
      <!--container--> 
    </div>
    <div class="page-title">
      <h2>PRODUCT Detail</h2>
    </div>
  </div>
  <!-- BEGIN Main Container -->
  <div class="main-container col1-layout wow bounceInUp animated">
    <div class="main">
      <div class="col-main"> 
        <!-- Endif Next Previous Product -->
        <div class="product-view wow bounceInUp animated" itemscope="" itemtype="http://schema.org/Product" itemid="#product_base">
          <div id="messages_product_view"></div>
          <!--product-next-prev-->
          <div class="product-essential container">
            <div class="row">
              <form action="#" method="post" id="product_addtocart_form">
                <!--End For version 1, 2, 6 --> 
                <!-- For version 3 -->
                <div class="product-img-box col-lg-5 col-sm-5 col-xs-12">
                  <div class="new-label new-top-left">Hot</div>
                  <div class="sale-label sale-top-left">-15%</div>
                  <div class="product-image">
                    <div class="product-full"><img id="product-zoom1" src="${pageContext.request.contextPath}/img/<c:if test="${avatar == null}">default_avatar.png</c:if><c:if test="${avatar != null}">${avatar}</c:if>" data-zoom-image="${pageContext.request.contextPath}/img/<c:if test="${avatar == null}">default_avatar.png</c:if><c:if test="${avatar != null}">${avatar}</c:if>" alt="product-image"/> </div>
                  </div>
                </div>
                <!--End For version 1,2,6--> 
                <!-- For version 3 -->
                <div class="product-shop col-lg- col-sm-7 col-xs-12">
                  <div class="product-name">
                    <h1>${modelo} </h1>
                  </div>
                  <div class="price-block">
                    <div class="price-box">
                     
                      <p class="special-price"> <span class="price-label">Special Price</span> <span id="product-price-48" class="price">${valor}</span> </p>
                    </div>
                  </div>
                    <button class="button" title="Alugar" type="button"><span>  $ALUGAR  </span></button>
                    <button class="button" title="Alugar" type="button"><span>  PERFIL LOCADOR  </span></button>
                </div>
                <!--product-shop--> 
                <!--Detail page static block for version 3-->
              </form>
            </div>
          </div>
          <!--product-essential-->
          <div class="product-collateral container">
            <div id="productTabContent" class="tab-content">
                <h2 class="woocommerce-Reviews-title"> Descrição do veículo</h2>
                <br/>
              <div class="tab-pane fade in active" id="product_tabs_description">
                <div class="std">
                  <h4> ${descricao}.</h4>
                </div>
              </div>
                <br/>
              <!--<div class="tab-pane fade" id="product_tabs_tags">-->
              <div>
                  <h2 class="woocommerce-Reviews-title"> Veículo overview </h2>
              </div>
                <div class="spec-row" id="summarySpecs">
                    <table width="100%">
                      <tbody>
                        <tr>
                          <td class="label-spec"> PLACA <span class="coln">:</span></td>
                          <td class="value-spec"> ${placa} </td>
                        </tr>
                        <tr>
                          <td class="label-spec"> ABS. <span class="coln">:</span></td>
                          <td class="value-spec"> <c:if test="${abs == true}">SIM</c:if> <c:if test="${abs == false}">NÃO</c:if> </td>
                        </tr>
                        <tr class="odd">
                          <td class="label-spec"> MODELO <span class="coln">:</span></td>
                          <td class="value-spec"> ${modelo} </td>
                        </tr>
                        <tr class="odd">
                          <td class="label-spec"> AR CONDICIONADO <span class="coln">:</span></td>
                          <td class="value-spec"> <c:if test="${abs == true}">SIM</c:if> <c:if test="${abs == false}">NÃO</c:if> </td>
                        </tr>
                        <tr>
                          <td class="label-spec"> AIRBAGS <span class="coln">:</span></td>
                          <td class="value-spec"> <c:if test="${abs == true}">SIM</c:if> <c:if test="${abs == false}">NÃO</c:if> </td>
                        </tr>
                        <tr>
                          <td class="label-spec"> NUMERO DE LUGARES <span class="coln">:</span></td>
                          <td class="value-spec"> ${num_lugares} </td>
                        </tr>
                      </tbody>
                    </table>
                  <!--</div>-->
              </div>
              <br/>
              <br/>
                <div class="woocommerce-Reviews">
                  <div>
                        <h2 class="woocommerce-Reviews-title">Reviews </h2>
                    <ol class="commentlist">
                        <c:forEach var="review" items="${requestScope.reviewList}">
                          <li class="comment">
                            <div> <img alt="" src="${pageContext.request.contextPath}/img/${review.cpf_locador}" class="avatar avatar-60 photo">
                              <div class="comment-text">
                                <p class="meta"> <strong>${review.cpf_locatario}</strong> <span></span> ${review.data_review} </p>
                                <div class="description">
                                  <p> ${review.descricao} </p>
                                </div>
                              </div>
                            </div>
                          </li>
                          <!-- #comment-## -->
                        </c:forEach>
                    </ol>
                  </div>
                  <div>
                      <c:if test="${cpf_p != cpf_c}">
<!--                    <div>
                      <div class="comment-respond"> <span class="comment-reply-title">Add a review </span>
                        <form action="${pageContext.servletContext.contextPath}/review/create?placa_carro=${placa}&cpf_locador=${cpf_c}&cpf_locatario=${cpf_p}" method="post" class="comment-form" novalidate>
                          <p class="comment-form-comment">
                            <textarea id="comment" name="comment" cols="45" rows="8" required></textarea>
                          </p>
                          <p class="form-submit">
                            <input name="submit" type="submit" id="submit" class="submit" value="Submit">
                          </p>
                        </form>
                      </div>
                       #respond  
                    </div>-->
                    <button class="button " title="Add Review" type="button"><a href="${pageContext.servletContext.contextPath}/review/create?placa_carro=${placa}&cpf_locador=${cpf_c}&cpf_locatario=${cpf_p}">  Adicionar Descricao  </a> </button>
                    </c:if>
                  </div>
                  <div class="clear"></div>
                </div>
            </div>
          </div>
          <!-- end related product --> 
        </div>
        <!--box-additional--> 
        <!--product-view--> 
      </div>
    </div>
    <!--col-main--> 
  </div>
  <!--main-container--> 
</div>
<!--col1-layout--> 
<!-- End For version 1,2,3,4,6 -->

 <footer> 
    <div class="container">
      <head>
      <%@include file="../header_footer/foot.jsp"%>
      <title>[BD 2020] Carros</title>
    </div>
  </footer>
<!--page--> 

<div id="fade"></div>

<!-- JavaScript --> 
<script type="text/javascript" src="js/jquery.min.js"></script> 
<script type="text/javascript" src="js/bootstrap.min.js"></script> 
<script src="js/bootstrap-slider.min.js"></script> 
<script src="js/bootstrap-select.min.js"></script> 
<script type="text/javascript" src="js/parallax.js"></script> 
<script type="text/javascript" src="js/revslider.js"></script> 
<script type="text/javascript" src="js/common.js"></script> 
<script type="text/javascript" src="js/jquery.bxslider.min.js"></script> 
<script type="text/javascript" src="js/owl.carousel.min.js"></script> 
<script src="js/cloud-zoom.js"></script> 
<script type="text/javascript" src="js/jquery.mobile-menu.min.js"></script> 
<script type="text/javascript">
    function HideMe()
    {
        jQuery('.popup1').hide();
        jQuery('#fade').hide();
	
		
    }
	function ShowMe()
    {
        jQuery('.popup2').show();
        jQuery('#fade').show();
		
    }
		function ShowMe1()
    {
        jQuery('.popup3').show();
        jQuery('#fade').show();
		
    }
	function HideMe1()
    {
        jQuery('.popup2').hide();
        jQuery('#fade').hide();

		
    }
	
		function HideMe2()
    {
        jQuery('.popup3').hide();
        jQuery('#fade').hide();

		
    }
</script>

</body>

</html>
