<%-- 
    Document   : teste
    Created on : Apr 19, 2021, 12:30:34 PM
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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

<html>
    <header>
        <div class="container">
        <head>
        <%@include file="/header_footer/head.jsp"%>
        <title>[BD 2020] Carros</title>
        </div>
    </header>
<body>
    <c:set var='porradeteste' value="${requestScope.tamanhoKKKK}" />
    <h1>CU</h1>
    <h1>${requestScope.nomeList}</h1>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
        <table class="table table-striped table-dark">
            <thead>
              <tr>
                <th scope="col">Posição</th>
                <th scope="col">Nome</th>
                <th scope="col">Modelo</th>
                <th scope="col">Placa</th>
                <th scope="col">Media</th>
              </tr>
            </thead>
             <tbody>
            <c:forEach var="nome" items="${requestScope.nomeList}">
                <tr>
                <th scope="row">1</th>
                <td>${nome}</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <c:set var='contador' value="${requestScope.tamanhoKKKK + 1}" scope="page"/>
              </tr>
            </c:forEach>
            </tbody>
<!--            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>@mdo</td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>@mdo</td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
                <td>@mdo</td>
              </tr>
              
            </tbody>-->
        </table>
    <footer> 
        <div class="container">
          <head>
          <%@include file="/header_footer/foot.jsp"%>
          <title>[BD 2020] Carros</title>
        </div>
    </footer> 
</body>
    
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
</html>
