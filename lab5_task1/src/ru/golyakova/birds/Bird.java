package ru.golyakova.birds;

import java.util.Random;

public abstract class Bird {
  private String name;
  private static Random random = new Random();

  public Bird(String name) {
    this.name = name;
  }

  public abstract void sing();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static Random getRandom() {
    return random;
  }

  public void display() {
    System.out.println(" Я - " + name);
  }

  @Override
  public String toString() {
    return "Птица {" + name + "}";
  }
}
