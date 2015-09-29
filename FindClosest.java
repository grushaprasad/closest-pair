import java.util.*;
import java.awt.geom.Point2D;

public class FindClosest
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);

 // read number of points and make arrays
    int numPoints = scan.nextInt();
    Point2D.Double[] points = new Point2D.Double[numPoints];
    Point2D.Double[] pointsX = new Point2D.Double[numPoints];
    Point2D.Double[] pointsY = new Point2D.Double[numPoints];

 // read points and add to array
    for (int p = 0; p < numPoints; p++)
    {
      points[p] = new Point2D.Double(scan.nextDouble(), scan.nextDouble());
    }
    
    for (int p = 0; p< points.length; p++) {
      pointsX[p] = points[p];
      pointsY[p] = points[p];
    }
      
    Point2D.Double point1 = new Point2D.Double(1,1);
    Point2D.Double point2 = new Point2D.Double(1,1);
    PointPair x = new PointPair(pointsX, point1, point2);
    PointPair z = x.getClosestPoints();
    System.out.println("This is the first thing to be printed that I am testing now" + z.getPoint1());
    PointPair y = new PointPair(pointsY, point1, point2);
    //PointPair z = new PointPair(points, point1, point2);
 
    //System.out.println(Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2)));

    
    
    Point2D.Double[] sortedX = x.SortVertical() ;
    Point2D.Double [] sortedY = y.SortHorizontal();   
    System.out.println();
    System.out.println();
    PointPair testing = x.ClosestOfThreePoints(sortedX);
    System.out.println("Point 1 is " + testing.getPoint1());
    System.out.println("Point 2 is " + testing.getPoint2());
    
   x.ClosestPoints(sortedX, sortedY, z);
    double distance = Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2)); 
    
    System.out.println("The closest pair were" + point1 + " and " + point2 + ". And the distance between them was " + distance); 
  }
}