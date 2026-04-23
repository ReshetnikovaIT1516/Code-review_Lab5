package ru.golyakova.city;

import ru.golyakova.main.CityInterface;

import java.util.Map;

public class CityAdapter implements CityInterface {
  private City city;

  public CityAdapter(City city) {
    this.city = city;
  }

  public City getOriginalCity() {
    return city;
  }

  @Override
  public String getName() {
    return city.getName();
  }

  @Override
  public Map<String, Integer> getRoads() {
    return city.getRoads();
  }

  @Override
  public void addRoad(CityInterface otherCity, int cost) {
    if (otherCity instanceof CityAdapter) {
      City originalOtherCity = ((CityAdapter) otherCity).getOriginalCity(); //приводим к типу
      city.addRoad(originalOtherCity, cost);
    } else if (otherCity instanceof BidirectCityAdapter) {
      BidirectionalCity originalOtherCity = ((BidirectCityAdapter) otherCity).getOriginalCity();
      city.addRoad(originalOtherCity, cost);
    }
  }

  @Override
  public void removeRoad(CityInterface otherCity) {
    if (otherCity instanceof CityAdapter) {
      City originalOtherCity = ((CityAdapter) otherCity).getOriginalCity();
      city.removeRoad(originalOtherCity);
    } else if (otherCity instanceof BidirectCityAdapter) {
      BidirectionalCity originalOtherCity = ((BidirectCityAdapter) otherCity).getOriginalCity();
      city.removeRoad(originalOtherCity);
    }
  }

  @Override
  public boolean hasRoadTo(String nameCity) {
    return city.hasRoadTo(nameCity);
  }

  @Override
  public String toString() {
    return city.toString();
  }
}
