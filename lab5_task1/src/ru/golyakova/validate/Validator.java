package ru.golyakova.validate;

import ru.golyakova.exceptions.*;
import ru.golyakova.city.*;
import ru.golyakova.main.CityInterface;

import java.util.*;

public class Validator {
  public static void validCityName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new EmptyCityNameException("Название города не может быть пустым");
    }
  }

  public static void validCity(City city, City currentCity) {
    if (city == null) {
      throw new InvalidCityException("Город не может быть 0");
    }
    if (city == currentCity) {
      throw new InvalidCityException("Нельзя создать дорогу из города в него же");
    }
  }

  public static void validCost(int cost) {
    if (cost <= 0) {
      throw new InvalidNumberException("Стоимость не может быть положительной");
    }
  }

  public static int validPositiveNumber(String input) {
    try {
      int number = Integer.parseInt(input);
      if (number <= 0) {
        throw new InvalidNumberException("Значение должно быть положительным");
      }
      return number;
    } catch (NumberFormatException e) {
      throw new InvalidNumberException("Ошибка, введите целое положительное число");
    }
  }

  public static void validCityExist(City city, String name) {
    if (city == null) {
      throw new CityNotFoundException("Город " + name + " не найден");
    }
  }

  public static void validEnoughCity(int countCity, int required, String operation) {
    if (countCity < required) {
      throw new NotEnoughCityException("Для операции " + operation + " нужно минимум " + required + " города");
    }
  }

  public static void validDifferentCity(String cityName1, String cityName2) {
    if (cityName1.equals(cityName2)) {
      throw new SameCityException("Нельзя создать дорогу из города " + cityName1 + " в себя");
    }
  }

  public static City createCity(String name, Map<String, City> cities) {
    validCityName(name);
    String trimm = name.trim();
    if (cities.containsKey(trimm)) {
      throw new RoadSystemException("Город " + trimm + " уже существует");
    }
    City city = new City(trimm);
    cities.put(trimm, city);
    return city;
  }

  public static City getValidCity(String name, Map<String, City> cities) {
    validCityName(name);
    City city = cities.get(name.trim());
    validCityExist(city, name);
    return city;
  }

  public static String readAndValidCityName(Scanner scanner, String prompt) {
    System.out.print(prompt);
    String name = scanner.nextLine();
    validCityName(name);
    return name.trim();
  }

  public static double readDouble(Scanner scanner, String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        return scanner.nextDouble();
      } catch (Exception e) {
        System.out.println("Ошибка, введите корректное число");
        scanner.nextLine();
      }
    }
  }

  public static City createCityWithRoad(String name, Map<String, Integer> initialRoad, Map<String, City> existingCity) {
    String trimm = name.trim();
    Validator.validCityName(trimm);

    if (existingCity.containsKey(trimm)) {
      throw new RoadSystemException("Город " + trimm + " уже существует");
    }
    City city = new City(trimm);
    existingCity.put(trimm, city);
    for (Map.Entry<String, Integer> roadEntry : initialRoad.entrySet()) {
      String cityName = roadEntry.getKey();
      int cost = roadEntry.getValue();
      City otherCity = existingCity.get(cityName);
      if (otherCity == null) {
        throw new CityNotFoundException("Город " + cityName + " не найден");
      }
      city.addRoad(otherCity, cost);
    }
    return city;
  }

  public static BidirectionalCity createTwoWayCity(String name, Map<String, City> cities) {
    validCityName(name);
    String trimm = name.trim();
    if (cities.containsKey(trimm)) {
      throw new RoadSystemException("Город " + trimm + " уже существует");
    }
    BidirectionalCity city = new BidirectionalCity(trimm);
    cities.put(trimm, city);
    return city;
  }

  public static BidirectionalCity createTwoWayCityWithRoads(String name, Map<String, Integer> initialRoads, Map<String, City> existingCities) {
    String trimm = name.trim();
    Validator.validCityName(trimm);

    if (existingCities.containsKey(trimm)) {
      throw new RoadSystemException("Город " + trimm + " уже существует");
    }

    BidirectionalCity city = new BidirectionalCity(trimm);
    existingCities.put(trimm, city);

    for (Map.Entry<String, Integer> roadEntry : initialRoads.entrySet()) {
      String cityName = roadEntry.getKey();
      int cost = roadEntry.getValue();
      City otherCity = existingCities.get(cityName);
      if (otherCity == null) {
        throw new CityNotFoundException("Город " + cityName + " не найден");
      }
      city.addRoad(otherCity, cost);
    }
    return city;
  }

  public static CityInterface createCity(String name, boolean isTwoWay) {
    if (isTwoWay) {
      return new BidirectCityAdapter(new BidirectionalCity(name));
    } else {
      return new CityAdapter(new City(name));
    }
  }

  // НОВЫЙ МЕТОД: создание города с набором дорог
  public static City createCityWithRoads(String name, Map<String, Integer> roads, Map<String, City> existingCities) {
    validCityName(name);
    String trimmed = name.trim();
    if (existingCities.containsKey(trimmed)) {
      throw new RoadSystemException("Город " + trimmed + " уже существует");
    }

    City city = new City(trimmed);
    existingCities.put(trimmed, city);

    for (Map.Entry<String, Integer> entry : roads.entrySet()) {
      String neighborName = entry.getKey();
      int cost = entry.getValue();
      City neighbor = existingCities.get(neighborName);
      if (neighbor == null) {
        throw new CityNotFoundException("Город " + neighborName + " не найден при создании дорог для " + trimmed);
      }
      city.addRoad(neighbor, cost);
    }
    return city;
  }
}