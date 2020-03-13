package ru.stqa.pft.sandbox;

public class Homework2 {
  public static void main(String[] args) {

    Point a=new Point(2,4);
    Point b=new Point(3,6);

    System.out.println("Расстояние между точками равно " + dist(a,b));

  }

  public static double dist (Point a, Point b) {
    return Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));

  }
}
