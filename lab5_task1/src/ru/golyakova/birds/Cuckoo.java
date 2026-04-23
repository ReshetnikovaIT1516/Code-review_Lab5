package ru.golyakova.birds;

public class Cuckoo extends Bird {
  public Cuckoo() {
    super("Кукушка");
  }

  @Override
  public void sing() {
    int count = getRandom().nextInt(10) + 1;
    for (int i = 0; i < count; i++) {
      System.out.print("ку-ку");
      if (i < count - 1) {
        System.out.print(" ");
      }
    }
    System.out.println();
  }
}
