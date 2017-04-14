package Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TriangleUtils {

	private static double ratio1Min = 1;
	private static double ratio1Max = 0;
	private static double ratio2Min = 1;
	private static double ratio2Max = 0;
	
	public static TriangleEdges FindTriangleEdges(byte[][] matrix)
	{
	      List<Point> trianglePoints = new ArrayList<Point>(); 
	      List<Point> possibleLeftEdgePoints = new ArrayList<Point>(); 
	      List<Point> possibleRightEdgePoints = new ArrayList<Point>();
	      
	      //Build the array of all possible 'triangle' points
	      for (int i = 0; i < matrix.length; i++) 
	      {
	          for (int j = 0; j < matrix[i].length; j++) 
	          {
	              if(matrix[i][j] != 1)
	              {
	                  trianglePoints.add(new Point(i,j));
	              }
	          }
	      }
	    
	      //find left edge, minimum x and minimum y
	      Point leftHighEdge = (Point)trianglePoints.get(0);
//	      for(int i = 1; i < trianglePoints.size(); i++)
//	      {
//	          if ((trianglePoints.get(i).x < leftHighEdge.x) && (trianglePoints.get(i).y <= leftHighEdge.y))
//	          {
//	              leftHighEdge = trianglePoints.get(i);
//	          }
//	      }
	      Collections.sort(trianglePoints, (Point p1, Point p2) -> p1.x-p2.x);
	      
	      for(int i = 0; i < 100; i++)
	      {
	    	  possibleLeftEdgePoints.add(trianglePoints.get(i));
	      }
	      Collections.sort(possibleLeftEdgePoints, (Point p1, Point p2) -> p1.y-p2.y);
	      
	      
	      //leftHighEdge= FindAverage(possibleLeftEdgePoints);
	      leftHighEdge = possibleLeftEdgePoints.get(0);
	      
	     //find right edge, maximum x and minimum y
	      Point rightHighEdge = (Point)trianglePoints.get(0);
//	      for(int i = 1; i < trianglePoints.size(); i++)
//	      {
//	          if ((trianglePoints.get(i).x > rightHighEdge.x) && (trianglePoints.get(i).y <= leftHighEdge.y))
//	          {
//	              rightHighEdge = trianglePoints.get(i);
//	          }
//	      }
//	       
	      
	      Collections.sort(trianglePoints, (Point p1, Point p2) -> p1.x-p2.x);
	      
	      for(int i = trianglePoints.size()-1; i > trianglePoints.size() - 100; i--)
	      {
	    	  possibleRightEdgePoints.add(trianglePoints.get(i));
	      }
	      Collections.sort(possibleRightEdgePoints, (Point p1, Point p2) -> p1.y-p2.y);
	      rightHighEdge = possibleRightEdgePoints.get(0);
	      //rightHighEdge = FindAverage(possibleRightEdgePoints);
	      //find bottom edge, maximum y
	      Point bottomEdge = (Point)trianglePoints.get(0);
	      for(int i = 1; i < trianglePoints.size(); i++)
	      {
	          if(trianglePoints.get(i).y > bottomEdge.y)
	          {
	              bottomEdge = trianglePoints.get(i);
	          }
	      }
	       
	        
	      return new TriangleEdges(leftHighEdge, rightHighEdge, bottomEdge);
	 }
	
	public static boolean IsTriangleExist(byte[][] triangleMatrix, double thresholdValue1, double thresholdValue2, TriangleEdges edges )
	{
		int numberOfClusterTrianglePoints = 0;
		int numberOfClusterPoints = 0;
		int numberOfTotalTrianglePoints = 0;
		TriangleEdges edges1 = FindTriangleEdges(triangleMatrix);
		
		Barycentric barycentricCheck = new Barycentric(edges.leftEdge, edges.rightEdge, edges.bottomEdge);
		
		
		int m = triangleMatrix.length;
	    int n = triangleMatrix[0].length;

	    for(int x = 0; x < m; x++)
	    {
	        for(int y = 0; y < n; y++)
	        {
		        if(barycentricCheck.contains(new Point(x,y)))
		        {
		        	numberOfTotalTrianglePoints++;
		        }
	        }
	    }
	    
	    for(int x = 0; x < n; x++)
	    {
	        for(int y = 0; y < m; y++)
	        {
	        	if(triangleMatrix[x][y]==0) //Point from possible triangle
	        	{
	        		numberOfClusterPoints++;
		        	if(barycentricCheck.contains(new Point(x,y)))
		        	{
		        		numberOfClusterTrianglePoints++;
		        	}
	        	}
	        }
	    }
	    
	    System.out.println("numberOfClusterTrianglePoints - " + numberOfClusterTrianglePoints + "/numberOfTotalTrianglePoints- "+numberOfTotalTrianglePoints );
	    //Clean phase 1 
	    double ratio1 = (double)((double)numberOfClusterTrianglePoints/(double)numberOfTotalTrianglePoints);
		
	    System.out.println("numberOfClusterTrianglePoints - " + numberOfClusterTrianglePoints + "/numberOfClusterPoints- "+numberOfClusterPoints );
	    //Clean phase 2 - Cluster bigger than triangle
	    double ratio2 = (double)((double)numberOfClusterTrianglePoints/(double)numberOfClusterPoints);
	    
	    System.out.println("ratio1 - "+ratio1);
	    System.out.println("ratio2 - "+ratio2);
	    
	    
	       
	    if(ratio1Min>ratio1)
	    {
	    	ratio1Min = ratio1;
	    }
	    if(ratio2Min>ratio2)
	    {
	    	ratio2Min = ratio2;
	    }
	    if(ratio1Max<ratio1)
	    {
	    	ratio1Max = ratio1;
	    }
	    if(ratio2Max<ratio2)
	    {
	    	ratio2Max = ratio2;
	    }
	    
	    System.out.println("ratio1Min - "+ratio1Min);
	    System.out.println("ratio2Min - "+ratio2Min);
	    System.out.println("ratio1Max - "+ratio1Max);
	    System.out.println("ratio2Max - "+ratio2Max);
	    
	    CUtils.SetRatio1Range(ratio1Min, ratio1Max);
	    CUtils.SetRatio2Range(ratio2Min, ratio2Max);
	    
	    if(thresholdValue1>ratio1) 
	    	return false;
	    if(thresholdValue2>ratio2) 
	    	return false;
	   
	    return true;
	}

	public static byte[][] get_triangle_border(byte[][] mat ,int row_offset, int col_offset  )
	{
		
		byte [][] border =  new byte [ mat.length ] [mat[0].length ];
		 
		for (int row = 0; row < mat.length ; row ++)
	            for (int col = 0; col < mat[0].length; col++)
	            	border[row][col]=1;
		 
		//find left border
		for (int i = row_offset; i < mat.length-row_offset; i++)
		{
	       for (int j = col_offset; j < mat[0].length-col_offset; j++)
	       {
              if (mat [i][j] == 0)
              {
            	  border[i][j] = 0;
            	  break;
              }
	       }  
		}
		//find right border
		for (int i = mat.length-1-row_offset; i > row_offset; i--)
		{
	       for (int j = mat[0].length-1-col_offset; j > col_offset; j--)
	       {
              if (mat [i][j] == 0){
            	  border[i][j] = 0;
            	  break;
              }
	       }  
		}
		//find upper border
		for (int i = col_offset; i < mat[0].length-1-col_offset; i++)
		{
	       for (int j = row_offset; j < mat.length-1-row_offset; j++)
	       {
              if (mat [j][i] == 0)
              {
            	  border[j][i] = 0;
            	  break;
              }
	       }  
		}
		//find lower border
		for (int i = mat[0].length-1-col_offset; i > col_offset; i--)
		{
	       for (int j = mat.length-1-row_offset; j > row_offset; j--)
	       {
              if (mat [j][i] == 0)
              {
            	  border[j][i] = 0;
            	  break;
              }
	       }  
		}
		
		return border;
	}

	public static boolean AreValidTriangleEdges(TriangleEdges edges) 
	{
		//System.out.println("edges.leftEdge.x - " + edges.leftEdge.x);
		//System.out.println("edges.leftEdge.y - " + edges.leftEdge.y);
		if((edges.leftEdge.x >= 0 && edges.leftEdge.x <= 50) || (edges.leftEdge.x <= 399 && edges.leftEdge.x >= 350))
			return false;
		if((edges.leftEdge.y >= 0 && edges.leftEdge.y <= 50) || (edges.leftEdge.y <= 399 && edges.leftEdge.y >= 350))
			return false;
		
		//System.out.println("edges.rightEdge.x - " + edges.rightEdge.x);
		//System.out.println("edges.rightEdge.y - " + edges.rightEdge.y);
		if((edges.rightEdge.x >= 0 && edges.rightEdge.x <= 50) || (edges.rightEdge.x <= 399 && edges.rightEdge.x >= 370))
			return false;
		if((edges.rightEdge.y >= 0 && edges.rightEdge.y <= 50) || (edges.rightEdge.y <= 399 && edges.rightEdge.y >= 350))
			return false;

		//System.out.println("edges.bottomEdge.x - " + edges.bottomEdge.x);
		//System.out.println("edges.bottomEdge.y - " + edges.bottomEdge.y);
		if((edges.bottomEdge.x >= 0 && edges.bottomEdge.x <= 50) || (edges.bottomEdge.x <= 399 && edges.bottomEdge.x >= 350))
			return false;
		
		return true;
	}
}


