package Class;

import java.util.List;

public class AreasOfInterest extends TriangleEdges{
	
	int pixelsFactor;
	 List<Point> upperEdge; //area 3 in the book ,
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> rightEdge2; //area 4 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> leftEdge2; //area 5 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> triangleArea; //area 6 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom  vertex 
	 */
	 List<Point> leftSide; //area 7 in the book 
	/*
	 the first argument is the upper vertex the second vertex is the bottom vertex and 
	 the third vertex is the middle  vertex 
	 */
	 List<Point> rightSide; //area 8 in the book 
	/*
	 the first argument is the upper vertex the second vertex is the bottom vertex and 
	 the third vertex is the middle  vertex 
	 */
	
	public AreasOfInterest(Point leftEdge, Point rightEdge, Point bottomEdge,int pixelsFactor) { 
		super(leftEdge, rightEdge, bottomEdge);
		
		this.pixelsFactor=pixelsFactor;

	}
	/*
	 * function that puts the area 3 (page 17 on the book) the user will give the rectangle height 
	 */
	public void Area3(int height){  
		
		Point p1 = new Point(leftEdge.x+this.pixelsFactor,leftEdge.y+this.pixelsFactor);
		upperEdge.add(p1);
		Point p2 = new Point(rightEdge.x+this.pixelsFactor,rightEdge.y+this.pixelsFactor);
		upperEdge.add(p2);
		Point p3 = new Point(leftEdge.x+height,leftEdge.y+height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p3);
		Point p4 = new Point(rightEdge.x+height,rightEdge.y+height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p4);
	}
	
	/*
	 * function that puts the area 3 (page 17 on the book) the user will give the rectangle height and width
	 */
	public void Area4(int height,int width){  
		
		Point p1 = new Point(leftEdge.x+this.pixelsFactor,leftEdge.y+this.pixelsFactor);
		upperEdge.add(p1);
		Point p2 = new Point(leftEdge.x+width,leftEdge.y+this.width);
		upperEdge.add(p2);
		Point p3 = new Point(leftEdge.x+height,leftEdge.y+height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p3);
		Point p4 = new Point(rightEdge.x+height,rightEdge.y+height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p4);
	}



}
