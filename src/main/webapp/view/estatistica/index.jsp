<%-- 
    Document   : teste
    Created on : 15 de abr. de 2021, 10:31:26
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estatísticas</title>
    </head>
    <body>
        <h1> ${chartIndexAxis}</h1>
        <div>
            <canvas id="myChart" style="max-width:1000px;max-height:500px"></canvas>
        </div> 
    </body>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const labels = [ ${chartLabels} ];
        const data = {
          labels: labels,
          datasets: [{
            label: "${param.cpf_locador}",
            backgroundColor: 'rgb(255, 66, 132)',
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
</html>
