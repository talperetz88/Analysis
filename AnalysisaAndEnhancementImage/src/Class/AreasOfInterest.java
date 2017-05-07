package Class;

import java.util.ArrayList;
import java.util.List;

public class AreasOfInterest  {
	
	int pixelsFactor;
	 List<Point> upperEdge ; //area 3 in the book ,
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
	
	public AreasOfInterest(int pixelsFactor) { 
		this.pixelsFactor=pixelsFactor;
		upperEdge = new ArrayList<Point>(); 
		rightEdge2 = new ArrayList<Point>(); 
		leftEdge2 = new ArrayList<Point>(); 
	}
	/*
	 * function that puts the area 3 (page 17 on the book) the user will give the rectangle height 
	 */
	public void Area3(TriangleEdges edges,int height,int width){  
		
		Point p1 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y-height); 
		upperEdge.add(p1);
		Point p2 = new Point(edges.rightEdge.x+this.pixelsFactor,edges.leftEdge.y-height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p2);
		Point p3 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y+this.pixelsFactor);
		upperEdge.add(p3);
		Point p4 = new Point(edges.rightEdge.x+this.pixelsFactor,edges.leftEdge.y+this.pixelsFactor);
		upperEdge.add(p4);
		
	}
	
	/*
	 * function that puts the area 4 (page 17 on the book) the user will give the rectangle height and width
	 */
	
	public void Area4(TriangleEdges edges,int height){  
		
		int width=65;
		Point p1 = new Point(edges.rightEdge.x-this.pixelsFactor,edges.rightEdge.y-this.pixelsFactor);
		rightEdge2.add(p1);
		Point p2 = new Point(edges.rightEdge.x+width,edges.rightEdge.y);
		rightEdge2.add(p2);
		Point p3 = new Point(edges.bottomEdge.x-this.pixelsFactor,edges.bottomEdge.y+3*this.pixelsFactor);  
		rightEdge2.add(p3);
		Point p4 = new Point(edges.bottomEdge.x+width,edges.bottomEdge.y+3*this.pixelsFactor); 
		rightEdge2.add(p4);
	}
	
	
	/*
	 * function that puts the area 5 (page 17 on the book) the user will give the rectangle height and width
	 */
	
	public void Area5(TriangleEdges edges,int height){  
		
		int width=65;
		Point p1 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y-this.pixelsFactor);
		leftEdge2.add(p1);
		Point p2 = new Point(edges.leftEdge.x-width,edges.leftEdge.y);
		leftEdge2.add(p2);
		Point p3 = new Point(edges.bottomEdge.x-this.pixelsFactor,edges.bottomEdge.y+3*this.pixelsFactor);  
		leftEdge2.add(p3);
		Point p4 = new Point(edges.bottomEdge.x-width,edges.bottomEdge.y+3*this.pixelsFactor); 
		leftEdge2.add(p4);
	}
	
	public void Area6(TriangleEdges edges,int height){  
		
		Point p1 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y-this.pixelsFactor);
		leftEdge2.add(p1);
		Point p2 = new Point(edges.rightEdge.x+this.pixelsFactor,edges.leftEdge.y-this.pixelsFactor);
		leftEdge2.add(p2);
		Point p3 = new Point(edges.bottomEdge.x+this.pixelsFactor,edges.bottomEdge.y+this.pixelsFactor);  
		leftEdge2.add(p3);
	}



}
