$(function () {
    $('#peopleNumber-analysis').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '每月参加活动人数比较'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: '月总人数'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '今年',
            data: [45, 48, 67, 78, 80, 95, 0, 0, 0, 0, 0, 0]
        }, {
            name: '去年',
            data: [39, 42, 57, 85, 79, 92, 80, 76, 62, 63, 66, 48]
        }]
    });
});				