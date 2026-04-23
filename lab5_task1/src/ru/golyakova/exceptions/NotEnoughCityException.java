package ru.golyakova.exceptions;

public class NotEnoughCityException extends RuntimeException {
  public NotEnoughCityException(String message) {
    super(message);
  }
}
