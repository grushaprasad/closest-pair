import java.util.*;
import java.awt.geom.Point2D;

public class sort {
  
  private Point2D.Double[] points;
  private Point2D.Double [] b;
  
  public sort (Point2D.Double[] points) {
    this.points = points;
  }
  
  
  
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
  
  
  
  
 /* public Point2D.Double[] sortVertical() {   //sorts by x-axis
    Point2D.Double [] x = new Point2D.Double[points.length];
  } */
}