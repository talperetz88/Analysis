package Class;

public class TriangleEdges
{
	public Point leftEdge;
	public Point rightEdge;
	public Point bottomEdge;
	
	public TriangleEdges(Point leftEdge, Point rightEdge, Point bottomEdge)
	{
		this.leftEdge = leftEdge;
		this.rightEdge = rightEdge;
		this.bottomEdge = bottomEdge;
	}
	
	public String toString()
	{
		return "LeftEdge - "+leftEdge.x + " " +leftEdge.y + "; RightEdge - "+rightEdge.x + " " +rightEdge.y + "; BottomEdge - "+bottomEdge.x + " " +bottomEdge.y;
	}
}