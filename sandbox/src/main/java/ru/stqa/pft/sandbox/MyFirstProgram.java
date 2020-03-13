package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
   hello("world");
   double l=8;
   double a=4;
   double b=6;
    System.out.println("Площадь квадарата со стороной " + l + " = " + area(l));
    System.out.println("Площадь прямоугольника со сторонами " + a  + " и " + b + " = " + area(a,b));
    }

   public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
   }

   public static double area(double l) {
    return l*l;
   }

  public static double area(double a,double b) {
    return a*b;
  }

}