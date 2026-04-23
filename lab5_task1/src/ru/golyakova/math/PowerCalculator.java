package ru.golyakova.math;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class PowerCalculator {
  public static double calculatePower(String xStr, String yStr) {
    int x = parseInt(xStr);
    int y = parseInt(yStr);
    return pow(x, y);
  }

  public static double calculatePowerSafe(String xStr, String yStr) {
    try {
      return calculatePower(xStr, yStr);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Ошибка: x и y должны быть целыми числами");
    }
  }
}
