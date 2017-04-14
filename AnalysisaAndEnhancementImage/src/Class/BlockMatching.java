package Class;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockMatching {

	
	public void identifyTheRequirArea(){
		
	}
	
	
	
	//distance function to identify if two images are similer
	public double MAD(String first,String second,Point point) throws IOException{
		     
		BufferedImage image;
		try{
		 image = ImageIO.read(new File(first));
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int [][] fr = CUtils.ColorImageTo2DArray(first);
	      int [][] sec = CUtils.ColorImageTo2DArray(second);
	      TriangleEdges edges = TriangleUtils.FindTriangleEdges(CUtils.BlackWhiteImageToBinaryArray(first));
	      System.out.println("fuck");
	      System.out.println(edges.toString());
	      double sum = 0;
	      for (int row = point.x; row < height-point.x; row++) 
	         for (int col = point.y; col < width-point.y; col++) 
	        	 sum += Math.abs(fr[row][col]-sec[row][col]);
	         sum =  (1/Math.pow(width, 2.0))*sum;
	         return sum;
		}
		catch (IOException e){
			return -1;
		}

	}
	//distance function to identify if two images are similer
	public double MES(String first,String second,Point point) throws IOException{
	     
		BufferedImage image;
		try{
		 image = ImageIO.read(new File(first));
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int [][] fr = CUtils.ColorImageTo2DArray(first);
	      int [][] sec = CUtils.ColorImageTo2DArray(second);
	      double sum = 0;
	      for (int row = point.x; row < height-point.x; row++) 
	         for (int col = point.y; col < width-point.y; col++) 
	        	 sum += Math.pow(fr[row][col]-sec[row][col],2.0);
	         sum =  (1/Math.pow(width, 2.0))*sum;
	         return sum;
		}
		catch (IOException e){
			return -1;
		}

	}
	
	
	
	/*
	   private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
		      int width = image.getWidth();
		      int height = image.getHeight();
		      int[][] result = new int[height][width];

		      for (int row = 0; row < height; row++) {
		         for (int col = 0; col < width; col++) {
		            result[row][col] = image.getRGB(col, row);
		         }
		      }

		      return result;
		   }*/
}
