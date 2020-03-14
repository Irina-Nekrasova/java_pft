package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDistPoint {

  @Test
  public void testDist () {
    Point a=new Point(0,0);
    Point b=new Point(100,100);
    Assert.assertEquals(a.dist(b),141.42);

    Point q=new Point(2,4);
    Point w=new Point(3,6);
    Assert.assertEquals(q.dist(w),2.24);
  }

}
