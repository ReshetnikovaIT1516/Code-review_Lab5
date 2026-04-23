package ru.golyakova.route;

import ru.golyakova.city.City;
import ru.golyakova.exceptions.InvalidCityException;

import java.util.*;

public class Route {
  private City startCity;
  private City endCity;
  private final Map<String, City> cityMap;

  public Route(City startCity, City endCity, Map<String, City> cityMap) {
    if (startCity == null || endCity == null) {
      throw new InvalidCityException("Город начала и город конца не могут быть null");
    }
    if (cityMap == null) {
      throw new IllegalArgumentException("Карта городов не может быть null");
    }
    this.startCity = startCity;
    this.endCity = endCity;
    this.cityMap = cityMap;
  }

  public City getStartCity() {
    return startCity;
  }

  public City getEndCity() {
    return endCity;
  }

  public void setStartCity(City startCity) {
    if (startCity == null) {
      throw new InvalidCityException("Город начала не может быть null");
    }
    this.startCity = startCity;
  }

  public void setEndCity(City endCity) {
    if (endCity == null) {
      throw new InvalidCityException("Город конца не может быть null");
    }
    this.endCity = endCity;
  }

  // Алгоритм Дейкстры для поиска кратчайшего пути
  public City[] getPath() {
    if (startCity.equals(endCity)) {
      return new City[]{startCity};
    }

    Map<City, Integer> distance = new HashMap<>();
    Map<City, City> previous = new HashMap<>();
    Set<City> visited = new HashSet<>();
    PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

    // Инициализация расстояний
    for (City city : cityMap.values()) {
      distance.put(city, Integer.MAX_VALUE);
    }
    distance.put(startCity, 0);
    queue.add(startCity);

    while (!queue.isEmpty()) {
      City current = queue.poll();

      if (visited.contains(current)) {
        continue;
      }
      visited.add(current);

      if (current.equals(endCity)) {
        break;
      }

      for (Map.Entry<String, Integer> road : current.getRoads().entrySet()) {
        String neighborName = road.getKey();
        int cost = road.getValue();
        City neighbor = cityMap.get(neighborName);

        if (neighbor == null || visited.contains(neighbor)) {
          continue;
        }

        int newDist = distance.get(current) + cost;
        if (newDist < distance.get(neighbor)) {
          distance.put(neighbor, newDist);
          previous.put(neighbor, current);
          queue.add(neighbor);
        }
      }
    }

    // Если путь не найден
    if (distance.get(endCity) == Integer.MAX_VALUE) {
      return new City[0];
    }

    // Восстановление пути
    List<City> path = new ArrayList<>();
    City step = endCity;
    while (step != null) {
      path.add(step);
      step = previous.get(step);
    }
    Collections.reverse(path);
    return path.toArray(new City[0]);
  }

  @Override
  public String toString() {
    City[] path = getPath();
    if (path.length == 0) {
      return "Маршрут из " + startCity.getName() + " в " + endCity.getName() + " не найден";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < path.length; i++) {
      if (i > 0) sb.append(" → ");
      sb.append(path[i].getName());
    }
    return sb.toString();
  }
}