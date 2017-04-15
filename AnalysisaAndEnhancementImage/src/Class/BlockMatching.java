package Class;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockMatching {

	
	public double identifyTheRequirArea(String func){
		BufferedImage image,image2;
		try{
		 image = ImageIO.read(new File("4.png"));
		 image2 = ImageIO.read(new File("4.png"));
	      Matlab.ExecuteMatlabCode("4.png");
	      Matlab.ExecuteMatlabCode("4.png");
	      byte [][]matrixg =CUtils.BlackWhiteImageToBinaryArray("MatlabResult"+"4.png");
	      TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
	      TriangleEdges edges1 = TriangleUtils.FindTriangleEdges(CUtils.BlackWhiteImageToBinaryArray("MatlabResult"+"4.png"));
			if(!TriangleUtils.AreValidTriangleEdges(edges) && !TriangleUtils.AreValidTriangleEdges(edges1))
			{
				System.out.println("Triangle edges are not valid");
				//Delete matlab result
				CUtils.DeleteFileByPath(CUtils.GetImagesDestPath() + "MatlabResult"+"5.png");
				//continue;
			}
			if(!TriangleUtils.IsTriangleExist(matrixg, 0.4, 0.4, edges)){
				System.out.println("Triangle edges are not valid ind edges");
			}
			if(!TriangleUtils.IsTriangleExist(matrixg, 0.4, 0.4, edges1)){
				System.out.println("Triangle not found in image");
			}
			
			// Calculate square position
			int x = 200-Math.abs(edges.leftEdge.x-edges.rightEdge.x);
			int y = 300-Math.abs(Math.min(edges.leftEdge.y, edges.rightEdge.y)-edges.bottomEdge.y);
			int x1 = 200-Math.abs(edges1.leftEdge.x-edges1.rightEdge.x);
			int y1 = 300-Math.abs(Math.min(edges1.leftEdge.y, edges1.rightEdge.y)-edges1.bottomEdge.y);		
			x=x/2;
			y=y/2;
			x1=x1/2;
			y1=y1/2;
			//crop image 
			BufferedImage out = image.getSubimage(edges.leftEdge.x - x, edges.leftEdge.y - y,image.getWidth() - edges.rightEdge.x + x,image.getHeight() - edges.leftEdge.y + y );
			BufferedImage out1 = image2.getSubimage(edges1.leftEdge.x - x1, edges1.leftEdge.y - y1,image2.getWidth() - edges1.rightEdge.x + x1,image2.getHeight() - edges1.leftEdge.y + y1 );
			CUtils.SaveImage(out, "C:\\Project\\pic\\31.png");
			CUtils.SaveImage(out1, "C:\\Project\\pic\\42.png");
			if(func == "MAD")
				return MAD("C:\\Project\\pic\\31.png","C:\\Project\\pic\\42.png");
			else
				return MES("C:\\Project\\pic\\31.png","C:\\Project\\pic\\42.png");
		      
		}		catch (IOException e){
			System.out.println("Eror");
			return -1.0;
		}
	}
	
	

	//distance function to identify if two images are similer
	public double MAD(String first,String second) throws IOException{
		     
		BufferedImage image;
		try{
		 image = ImageIO.read(new File(first));
		 
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int [][] fr = CUtils.ColorImageTo2DArray(first);
	      int [][] sec = CUtils.ColorImageTo2DArray(second);


	      double sum = 0;
	      for (int row = 0; row < width; row++){ 
	         for (int col =0; col < height; col++) {
	        	 if(row <= height-1)
	        	 sum += Math.abs(fr[row][col]-sec[row][col]);
	         }
	      }
	         sum =  (1/Math.pow(width, 2.0))*sum;
	         return sum;
		}
		catch (IOException e){
			return -1;
		}

	}
	//distance function to identify if two images are similer
	public double MES(String first,String second) throws IOException{
	     
		BufferedImage image;
		try{
		 image = ImageIO.read(new File(first));
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int [][] fr = CUtils.ColorImageTo2DArray(first);
	      int [][] sec = CUtils.ColorImageTo2DArray(second);
	      double sum = 0;
	      for (int row = 0; row < width; row++) 
	         for (int col = 0; col < height; col++) 
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
