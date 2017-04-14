package Class;


public class TriangleBorder {
	
	public static int[][] get_triangle_border(int[][] mat ,int row_offset, int col_offset  ){
		
		int [][] border =  new int [ mat.length ] [mat[0].length ];
		
		 
		//find left border
		for (int i = row_offset; i < mat.length-row_offset; i++)
		{
	       for (int j = col_offset; j < mat[0].length-col_offset; j++)
	       {
              if (mat [i][j] == 1)
              {
            	  border[i][j] = 1;
            	  break;
              }
	       }  
		}
		//find right border
		for (int i = mat.length-1-row_offset; i > row_offset; i--)
		{
	       for (int j = mat[0].length-1-col_offset; j > col_offset; j--)
	       {
              if (mat [i][j] == 1){
            	  border[i][j] = 1;
            	  break;
              }
	       }  
		}
		//find upper border
		for (int i = col_offset; i < mat[0].length-1-col_offset; i++)
		{
	       for (int j = row_offset; j < mat.length-1-row_offset; j++)
	       {
              if (mat [j][i] == 1)
              {
            	  border[j][i] = 1;
            	  break;
              }
	       }  
		}
		//find lower border
		for (int i = mat[0].length-1-col_offset; i > col_offset; i--)
		{
	       for (int j = mat.length-1-row_offset; j > row_offset; j--)
	       {
              if (mat [j][i] == 1)
              {
            	  border[j][i] = 1;
            	  break;
              }
	       }  
		}
		
		return border;
	}
	
	public static void main(String[] args)
	{
		 //EXAMPLE//
		
		 int row_offset = 0;
		 int col_offset = 0;
		 int[ ][ ] mat = {
				 { 0,  0,  0,  0,  0, 0,  0,  0,  0,  0, 0, 0,  0, 0}, 
				 { 0 , 0 , 0 , 0,  0, 0,  0,  0,  0,  0, 0, 0,  0, 0},
				 { 0 , 1,  1,  1,  1, 1,  1,  1,  0,  0, 0, 0,  0, 0},
				 { 0,  1,  1,  1,  1, 1,  1,  1,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  1,  1,  1, 1,  1,  0,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  1,  1,  1, 1,  1,  0,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  0,  1,  1, 1,  0,  0,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  0,  1,  1, 1,  0,  0,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  0,  0,  1, 0,  0,  0,  0,  0, 0, 0,  0, 0}, 
				 { 0 , 0 , 0 , 0,  1, 0,  0,  0,  0,  0, 0, 0,  0, 0},
				 { 0,  0,  0,  0,  0, 0,  0,  0,  0,  0, 0, 0,  0, 0}, 
				 { 0 , 0 , 0 , 0,  0, 0,  0,  0,  0,  0, 0, 0,  0, 0},
				 { 0 , 0 , 0 , 0,  0, 0,  0,  0,  0,  0, 0, 0,  0, 0},
				 };
		 
		 int[ ][ ] square_mat = {
				 { 0,  0,  0,  0,  0, 0,  0,  0}, 
				 { 0,  0,  0,  0,  0, 0,  0,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  1,  1,  1,  1, 1,  1,  0},
				 { 0,  0,  0,  0,  0, 0,  0,  0}, 
				 { 0,  0,  0,  0,  0, 0,  0,  0},
				 };
		 int[][] res = get_triangle_border(square_mat,row_offset, col_offset);
		 
		 System.out.format("orig dem : %d %d " ,square_mat.length ,square_mat[0].length);
		 System.out.format("border dem : %d %d " ,res.length ,res[0].length);
		 System.out.println( );
		 
		for (int i = 0, d=0; i < res.length; i++, d++)
		{
		       System.out.print(d + ": ");
		       for (int j = 0; j < res[0].length; j++)
		       {
		           System.out.print(res[ i ][ j ] + " ");
		       }
		      System.out.println( );
		}
	  
	}
}