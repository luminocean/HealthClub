package logic.logicInterface;

import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeriesCollection;

public interface IStatisticService {

	DefaultCategoryDataset getPlaceData();

	DefaultCategoryDataset getCoachData();

	DefaultPieDataset getLivingPlaceData();

	TimeSeriesCollection getPeopleDataInMonths();

	int getPeopleInNextMonth();


	String getHotTag();
}
