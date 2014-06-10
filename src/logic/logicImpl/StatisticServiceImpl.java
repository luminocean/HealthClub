package logic.logicImpl;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import dao.daoInterface.StatisticDao;
import logic.logicInterface.StatisticService;
import model.Coach;
import model.Place;

public class StatisticServiceImpl implements StatisticService {
	private StatisticDao statisticDao;

	
	public StatisticDao getStatisticDao() {
		return statisticDao;
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

	
	/**
	 * 获取地点的数据集用于显示
	 */
	@Override
	public DefaultCategoryDataset getPlaceData() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		List<Place> places = statisticDao.getPlaceListInOrder();
		
		for( Place place: places ){
			dataSet.addValue(place.getOccasionNumber(),place.getPlace(),place.getPlace());
		}
		
		return dataSet;
	}

	@Override
	public DefaultCategoryDataset getCoachData() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		List<Coach> coaches = statisticDao.getCoachListInOrder();
		
		for( Coach coach: coaches ){
			dataSet.addValue(coach.getOccasions().size(), coach.getName(), coach.getName());
		}
		
		return dataSet;
	}

	@Override
	public DefaultPieDataset getLivingPlaceData() {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		
		Map<String, Integer> livingPlacesMap = statisticDao.getLivingPlaceMap();
		
		Set<Entry<String, Integer>> set = livingPlacesMap.entrySet();
		
		for( Entry<String, Integer> entry: set ){
			dataSet.setValue( entry.getKey(), entry.getValue());
		}
		
		return dataSet;
	}

	@Override
	public TimeSeriesCollection getPeopleDataInMonths() {
		List<Integer> data = statisticDao.getPeopleDataInMonths(6);
		
		TimeSeries series = new TimeSeries("月份", Month.class);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 10);
		
		for( int i=0; i<6; i++ ){
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int counter = data.get(i);
			
			
			series.add(new Month(month,year), counter);
			
			cal.add(Calendar.MONTH, 1);
		}


		TimeSeriesCollection dataSet = new TimeSeriesCollection();

		dataSet.addSeries(series);
		
		return dataSet;
	}

	@Override
	public int getPeopleInNextMonth() {
		int sampleSize = 6;
		List<Integer> data = statisticDao.getPeopleDataInMonths(sampleSize);
		
		int sum = 0;
		
		for( Integer i : data ){
			sum += i;
		}
		
		int average = sum/sampleSize;
		
		return average;
	}

	@Override
	public String getHotTag() {
		String tag = statisticDao.getHotTag();
		
		return tag;
	}
}
