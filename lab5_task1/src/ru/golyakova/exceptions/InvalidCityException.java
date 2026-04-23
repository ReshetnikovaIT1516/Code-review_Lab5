package ru.golyakova.exceptions;

public class InvalidCityException extends RoadSystemException { //пользовательское исключение
  public InvalidCityException(String message) {
    super(message);
  }
}
