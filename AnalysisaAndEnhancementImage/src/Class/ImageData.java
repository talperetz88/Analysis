
public class ImageData 
{
	public String name;
	public int width;
	public int height;
	public byte[][] imageMatrix;
	public byte[][] triangleBordersMatrix;
	public boolean isTriangleExist;
	public boolean temFound;
	public boolean granulomaFound;
	public TriangleEdges triangleEdges;
	public int[][] colorImageMatrix;
	
	
	public String ByteArrayToString(byte[][] matrix)
	{
		int m = matrix.length;
	    int n = matrix[0].length;

	    String output = "";
	    for(int x = 0; x < n; x++)
	    {
	        for(int y = 0; y < m; y++)
	        {
	        	output += matrix[x][y];
	        }
	        
	        output = output +"\n";
	    }
	    
	    return output;
	}
	
	public String IntArrayToString(int[][] matrix)
	{
		int m = matrix.length;
	    int n = matrix[0].length;

	    String output = "";
	    for(int x = 0; x < n; x++)
	    {
	        for(int y = 0; y < m; y++)
	        {
	        	output += matrix[x][y];
	        }
	        
	        output = output +"\n";
	    }
	    
	    return output;
	}
}
