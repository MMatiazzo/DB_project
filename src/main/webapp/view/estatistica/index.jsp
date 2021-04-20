<%-- 
    Document   : teste
    Created on : 15 de abr. de 2021, 10:31:26
    Author     : Guilherme
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estatísticas</title>
    </head>
    <body>
        <header>
            <div class="container">
            <%@include file="/header_footer/head.jsp"%>
            <title>[BD 2020] Carros</title>
            </div>
        </header>
            <br/>
            <br/>
        <div>
            <canvas id="myChart" style="max-width:1000px;max-height:500px"></canvas>
        </div>
    <footer> 
    <div class="container">
      <%@include file="/header_footer/foot.jsp"%>
      <title>[BD 2020] Carros</title>
    </div>
  </footer>    
    </body>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const colors_teste = ${chartData}.map((value) => value < 0 ? 'rgb(54, 162, 235)' : 'rgb(255, 205, 86)');
        console.log(colors_teste);
        
        const labels = [ ${chartLabels} ];
        const data = {
          labels: labels,
          datasets: [{
            label: "${param.cpf_locador}",
            backgroundColor: colors_teste,
            borderColor: 'rgb(255, 55, 132)',
            data: ${chartData}
          }],
        };
        const config = {
            type: ${chartType},// ${chartType}
            data,
            options: {
                indexAxis: ${chartIndexAxis},
                responsive: true,
                plugins:{
                    title: {
                        display: true,
                            text: ${chartTitle}
                    }
                }
            }
        };
        var myChart = new Chart(
            document.getElementById('myChart'),
            config
        );
    </script>
    
    
    
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
