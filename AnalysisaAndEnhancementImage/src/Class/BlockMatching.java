package Class;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockMatching {

	
	public double identifyTheRequirArea(String func,int p){
		BufferedImage image,image2;
		double [] res = new double [2*p];
		int flag = 0;
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
			
			for(int i = 0 ; i < (2*p) ; i++){//p-i
			int t=i-p;
			// Calculate square position
			int x = (200-Math.abs(edges.leftEdge.x-edges.rightEdge.x))/2;
			int y = (300-Math.abs(Math.min(edges.leftEdge.y, edges.rightEdge.y)-edges.bottomEdge.y))/2;
			int x1 = (200-(Math.abs(edges1.leftEdge.x-edges1.rightEdge.x)+ t))/2;
			int y1 = (300-Math.abs(Math.min(edges1.leftEdge.y, edges1.rightEdge.y)-edges1.bottomEdge.y))/2;		
		
			//crop image 
			if(flag ==  0){
			BufferedImage out = image.getSubimage(edges.leftEdge.x - x  , edges.leftEdge.y - y,image.getWidth() - edges.rightEdge.x + x,image.getHeight() - edges.leftEdge.y + y );
			CUtils.SaveImage(out, "C:\\Project\\pic\\31.png");
			flag = 1;
			}
			BufferedImage out1 = image2.getSubimage(edges1.leftEdge.x - x1, edges1.leftEdge.y - y1,image2.getWidth() - edges1.rightEdge.x + x1 ,image2.getHeight() - edges1.leftEdge.y + y1 );
			CUtils.SaveImage(out1, "C:\\Project\\pic\\42.png");
			
			if(func == "MAD"){
			res[i] = MAD("C:\\Project\\pic\\31.png","C:\\Project\\pic\\42.png");
				return getMin(res);
			}
			else{
				res[i] = MES("C:\\Project\\pic\\31.png","C:\\Project\\pic\\42.png");
				
				return getMin(res);
			}
				
			}  
		}catch (IOException e){
			System.out.println("Eror");
			return -1.0;
		}
		return -1;
	}
	
	

	private double getMin(double[] res) {
		double min = res[0];
		for(int i = 0 ; i < res.length ; i ++){
		if(res[i] < min)
			min = res[i];
		System.out.println("test" + res[i]);
		}
		return min;
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
	         System.out.println("the sum is:" +sum);
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
	
}
