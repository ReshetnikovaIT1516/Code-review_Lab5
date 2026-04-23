package ru.golyakova.geometry;

public final class Point3D extends Point2D {
  private final double z;

  public double getZ() {
    return z;
  }

  public Point3D(double x, double y, double z) {
    super(x, y);
    this.z = z;
  }

  @Override
  public String toString() {
    return "{" + getX() + ";" + getY() + ";" + z + "}";
  }
}
