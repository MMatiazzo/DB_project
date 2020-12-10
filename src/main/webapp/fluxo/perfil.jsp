<%-- 
    Document   : perfil
    Created on : 9 de dez. de 2020, 20:46:43
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="stylesheet/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="stylesheet/font-awesome.css" media="all">
<link rel="stylesheet" type="text/css" href="stylesheet/bootstrap-select.css">
<link rel="stylesheet" type="text/css" href="stylesheet/revslider.css" >
<link rel="stylesheet" type="text/css" href="stylesheet/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="stylesheet/owl.theme.css">
<link rel="stylesheet" type="text/css" href="stylesheet/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="stylesheet/jquery.mobile-menu.css">
<link rel="stylesheet" type="text/css" href="stylesheet/style.css" media="all">
<link rel="stylesheet" type="text/css" href="stylesheet/responsive.css" media="all">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700,800' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Teko:300,400,500,600,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Saira+Condensed:300,400,500,600,700,800" rel="stylesheet">
</head>
<body>
    <c:forEach var="carro" items="${requestScope.carList}" >
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
    </c:if>
    </c:forEach>
<div id="page">
  <header>
    <div class="container">
      <head>
      <%@include file="../header_footer/head.jsp"%>
      <title>[BD 2020] Carros</title>
      </head>
    </div>
  </header>
  <div class="page-heading">
    <div class="breadcrumbs">
      <div class="container">
        <!--row--> 
      </div>
      <!--container--> 
    </div>
    <div class="page-title">
      <h2>Perfil</h2>
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
                    <h1>NOME </h1>
                  </div>
                    <button class="button " title="Add to Cart" type="button" display="inline"><span>  Editar  </span></button>
                    <button class="button " title="Add to Cart" type="button"><span>  Creditar carteira  </span></button>
                    <button class="button " title="Add to Cart" type="button"><span>  Postar carro  </span></button>
                    <div class="woocommerce-Reviews">
                        <h2>Creditos Restantes: $700</h2>
                    </div>
                    
                </div>
                <!--product-shop--> 
                <!--Detail page static block for version 3-->
              </form>
            </div>
          </div>
          <!--product-essential-->
          <div class="product-collateral container">
            <div id="productTabContent" class="tab-content">
              <!--<div class="tab-pane fade" id="product_tabs_tags">-->
              <div>
                  <div>
                    <h2 class="woocommerce-Reviews-title"> Lista de carros postados: </h2>
                  </div>
                  <div class="category-products">
                    <ol class="products-list" id="products-list">
                        <c:forEach var="carro" items="${requestScope.carList}">
                            <li class="item even">
                                <div class="product-image"> <a href="novo_teste2?placa=${carro.placa}" title="HTC Rhyme Sense"> <img class="small-image" src="${pageContext.request.contextPath}/img/<c:if test="${carro.avatar == null}">default_avatar.png</c:if><c:if test="${carro.avatar != null}">${carro.avatar}</c:if>" alt="HTC Rhyme Sense"> </a> </div>
                                <div class="product-shop">
                                <h2 class="product-name"><a href="novo_teste2?placa=${carro.placa}" title="HTC Rhyme Sense">${carro.modelo}</a></h2>
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
                                <button class="button" title="Editar" type="button"><span>Editar</span></button>
                                </div>
                                </div>
                                </li>
                                </c:forEach>
                    </ol>
                </div>
              </div>
              <br/>
              <br/>
              <div>
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
                    <div>
                      <div class="comment-respond"> <span class="comment-reply-title">Add a review </span>
                        <form action="#" method="post" class="comment-form" novalidate>
                          <p class="comment-form-comment">
                            <textarea id="comment" name="comment" cols="45" rows="8" required></textarea>
                          </p>
                          <p class="form-submit">
                            <input name="submit" type="submit" id="submit" class="submit" value="Submit">
                          </p>
                        </form>
                      </div>
                      <!-- #respond --> 
                    </div>
                  </div>
                  <div class="clear"></div>
                </div>
            </div>
              <div class="tab-pane fade" id="product_tabs_custom1">
                <div class="product-tabs-content-inner clearfix">
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempor, lorem et placerat vestibulum, metus nisi posuere nisl, in accumsan elit odio quis mi. Cras neque metus, consequat et blandit et, luctus a nunc. Etiam gravida vehicula tellus, in imperdiet ligula euismod eget. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam erat mi, rutrum at sollicitudin rhoncus, ultricies posuere erat. Duis convallis, arcu nec aliquam consequat, purus felis vehicula felis, a dapibus enim lorem nec augue.</p>
                </div>
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

