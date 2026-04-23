package ru.golyakova.birds;

import ru.golyakova.validate.Validator;

public class Parrot extends Bird {
  private String text;

  public Parrot(String text) {
    super("Попугай");
    Validator.validCityName(text);
    this.text = text.trim();
  }

  @Override
  public void sing() {
    if (text.length() == 1) {
      System.out.println(text);
    } else {
      int n = getRandom().nextInt(text.length()) + 1;
      String song = text.substring(0, n); //извлекаем подстроку
      System.out.println(song);
    }
  }

  public String getText() {
    return text;
  }

  @Override
  public void display() {
    System.out.println(" Я - Попугай, я пою: " + text);
  }
}
