$(function () {
    $('#age-analysis').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员年龄分布'
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
            name: '人数比例',
            data: [
                ['0-10岁',    5.0],
                ['10-20岁',  16.8],
                ['20-30岁',  38.5],
                ['30-40岁',  26.2],
                ['40-50岁',  10.7],
                ['50+',       2.8]
            ]
        }]
    });
});				