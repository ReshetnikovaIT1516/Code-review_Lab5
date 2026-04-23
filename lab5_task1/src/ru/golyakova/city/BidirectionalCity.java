package ru.golyakova.city;

import ru.golyakova.exceptions.*;
import ru.golyakova.validate.Validator;

public final class BidirectionalCity extends City {
  public BidirectionalCity(String name) {
    super(name);
  }

  private void superAddRoad(City city, int cost) {
    super.addRoad(city, cost);
  }

  private void superRemoveRoad(City city) {
    super.removeRoad(city);
  }

  @Override
  public void addRoad(City city, int cost) {
    Validator.validCity(city, this);
    Validator.validCost(cost);

    // Проверяем, существует ли уже дорога
    if (this.hasRoadTo(city.getName())) {
      throw new RoadAlreadyExistsException("Дорога между " + this.getName() + " и " + city.getName() + " уже существует");
    }
    super.addRoad(city, cost);
    if (city instanceof BidirectionalCity) {
      BidirectionalCity twoWayCity = (BidirectionalCity) city;
      try {
        twoWayCity.superAddRoad(this, cost);
      } catch (RoadAlreadyExistsException e) {
        super.removeRoad(city);
        throw new RoadSystemException("Не удалось создать двустороннюю дорогу: " + e.getMessage());
      }
    }
  }

  @Override
  public void removeRoad(City city) {
    Validator.validCity(city, this);
    if (!this.hasRoadTo(city.getName())) {
      throw new RoadNotFoundException("Дороги между " + this.getName() + " и " + city.getName() + " не существует");
    }
    super.removeRoad(city);
    if (city instanceof BidirectionalCity) {
      BidirectionalCity twoWayCity = (BidirectionalCity) city;
      if (twoWayCity.hasRoadTo(this.getName())) {
        twoWayCity.superRemoveRoad(this);
      }
    }
  }


  @Override
  public final String toString() {
    return "Город " + this.getName();
  }

}
