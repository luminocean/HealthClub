package action.chart;

import java.awt.Font;

import logic.logicInterface.IStatisticService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionSupport;

public class BarChartAction extends ActionSupport {
	private String title;
	private String xAxis;
	private String yAxis;
	
	private JFreeChart chart;
	private IStatisticService statisticService;

	public JFreeChart getChart() {

		chart = ChartFactory.createBarChart3D(title, xAxis, yAxis,
				getDataset(), PlotOrientation.VERTICAL, false, false, false);

		chart.setTitle(new TextTitle(title, new Font("微软雅黑", Font.BOLD,

		22)));

		//LegendTitle legend = chart.getLegend();

		// 修改图例的字体

		//legend.setItemFont(new Font("微软雅黑", Font.PLAIN, 14));

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// 取得横轴

		CategoryAxis categoryAxis = plot.getDomainAxis();

		categoryAxis.setLabelFont(new Font("微软雅黑", Font.PLAIN, 22));

		// 分类标签以45度角倾斜

		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		categoryAxis.setTickLabelFont(new Font("微软雅黑", Font.PLAIN, 22));

		// 取得纵轴

		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();

		numberAxis.setLabelFont(new Font("微软雅黑", Font.PLAIN, 22));

		return chart;

	}

	
	/**
	 * 根据标题判断需要哪一种数据
	 * @return
	 */
	private CategoryDataset getDataset() {
		CategoryDataset dataSet;
		
		switch(title){
		case "场地统计":
			dataSet = getPlaceDataset();
			break;
			
		case "教练统计":
			dataSet = getCoachDataset();
			break;
		

		default:
			dataSet = getPlaceDataset();
			break;
		}

		return dataSet;
	}
	
	
	/**
	 * 获取教练的工作情况
	 * @return
	 */
	private CategoryDataset getCoachDataset() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		dataSet = statisticService.getCoachData();

		return dataSet;
	}


	/**
	 * 获取场地统计数据
	 * @return
	 */
	private CategoryDataset getPlaceDataset() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		dataSet = statisticService.getPlaceData();


		return dataSet;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
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
