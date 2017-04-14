package Class;

import java.util.Collections;
import java.util.List;

public class CircleUtils {
	
	
	public static class CircleCenter
	  {
	    Point center;
	    double radius;
	    
	    public CircleCenter(Point center, double radius)
	    {
	      this.center = center; this.radius = radius;
	    }
	    @Override 
	    public String toString()
	    {
	      return new StringBuilder().append("Center= ").append(center).append(", r=").append(radius).toString();
	    }
	  }
	
	public static CircleCenter FindCircleCenter(Point A, Point B, Point C)
	  {
//		NEED TO ADD IS_PERPENDICALUR METHOD FOR POINTS VALIDATION 
		double yDelta_a = B.y - A.y;
		double xDelta_a = B.x - A.x;
		double yDelta_b = C.y - B.y;
		double xDelta_b = C.x - B.x;

	    double aSlope = yDelta_a/xDelta_a;
	    double bSlope = yDelta_b/xDelta_b; 
	    
	    final double center_x = (aSlope*bSlope*(A.y - C.y) + bSlope*(A.x + B.x) 
	    		- aSlope*(B.x+C.x) )/(2* (bSlope-aSlope) );
	    final double center_y = -1*(center_x - (A.x+B.x)/2)/aSlope +  (A.y+B.y)/2;
	    final double radius = 
		       Math.sqrt( Math.pow(A.x - center_x,2) + Math.pow(A.y-center_y,2));
	    
	    
	    return new CircleCenter(new Point((int)center_x, (int)center_y), radius);
	    
	  }
	
	public static boolean IsPointInCircle(CircleData data, Point A)
	{
		final double dist = Math.sqrt( Math.pow(A.x - data.center.x,2) + Math.pow(A.y-data.center.y,2));
		
		if ((dist == data.radius) || (dist < data.radius))
		{
			return true;
		}
		
		return false;
		
	}
	
	
	public static CircleData FindCircleEdges(List<Point> pointsSet, boolean leftBorder)
	{
		Point upEdge = null;
		Point middleEdge = null;
		Point downEdge = null;
		Point center = null;
		double radius = 0;

		Collections.sort(pointsSet, (Point p1, Point p2) -> p1.y-p2.y);
		
		upEdge = pointsSet.get(0);
		downEdge = pointsSet.get(pointsSet.size()-1);
		
		Collections.sort(pointsSet, (Point p1, Point p2) -> p1.x-p2.x);
		if(leftBorder)
		{
			middleEdge = pointsSet.get(pointsSet.size()-1);
		}
		else
		{
			middleEdge = pointsSet.get(0);
		}
		
		System.out.println();
		System.out.println("upEdge - " + upEdge.x +" "+upEdge.y);
		System.out.println("middleEdge - " + middleEdge.x+" "+upEdge.y);
		System.out.println("downEdge - " + downEdge.x+" "+downEdge.y);
		
		CircleCenter centerdata = FindCircleCenter(upEdge, middleEdge, downEdge);
		center = centerdata.center;
		radius = centerdata.radius;
		
		
		System.out.println("center - " + center.x+" "+center.y);
		System.out.println("radius - " + radius);
		return new CircleData(upEdge, middleEdge, downEdge, center, radius);
		
	}
}