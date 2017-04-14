package Class;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectangleUtils 
{
	public static RectangleEdges FindRectangleEdges(List<Point> pointsSet)
	{
	     
	      List<Point> possibleLeftUpEdgePoints = new ArrayList<Point>();
	      List<Point> possibleLeftDownEdgePoints = new ArrayList<Point>(); 
	      List<Point> possibleRightUpEdgePoints = new ArrayList<Point>();
	      List<Point> possibleRightDownEdgePoints = new ArrayList<Point>();
	      
	      for(int i = 0; i < pointsSet.size(); i++)
	      {
	    	 // System.out.println("x - "+pointsSet.get(i).x + " y - "+pointsSet.get(i).y);
	      }
	      //find left up edge, minimum x and minimum y
	      Point leftUpEdge = (Point)pointsSet.get(0);
  
	      Collections.sort(pointsSet, (Point p1, Point p2) -> p1.x-p2.x);
	      
	      for(int i = 0; i < 20; i++)
	      {
	    	  possibleLeftUpEdgePoints.add(pointsSet.get(i));
	      }
	      Collections.sort(possibleLeftUpEdgePoints, (Point p1, Point p2) -> p1.y-p2.y);
	      leftUpEdge = possibleLeftUpEdgePoints.get(0);
	      
	      //find right up edge, maximum x and minimum y
	      Point rightUpEdge = (Point)pointsSet.get(0);

	      Collections.sort(pointsSet, (Point p1, Point p2) -> p1.x-p2.x);
	      
	      for(int i = pointsSet.size()-1; i > pointsSet.size() - 20; i--)
	      {
	    	  possibleRightUpEdgePoints.add(pointsSet.get(i));
	      }
	      Collections.sort(possibleRightUpEdgePoints, (Point p1, Point p2) -> p1.y-p2.y);
	      rightUpEdge = possibleRightUpEdgePoints.get(0);


	      //find left down edge, minimum x maximum y
	      Collections.sort(pointsSet, (Point p1, Point p2) -> p1.x-p2.x);
	      
	      Point leftDownEdge = (Point)pointsSet.get(0);
	      for(int i = 1; i < 20; i++)
	      {
	    	  possibleLeftDownEdgePoints.add(pointsSet.get(i));
	      }
	      Collections.sort(possibleLeftDownEdgePoints, (Point p1, Point p2) -> p1.y-p2.y); 
	      leftDownEdge = possibleLeftDownEdgePoints.get(possibleLeftDownEdgePoints.size()-1);
	      
	      //find right down edge, maximum x maximum y
	      Collections.sort(pointsSet, (Point p1, Point p2) -> p1.x-p2.x);
	      Point rightDownEdge = (Point)pointsSet.get(0);
	      for(int i = pointsSet.size()-1; i > pointsSet.size() - 20; i--)
	      {
	    	  possibleRightDownEdgePoints.add(pointsSet.get(i));
	      }
	      Collections.sort(possibleRightDownEdgePoints, (Point p1, Point p2) -> p1.y-p2.y); 
	      rightDownEdge = possibleRightDownEdgePoints.get(possibleLeftDownEdgePoints.size()-1);
	      
	      return new RectangleEdges(leftUpEdge, leftDownEdge, rightDownEdge, rightUpEdge);
	 }
	
	public static boolean IsRectangleContainsPoint(List<Point> pointsSet, Point checkPpoint)
	{
		RectangleEdges rectEdges = FindRectangleEdges(pointsSet);
		int width = rectEdges.leftDownEdge.y - rectEdges.leftUpEdge.y;
		int height = rectEdges.rightUpEdge.x - rectEdges.leftUpEdge.x;
		Rectangle rect = new Rectangle(rectEdges.leftUpEdge.x, rectEdges.leftUpEdge.y, width, height );
		rect.contains(checkPpoint.x, checkPpoint.y);
		
		return false;
	}
	
	
}
