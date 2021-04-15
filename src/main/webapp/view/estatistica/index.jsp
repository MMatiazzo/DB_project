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
        <h1>GRAFICO TESTE</h1>
        <div>
            <canvas id="myChart" style="max-width:1000px;max-height:500px"></canvas>
        </div> 
    </body>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const labels = [ // ${chartLabels}
            'January',
            'February',
            'March',
            'April',
            'May',
            'June'
        ];
        const data = {
          labels: labels,
          datasets: [{
            label: "${param.cpf_locador}",
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: [2,100,5]
          }],
          datasets: [{
            label: "${param.cpf_locador}",
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: [2,100,5]
          }]
        };
        const config = {
            type: 'doughnut',// ${chartType}
            data,
            options: {
                responsive: true,
                plugins:{
                    title: {
                        display: true,
                            text: 'Chart.js Bar Chart' // ${chartTitle}
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
