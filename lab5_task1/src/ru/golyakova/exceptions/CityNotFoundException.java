package ru.golyakova.exceptions;

public class CityNotFoundException extends RoadSystemException {
  public CityNotFoundException(String message) {
    super(message);
  }
}
