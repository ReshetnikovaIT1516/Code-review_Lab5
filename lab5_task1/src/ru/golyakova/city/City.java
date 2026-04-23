package ru.golyakova.city;

import ru.golyakova.exceptions.*;
import ru.golyakova.validate.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
  private String name;
  private Map<String, Integer> roads;

  //свойства
  public String getName() {
    return name;
  }

  public Map<String, Integer> getRoads() {
    return new HashMap<>(roads);
  }

  private void setName(String name) {
    Validator.validCityName(name);
    this.name = name.trim();
  }

  //конструктор
  public City(String name) {
    setName(name);
    this.roads = new HashMap<>();
  }

  public void addRoad(City city, int cost) {
    Validator.validCity(city, this);
    Validator.validCost(cost);
    if (roads.containsKey(city.getName())) {
      throw new RoadAlreadyExistsException("Дорога между " + this.name + " и " + city.getName() + " уже существует");
    }
    roads.put(city.getName(), cost);
  }

  public void removeRoad(City city) {
    Validator.validCity(city, this);
    if (!roads.containsKey(city.getName())) {
      throw new RoadNotFoundException("Дороги между " + this.name + " и " + city.getName() + " не существует");
    }
    roads.remove(city.getName());
  }

  public boolean hasRoadTo(String name) {
    return roads.containsKey(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof City)) {
      return false;
    }
    City other = (City) obj;
    return Objects.equals(this.roads, other.roads);
  }

  @Override
  public String toString() {
    return "Город " + name;
  }
}
