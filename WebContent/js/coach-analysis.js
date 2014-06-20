$(function () {
    
    var colors = Highcharts.getOptions().colors,
        categories = ['王坤', '张睿', '孙思凡', '李峰', '周荣高'],
        name = '教练',
        data = [{
                y: 42,
                color: colors[0],
                drilldown: {
                    name: '活动信息',
                    categories: ["足球","健身","篮球","瑜伽","舞蹈","羽毛球"],
                    data: [2,8,4,3,6,5],
                    color: colors[0]
                }
            }, {
                y: 35,
                color: colors[1],
                drilldown: {
                	name: '活动信息',
                	categories: ["足球","健身","篮球","瑜伽","舞蹈","羽毛球"],
                    data: [3,5,2,8,6,2],
                    color: colors[1]
                }
            }, {
                y: 38,
                color: colors[2],
                drilldown: {
                	name: '活动信息',
                	categories: ["足球","健身","篮球","瑜伽","舞蹈","羽毛球"],
                    data: [1,9,4,3,2,5],
                    color: colors[2]
                }
            }, {
                y: 47,
                color: colors[3],
                drilldown: {
                	name: '活动信息',
                	categories: ["足球","健身","篮球","瑜伽","舞蹈","羽毛球"],
                    data: [2,3,6,3,6,5],
                    color: colors[3]
                }
            }, {
                y: 44,
                color: colors[4],
                drilldown: {
                	name: '活动信息',
                	categories: ["足球","健身","篮球","瑜伽","舞蹈","羽毛球"],
                    data: [3,5,4,3,6,7],
                    color: colors[4]
                }
            }];

    function setChart(name, categories, data, color) {
	chart.xAxis[0].setCategories(categories, false);
	chart.series[0].remove(false);
	chart.addSeries({
		name: name,
		data: data,
		color: color || 'white'
	}, false);
	chart.redraw();
    }

    var chart = $('#coach-analysis').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '教练使用信息'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: '活动次数'
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function() {
                            var drilldown = this.drilldown;
                            if (drilldown) { // drill down
                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                            } else { // restore
                                setChart(name, categories, data);
                            }
                        }
                    }
                },
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y;
                    }
                }
            }
        },
        tooltip: {
            formatter: function() {
                var point = this.point,
                    s = this.x +':<b>'+ this.y +'</b><br/>';
                if (point.drilldown) {
                    s += '点击查看'+ point.category +' 具体活动';
                } else {
                    s += '点击回到总体数据';
                }
                return s;
            }
        },
        series: [{
            name: name,
            data: data,
            color: 'white'
        }],
        exporting: {
            enabled: false
        }
    })
    .highcharts(); // return chart
});				