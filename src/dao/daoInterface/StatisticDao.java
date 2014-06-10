package dao.daoInterface;

import java.util.List;
import java.util.Map;

import model.Coach;
import model.Place;

public interface StatisticDao {

	List<Place> getPlaceListInOrder();

	List<Coach> getCoachListInOrder();

	Map<String, Integer> getLivingPlaceMap();

	List<Integer> getPeopleDataInMonths(int pastMonths);

	String getHotTag();
}
