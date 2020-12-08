<%-- 
    Document   : grid
    Created on : 7 de dez. de 2020, 14:34:22
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div id="page">
  <header>
    <div class="container">
      <head>
      <%@include file="../header_footer/head.jsp"%>
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
                    <li><a href="#">Name</a></li>
                    <li><a href="#">Price</a></li>
                    <li><a href="#">Position</a></li>
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
            <div class="product-image"> <a href="car-detail.jsp" title="HTC Rhyme Sense"> <img class="small-image" src="${pageContext.request.contextPath}/img/<c:if test="${carro.avatar == null}">default_avatar.png</c:if><c:if test="${carro.avatar != null}">${carro.avatar}</c:if>" alt="HTC Rhyme Sense"> </a> </div>
            <div class="product-shop">
              <h2 class="product-name"><a href="car-detail.jsp" title="HTC Rhyme Sense">HTC Rhyme Sense</a></h2>
              <div class="desc std">
              <c:if test="${carro.disponibilidade == true}"> <div class="new-label"> Used </div> </c:if>
                <br>
                <br>
                <p> ${carro.descricao} </p>
              </div>
              <div class="price-box">
                <p class="special-price"> <span class="price-label"></span> <span id="product-price-212" class="price"> $${carro.preco} </span> </p>
              </div>
              <div class="actions">
                <button class="button btn-cart ajx-cart" title="Add to Cart" type="button"><span>Alugar</span></button>
            </div>
          </li>
          </c:forEach>
        </ol>
          </div>
          <div class="toolbar bottom">
            
            <form id="sort-by">
              <label class="left">Sort By: </label>
              <ul>
                <li><a href="#">Position<span class="right-arrow"></span></a>
                  <ul>
                    <li><input type="button" name="order" value="name"> <a href="#">Name</a></li>
                    <li><input type="button" name="order" value="price"><a href="#">Price</a></li>
                    <li><input type="button" name="order" value="position"><a href="#">Position</a></li>
                  </ul>
                </li>
              </ul>
              <a class="button-asc left" href="#" title="Set Descending Direction"><span class="top_arrow"></span></a> </div>
          </form>
          
        </article>
        </div>
        <!--	///*///======    End article  ========= //*/// --> 
      </div>
      <aside class="col-left sidebar col-sm-3 col-xs-12 col-sm-pull-9 wow bounceInUp animated"> 
      <div class="section-filter">
            <div class="b-filter__inner bg-grey">
              <h2>Find your right car</h2>
              <form class="b-filter">
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98">
                    <option>Select Make</option>
                    <option>Make 1</option>
                    <option>Make 2</option>
                    <option>Make 3</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98">
                    <option>Select Car Status</option>
                    <option>Status 1</option>
                    <option>Status 2</option>
                    <option>Status 3</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98">
                    <option>Select Model</option>
                    <option>Model 1</option>
                    <option>Model 2</option>
                    <option>Model 3</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98">
                    <option>Select All Locations</option>
                    <option>Location 1</option>
                    <option>Location 2</option>
                    <option>Location 3</option>
                  </select>
                </div>
                <div class="btn-group bootstrap-select" style="width: 100%;">
                  <select class="selectpicker" data-width="100%" tabindex="-98">
                    <option>Select Year</option>
                    <option>2017</option>
                    <option>2016</option>
                    <option>2015</option>
                  </select>
                </div>
                <div class="ui-filter-slider">
                  <div class="sidebar-widget-body m-t-10">
                    <div class="price-range-holder"> <span class="min-max"> <span class="pull-left">$200.00</span> <span class="pull-right">$800.00</span> </span>
                      <input type="text" class="price-slider" value="" style="display:block" >
                    </div>
                    <!-- /.price-range-holder --> 
                  </div>
                </div>
                <div>
                  <div class="b-filter__btns">
                    <button class="btn btn-lg btn-primary">search vehicle</button>
                  </div>
                </div>
              </form>
            </div>
          </div>      
      
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
      <%@include file="../header_footer/foot.jsp"%>
      <title>[BD 2020] Carros</title>
    </div>
  </footer>
  <!-- End For version 1,2,3,4,6 --> 
</div>
<%@include file="/view/include/scripts.jsp"%>
<script src="${pageContext.servletContext.contextPath}/assets/js/car.js"></script>
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
<script type="text/javascript" src="js/jquery.mobile-menu.min.js"></script> 
<script src="js/countdown.js"></script> 
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
