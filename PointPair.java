import java.util.*;
import java.awt.geom.Point2D;

public class PointPair{
  private Point2D.Double [] points;
  private Point2D.Double point1;
  private Point2D.Double point2;
  
  public PointPair (Point2D.Double [] points, Point2D.Double point1, Point2D.Double point2) {
    this.points = points;
    this.point1 = point1;
    this.point2 = point2;
    //Point2D.Double [] b = new Point2D.Double [points.length];
  }
  
  public PointPair closestPoints;
  
  public Point2D.Double getPoint1 () {
    return point1;
  }
  
  public Point2D.Double getPoint2 () {
    return point2;
  }
  
  public void setPoint1(Point2D.Double x) {
    point1 = x;
  }
  
  public void setPoint2(Point2D.Double x) {
    point2 = x;
  }
  
  /* 
    Isaiah: Function added to avoid stack overflow when initializing
    the cloestPOints variable
  */
  public PointPair getClosestPoints () {
    if (closestPoints == null) {
      closestPoints = new PointPair(points, point1, point2);
    }
     return closestPoints;

  }
  /// SORTING ALGORITHMS
  
  
  public void mergeSortVertical(Point2D.Double [] points, Point2D.Double [] b, int lo, int hi) {
    if (hi <= lo) return;
    else {
      int mid = (lo+hi)/2;
      mergeSortVertical(points, b, lo, mid);
      mergeSortVertical(points, b, mid+1, hi);
      mergeVertical(points, lo, mid, mid+1, hi);
    }
    Point2D.Double []t = points;
    points = b;
    b = t;
  }
  
 public void mergeVertical (Point2D.Double [] points, int lo1, int hi1, int lo2, int hi2) {
 // lo1 is the lowes
   Point2D.Double []b = new Point2D.Double[hi2-lo1 +1];
   int i = lo1;    //next position in bottom half
   int j = lo2;    // next position in top half
   int k = 0;  // position in array b that gets filled. 
   while (i <= hi1 && j<= hi2) {
     if (points[i].getX() < points[j].getX()) b[k++] = points[i++];
     else b[k++] = points [j++];
   }
   while (i <= hi1) { 
     b[k++] = points[i++]; 
   }
   while (j <= hi2) {
     b[k++] = points[j++];
   }
   for (int l = 0; l < k; l++) points[lo1++] = b[l]; 
   
 }
 
 public Point2D.Double [] SortVertical () {
   Point2D.Double [] b = new Point2D.Double [points.length];
   this.mergeSortVertical(points, b, 0, points.length-1);
   return points;
 }
 
 
 
 public void mergeSortHorizontal(Point2D.Double [] points, Point2D.Double [] b, int lo, int hi) {
   if (hi <= lo) return;
   else {
     int mid = (lo+hi)/2;
     mergeSortHorizontal(points, b, lo, mid);
     mergeSortHorizontal(points, b, mid+1, hi);
     mergeHorizontal(points, lo, mid, mid+1, hi);
   }
   Point2D.Double []t = points;
   points = b;
   b = t;
 }
 
 
  public void mergeHorizontal (Point2D.Double [] points, int lo1, int hi1, int lo2, int hi2) {
    // lo1 is the lowes
    Point2D.Double []b = new Point2D.Double[hi2-lo1 +1];
    int i = lo1;    //next position in bottom half
    int j = lo2;    // next position in top half
    int k = 0;  // position in array b that gets filled. 
    while (i <= hi1 && j<= hi2) {
      if (points[i].getY() < points[j].getY()) b[k++] = points[i++];
      else b[k++] = points [j++];
    }
    while (i <= hi1) { 
      b[k++] = points[i++]; 
    }
    while (j <= hi2) {
      b[k++] = points[j++];
    }
    for (int l = 0; l < k; l++) points[lo1++] = b[l]; 
    
  }
  
  public Point2D.Double [] SortHorizontal () {
    Point2D.Double [] b = new Point2D.Double [points.length];
    this.mergeSortHorizontal(points, b, 0, points.length-1);
    return points;
  }
  
  
  // BINARY SEARCH
  public int BinarySearch (Point2D.Double [] a, Point2D.Double b, int min, int max) {
   // int pos = 0;
    if (max < min) return -1;
    int mid = (min+max)/2;
    if (b.equals(a[mid])) return mid;
    else if (b.getY() < a[mid].getY()) return BinarySearch(a, b, min, mid-1);
    else return BinarySearch(a,b, mid+1, max);
    //return  pos;
  } 
  
  
  /// CREATING AN ARRAY TO MATCH XSORTED WITH YSORTED
  
  public int [] MatchYtoX (Point2D.Double [] x, Point2D.Double [] y) {   //Gives an array that has for all the elements in SortedX, the order they appear in SortedY
    int [] matched = new int[x.length];
    for (int i = 0; i < x.length; i++) {
      matched[i] = BinarySearch(y, x[i], 0, y.length-1);
    }
    return matched;
  }
      
    
  
  
  
  /// CALCULATING DISTANCE BETWEEN POINTS
  
  public double CalcDist (Point2D.Double p1, Point2D.Double p2) {
    System.out.println("reached here and have access to " + p2 + "and also " + p1);
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }
  
  public PointPair ClosestOfThreePoints (Point2D.Double [] p) {
    double shortDist;
    PointPair closestPoints = new PointPair(p, new Point2D.Double(0,0), new Point2D.Double(0,0));
    if (p.length == 3) {
      System.out.println(p[0]);
      System.out.println(p[1]);
      double a = Math.abs(CalcDist (p[0], p[1]));
      double b = Math.abs(CalcDist (p[1], p[2]));
      double c = Math.abs(CalcDist (p[2], p[0]));
      shortDist = Math.min (Math.min(a,b), c);
        if (shortDist == a) {
          closestPoints.point1 = p[0];
          closestPoints.point2 = p[1];  
        }
        
        else if (shortDist == b) {
          closestPoints.point1 = p[1];
          closestPoints.point2 = p[2];
        }
        
        else {
          closestPoints.point1 = p[0];
          closestPoints.point2 = p[2];
        }
      }
    
    else if (p.length == 2) {
      shortDist = Math.abs(CalcDist (p[0], p[1])); 
      if (shortDist < distance) {
        closestPoints.point1 = p[0];
        closestPoints.point2 = p[1];
      }
    }
    return closestPoints;
  }
  
  
  
  
  
  /// THE RECURSIVE FUNCTION
  
   double distance = Double.POSITIVE_INFINITY;
  // Point2D.Double p1 = new Point2D.Double(0,0);
  // Point2D.Double p2 = new Point2D.Double(0,0);
   

   
   
   
   public void ClosestPoints (Point2D.Double [] sortedX, Point2D.Double [] sortedY, /*PointPair closestPoints*/) {

   Point2D.Double [] firstHalf;
   Point2D.Double [] secondHalf;
   Point2D.Double [] firstHalfY;
   Point2D.Double [] secondHalfY;
   //PointPair left =  new PointPair(sortedX, new Point2D.Double(0,0), new Point2D.Double(0,0));
   //PointPair right =  new PointPair(sortedX, new Point2D.Double(0,0), new Point2D.Double(0,0));
   /*Point2D.Double left1 = new Point2D.Double(0,0);
   Point2D.Double right1 = new Point2D.Double(0,0);
   Point2D.Double left2 = new Point2D.Double(0,0);
   Point2D.Double right2 = new Point2D.Double(0,0); */
   int mid = sortedX.length/2-1;
   double midx = sortedX[mid].getX();   //left hand side will have one less than right han side in case of odd numbers
   System.out.println(mid);
   System.out.println("hi");
   
   
   if (mid == 0) {
     System.out.println("sortedX length is " + sortedX.length);
     System.out.println("Running ClosestOfThreePoints");
     closestPoints = ClosestOfThreePoints(sortedX);   //maybe i should return instead
   }
   
   else  {   
        //Preparing the sub arrays from X   O(2n) = O(n)
     firstHalf = new Point2D.Double[mid];
     for (int i = 0; i < mid; i++) {
       firstHalf[i] = sortedX[i];
       System.out.println("making first half");
      }
      
      secondHalf = new Point2D.Double[mid+1];
      for (int j = mid; j<secondHalf.length; j++) {
        secondHalf[j] = sortedX[j];
        System.out.println("making second half");
      }
      
      //Preparing the sub arrays from Y 
      
      firstHalfY = new Point2D.Double[firstHalf.length];
      int current1 = 0;
      for (int i = 0; i < sortedY.length; i++) {
        if ( sortedY[i].getX() <= midx) {
          firstHalfY[current1] = sortedY[i];
          current1++;
          System.out.println("making furst half of Y");
          
        }
      }
      
      secondHalfY = new Point2D.Double[secondHalf.length];
      int current2 = 0;
      for (int i = 0; i < sortedY.length; i++) {
        if ( sortedY[i].getX() > midx) {
          firstHalfY[current2] = sortedY[i];
          current2++;
          System.out.println("making second half of Y");
        }
      }
   
      
      PointPair1 = ClosestPoints(firstHalf, firstHalfY, left);
      ClosestPoints(secondHalf, secondHalfY, right);
      
   return MinimumDistance(ClosestPoints(first half), ClosestPoints(second half));
   }
   
   //Figuring out the shortest distance
      double distLeft = Math.abs(CalcDist(left.getPoint1(), left.getPoint2()));
      double distRight = Math.abs(CalcDist(right.getPoint1(), right.getPoint2()));
      boolean left_bool = false;
      boolean right_bool = false;
      double shortDist;
      if (distLeft <= distRight) {
        left_bool = true;
        shortDist = distLeft;
      }
      
      else {
        right_bool = true;
        shortDist = distRight;
      }
      
   //Creating values that fall within the corridor of x-coordinates
      Point2D.Double [] sortedBoundaryLeft = new Point2D.Double[sortedX.length/2 + 1];   // it will always be in half. +1 to make sure there isn't ever an overflow
      Point2D.Double [] sortedBoundaryRight = new Point2D.Double[sortedX.length/2 + 1];
      int currLeft = 0;
      int currRight = 0;
      for (int i = 0; i < sortedY.length; i++) {
        if (sortedY[i].getX() <= midx) {    
          sortedBoundaryLeft[currLeft] = sortedY[i];
          currLeft++;
        }     
        else {
          sortedBoundaryRight[currRight] = sortedY[i];
          currRight++;
        }
      }
      
      
      //Callculating the shortest distance based on sorted by Y
      for (int i = 0; i< sortedBoundaryLeft.length; i++) {
        int j = 0;
        while (sortedBoundaryLeft[i].getY() - sortedBoundaryRight[j].getY() < shortDist) {
          shortDist = Math.abs(CalcDist(sortedBoundaryLeft[i], sortedBoundaryRight[j]));
          closestPoints.setPoint1(sortedBoundaryLeft[i]);
          closestPoints.setPoint2(sortedBoundaryRight[j]);
          j++;
        }
      }    
   }
 }



