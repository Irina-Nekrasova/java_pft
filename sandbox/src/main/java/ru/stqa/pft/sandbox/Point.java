package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point (double x, double y) {
    this.x=x;
    this.y=y;
  }
  public double dist (Point b) {
    return Math.sqrt(Math.pow(b.x-this.x,2)+Math.pow(b.y-this.y,2));

  }

}
