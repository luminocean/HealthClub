$(function () {
    $('#place-analysis').highcharts({
        chart: {
            type: 'column',
            margin: [ 50, 50, 100, 80]
        },
        title: {
            text: '场地使用信息'
        },
        xAxis: {
            categories: ["足球场","健身房","篮球场","瑜伽房","舞蹈房","羽毛球场"],
            labels: {
                rotation: -45,
                align: 'right',
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '使用次数/月'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '使用次数: <b>{point.y:.1f}</b>',
        },
        series: [{
            name: 'Population',
            data: [10,42,22,28,11,34],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });
});
				