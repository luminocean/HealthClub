package action.chart;

import java.awt.Font;
import java.util.Calendar;

import logic.logicInterface.IStatisticService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.opensymphony.xwork2.ActionSupport;

public class LineChartAction extends ActionSupport {
	private String title;
	private String xAxis;
	private String yAxis;
	
	
	private JFreeChart chart;
	private IStatisticService statisticService;

	public JFreeChart getChart() {

		chart = ChartFactory.createTimeSeriesChart(title, xAxis, yAxis,

		getDataSet(), true, false, false);

		// 重新设置图标标题，改变字体

		chart.setTitle(new TextTitle(title, new Font("微软雅黑", Font.ITALIC,

		22)));

		// 取得统计图标的第一个图例

		LegendTitle legend = chart.getLegend(0);

		// 修改图例的字体

		legend.setItemFont(new Font("微软雅黑", Font.BOLD, 14));

		XYPlot plot = (XYPlot) chart.getPlot();

		// 取得横轴

		ValueAxis categoryAxis = plot.getDomainAxis();

		// 设置横轴显示标签的字体

		categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 22));

		categoryAxis.setTickLabelFont(new Font("微软雅黑", Font.BOLD, 18));

		// 取得纵轴

		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();

		// 设置纵轴显示标签的字体

		numberAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 22));

		return chart;

	}

	public void setChart(JFreeChart chart) {

		this.chart = chart;

	}

	// 返回一个CategoryDataset实例

	private XYDataset getDataSet() {

		TimeSeries apple = new TimeSeries("月份", Month.class);

		apple.add(new Month(10, 2007), 3900);

		apple.add(new Month(11, 2007), 900);

		apple.add(new Month(12, 2007), 2500);

		apple.add(new Month(1, 2008), 3900);

		apple.add(new Month(2, 2008), 2000);

		apple.add(new Month(3, 2008), 3300);
	 

		TimeSeriesCollection dataSet = new TimeSeriesCollection();

		dataSet.addSeries(apple);
		
		dataSet = statisticService.getPeopleDataInMonths();


		return dataSet;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

	public IStatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(IStatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
}
