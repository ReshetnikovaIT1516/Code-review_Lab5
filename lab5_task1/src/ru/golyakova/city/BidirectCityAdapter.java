package ru.golyakova.city;

import ru.golyakova.main.CityInterface;

import java.util.Map;

public class BidirectCityAdapter implements CityInterface {
  private BidirectionalCity bidirectionalCity;

  public BidirectCityAdapter(BidirectionalCity city) {
    this.bidirectionalCity = city;
  }

  public BidirectionalCity getOriginalCity() {
    return bidirectionalCity;
  }

  @Override
  public String getName() {
    return bidirectionalCity.getName();
  }

  @Override
  public Map<String, Integer> getRoads() {
    return bidirectionalCity.getRoads();
  }

  @Override
  public void addRoad(CityInterface otherCity, int cost) {
    if (otherCity instanceof BidirectCityAdapter) {
      BidirectionalCity originalOtherCity = ((BidirectCityAdapter) otherCity).getOriginalCity();
      if (!bidirectionalCity.hasRoadTo(originalOtherCity.getName())) {
        bidirectionalCity.addRoad(originalOtherCity, cost);
      }
    } else if (otherCity instanceof CityAdapter) {
      City originalOtherCity = ((CityAdapter) otherCity).getOriginalCity();
      if (!bidirectionalCity.hasRoadTo(originalOtherCity.getName())) {
        bidirectionalCity.getRoads().put(originalOtherCity.getName(), cost);
      }
    }
  }

  @Override
  public void removeRoad(CityInterface otherCity) {
    if (otherCity instanceof BidirectCityAdapter) {
      BidirectionalCity originalOtherCity = ((BidirectCityAdapter) otherCity).getOriginalCity();
      bidirectionalCity.removeRoad(originalOtherCity);
    } else if (otherCity instanceof CityAdapter) {
      City originalOtherCity = ((CityAdapter) otherCity).getOriginalCity();
      if (bidirectionalCity.hasRoadTo(originalOtherCity.getName())) {
        bidirectionalCity.getRoads().remove(originalOtherCity.getName());
      }
    }
  }

  @Override
  public boolean hasRoadTo(String nameCity) {
    return bidirectionalCity.hasRoadTo(nameCity);
  }

  @Override
  public String toString() {
    return bidirectionalCity.toString();
  }
}
