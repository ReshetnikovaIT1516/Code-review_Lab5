package ru.golyakova.exceptions;

public class EmptyCityNameException extends RoadSystemException {
  public EmptyCityNameException(String message) {
    super(message);
  }
}
