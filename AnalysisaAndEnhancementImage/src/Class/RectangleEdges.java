package Class;


public class RectangleEdges {

	public Point leftUpEdge;
	public Point leftDownEdge;
	public Point rightUpEdge;
	public Point rightDownEdge;
	
	
	public RectangleEdges(Point leftUpEdge, Point leftDownEdge, Point rightUpEdge, Point rightDownEdge)
	{
		this.leftUpEdge = leftUpEdge;
		this.rightUpEdge = rightUpEdge;
		this.rightDownEdge = rightDownEdge;
		this.leftDownEdge = leftDownEdge;
		
		System.out.println("Rectangle edges:");
		System.out.println("leftUpEdge - " + leftUpEdge.x + " "+ leftUpEdge.y );
		System.out.println("leftDownEdge - " + leftDownEdge.x + " "+ leftDownEdge.y);
		System.out.println("rightUpEdge - " + rightUpEdge.x + " "+ rightUpEdge.y);
		System.out.println("rightDownEdge - " + rightDownEdge.x + " "+ rightDownEdge.y);
	}
}
