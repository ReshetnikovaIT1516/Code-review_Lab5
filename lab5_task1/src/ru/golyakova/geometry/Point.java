package ru.golyakova.geometry;

public class Point implements Cloneable {
  private double x;
  private double y;

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  String packageInfo;
  protected int protectedId;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
    this.packageInfo = "default";
    this.protectedId = 0;
  }

  //package-private конструктор
  Point(double x, double y, String info) {
    this.x = x;
    this.y = y;
    this.packageInfo = info;
    this.protectedId = 0;
  }

  protected Point(double x, double y, int id) {
    this.x = x;
    this.y = y;
    this.packageInfo = "default";
    this.protectedId = id;
  }

  @Override
  public String toString() {
    return "{" + x + ";" + y + "}";
  }

  @Override
  public Point clone() {
    try {
      return (Point) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError("Клонирование не поддерживается");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    //приведение типа и сравнение
    Point point = (Point) obj;

    return Double.compare(this.x, point.x) == 0 && Double.compare(this.y, point.y) == 0;
  }
}
