package ru.golyakova.main;

import java.util.Map;

public interface CityInterface {
  String getName();

  Map<String, Integer> getRoads();

  void addRoad(CityInterface city, int cost);

  void removeRoad(CityInterface city);

  boolean hasRoadTo(String nameCity);
}
