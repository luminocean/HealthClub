package action.chart;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import logic.logicInterface.StatisticService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionSupport;

public class PieChartAction extends ActionSupport {
	private String title;
	
	private StatisticService statisticService;
	private JFreeChart chart;

	public JFreeChart getChart() {

		chart = ChartFactory.createPieChart3D(title, getDataset(), true, false, false);

		chart.setTitle(new TextTitle(title, new Font("微软雅黑", Font.BOLD, 22)));

		LegendTitle legend = chart.getLegend();

		legend.setItemFont(new Font("微软雅黑", Font.BOLD, 14));

		PiePlot3D plot = (PiePlot3D) chart.getPlot();

		plot.setLabelFont(new Font("微软雅黑", Font.PLAIN, 18));

		// 设定背景透明度（0-1.0之间）

		plot.setBackgroundAlpha(0.9f);

		// 设定前景透明度（0-1.0之间）

		plot.setForegroundAlpha(0.50f);

		String unitStyle = "{0}({2})";

		// 设置图例显示样式

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitStyle,

		NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));

		// 设置引用标签显示样式

		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(

		unitStyle, NumberFormat.getNumberInstance(), new DecimalFormat(

		"0.00%")));

		return chart;

	}

	public void setChart(JFreeChart chart) {

		this.chart = chart;

	}

	
	/**
	 * 根据标题判断需要哪一种数据
	 * @return
	 */
	private DefaultPieDataset getDataset() {
		DefaultPieDataset dataSet;
		
		switch(title){
		case "年龄统计":
			dataSet = getAgeDataSet();
			break;
		
		case "性别统计":
			dataSet = getSexDataSet();
			break;
			
		case "居住地统计":
			dataSet = getLivingPlaceDataSet();
			break;
			
		case "会员状态统计":
			dataSet = getStatusDataSet();
			break;
			
		default:
			dataSet = getAgeDataSet();
			break;
		}

		return dataSet;
	}


	/**
	 * 获取居住地数据
	 * @return
	 */
	private DefaultPieDataset getLivingPlaceDataSet() {
		DefaultPieDataset dataSet = new DefaultPieDataset();

		dataSet.setValue("华东", 35);

		dataSet.setValue("华北", 30);

		dataSet.setValue("华南", 41);

		dataSet.setValue("西部", 16);

		dataSet.setValue("海外", 21);
		
		dataSet = statisticService.getLivingPlaceData();

		return dataSet;
	}

	/**
	 * 获取年龄的数据
	 * @return
	 */
	private DefaultPieDataset getAgeDataSet() {
		DefaultPieDataset dataSet = new DefaultPieDataset();

		dataSet.setValue("小于十岁", 20);

		dataSet.setValue("十至十八岁", 8);

		dataSet.setValue("十八至三十岁", 15);

		dataSet.setValue("三十至五十岁", 15);

		dataSet.setValue("五十岁以上", 5);

		return dataSet;
	}
	
	
	/**
	 * 获取性别的数据
	 * @return
	 */
	private DefaultPieDataset getSexDataSet() {
		DefaultPieDataset dataSet = new DefaultPieDataset();

		dataSet.setValue("男性", 30);

		dataSet.setValue("女性", 36);

		return dataSet;
	}
	
	/**
	 * 获取会员状态数据
	 * @return
	 */
	private DefaultPieDataset getStatusDataSet() {
		DefaultPieDataset dataSet = new DefaultPieDataset();

		dataSet.setValue("正常", 62);
		dataSet.setValue("欠费", 15);
		dataSet.setValue("未激活", 21);

		return dataSet;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
}
