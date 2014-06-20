$(function () {
    $('#address-analysis').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员居住地信息'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                ['玄武区',   45.0],
                ['白下区',    26.8],
                ['秦淮区',    8.5],
                ['建邺区',     6.2],
                ['鼓楼区',   47],
                ['下关区',   45.0],
                ['白下区',       26.8],
                ['浦口区',    8.5],
                ['栖霞区',     6.2],
                ['雨花台区',   27],
                ['江宁区',     6.2],
                ['六合区',   37],
                ['溧水县',   45.0],
                ['高淳县',       26.8],
            ]
        }]
    });
});				