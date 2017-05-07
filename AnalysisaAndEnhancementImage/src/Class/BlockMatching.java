package Class;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockMatching {

	
	
	public double identifyTheRequirArea(String path,String name1,String name2,String func,int p,int q,int treshold){
		BufferedImage image,image2;
		double [][] res = new double [q][q];
		int flag = 0,rightCorX = 0,rightCorY = 0,leftCorX = 0, leftCorY = 0;
		double height = 0, height2 = 0,width = 0,tres = 1.5;
		try{
		 image = ImageIO.read(new File("4.png"));
		 image2 = ImageIO.read(new File("4.png"));
	     /* Matlab.ExecuteMatlabCode("4.png");
	      Matlab.ExecuteMatlabCode("4.png");*/
	      byte [][]matrixg =CUtils.BlackWhiteImageToBinaryArray(path +""+ name1);
	      byte [][]matrixg2 = CUtils.BlackWhiteImageToBinaryArray(path + name2);
	      
	      TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
	      TriangleEdges edges1 = TriangleUtils.FindTriangleEdges(matrixg2);
			if(!TriangleUtils.AreValidTriangleEdges(edges))
			{
				System.out.println("Triangle edges are not valid");
				//Delete matlab result
				CUtils.DeleteFileByPath(path + name1);
				//continue;
			}
			if(!TriangleUtils.AreValidTriangleEdges(edges1)){
				System.out.println("Triangle edges are not valid");
				//Delete matlab result
				CUtils.DeleteFileByPath(path + name2);
				//continue;
			}
			if(!TriangleUtils.IsTriangleExist(matrixg, 0.4, 0.4, edges)){
				System.out.println("Triangle edges are not valid ind edges");
			}
			if(!TriangleUtils.IsTriangleExist(matrixg2, 0.4, 0.4, edges1)){
				System.out.println("Triangle not found in image");
			}
			
			height = culcHeightOfTriangle(edges);
			height2 = culcHeightOfTriangle(edges1);
			//p is safe distance; tres is the size of the rectangle
			if(Math.abs(height-height2) <= treshold){
				if(height2 - height < 0){
					width = Math.abs(edges.leftEdge.x - edges.rightEdge.x); 
					width *= tres;
					height *= tres;
					leftCorX = edges.leftEdge.x - p;
					leftCorY= edges.leftEdge.y - p;
					rightCorX = leftCorX + (int)width;
					rightCorY = leftCorY + (int)height;
					
				}else{
					width = Math.abs(edges1.leftEdge.x - edges1.rightEdge.x);
					width *= tres;
					height2 *= tres;
					leftCorX = edges1.leftEdge.x - p;
					leftCorY= edges1.leftEdge.y - p;
					rightCorX = leftCorX + (int)width;
					rightCorY = leftCorY + (int)height2;
				}
			}
			
			
			//for(int i = 0 ; i < 2*p ; i++){
			
			//int t=i-p;
			//Calculate square position
			/*
			double y = (Math.abs(Math.min(edges.leftEdge.y, edges.rightEdge.y)-edges.bottomEdge.y));
			double x = (Math.abs(edges.leftEdge.x-edges.rightEdge.x));
			double y1 = Math.abs(Math.min(edges1.leftEdge.y, edges1.rightEdge.y)-edges1.bottomEdge.y);
			double x1 = Math.abs(edges1.leftEdge.x-edges1.rightEdge.x);
			
			//check similarity and culc position of rectangle
			if(Math.abs(y-y1) <= treshold){
				 height =Math.max(y, y1);
				
				height = Math.round(height*1.5);
				if(y1-y < 0){
					width = x;
					width = Math.round(width*1.5);
					x = (width -x)/2;
					y = (height - y)/2;
					
					rightCorX = (int)(edges.leftEdge.x - x);
					rightCorY = (int)( edges.leftEdge.y - y);
					leftCorX =(int)(image.getWidth() - edges.rightEdge.x + x);
					leftCorY = (int)(image.getHeight() - edges.leftEdge.y + y);
					
				}else{
					width = x1;
					width = Math.round(width*1.5);
					x1 = (width -x1)/2;
					y1 = (height - y1)/2;
					
					rightCorX = (int)(edges1.leftEdge.x - x1);
					rightCorY = (int)( edges1.leftEdge.y - y1);
					leftCorX =(int)(image.getWidth() - edges1.rightEdge.x + x1);
					leftCorY = (int)(image.getHeight() - edges1.leftEdge.y + y1);
				}
				
				
			}*/
		
			//crop image q is how far we going to search for a similarity between the images
			for(int qw = 0 ; qw < q;qw++)
				for(int qh = 0 ; qh < q ; qh++){
			if(flag ==  0){
			BufferedImage out = image.getSubimage(leftCorX ,leftCorY,rightCorX,rightCorY );
			CUtils.SaveImage(out, path + "BlockMatching" + name1);
			flag = 1;
			}
			BufferedImage out1 = image2.getSubimage(leftCorX + qw ,leftCorY - qh,rightCorX,rightCorY);
			CUtils.SaveImage(out1, path + "BlockMatching" +name2);
			
			if(func == "MAD"){
			 res[qw][qh] = MAD(path + "BlockMatching" + name1,path + "BlockMatching" + name2);
			if(qw == res.length)
				return -1;
			}
			else{
				res[qw][qh] = MES(path + "BlockMatching" + name1,path + "BlockMatching" + name2);
				if(qw == res.length)
				return -1;
			}
			}
		}catch (IOException e){
			System.out.println("Eror");
			return -1.0;
		}
		double[] result = getMin(res);
		BufferedImage out1 = image2.getSubimage(leftCorX + (int)result[1] ,leftCorY -(int) result[2],rightCorX,rightCorY);
		CUtils.SaveImage(out1, path + "BlockMatching" +name2);
		return result [0];
	}
	
	
	//culc the height of triangle using an area
	private double culcHeightOfTriangle(TriangleEdges edges) {
		double area = 0,height = 0;
		area = 0.5*((edges.leftEdge.x-edges.rightEdge.x)*(edges.leftEdge.y - edges.rightEdge.y)-(edges.leftEdge.x-edges.bottomEdge.x)*(edges.leftEdge.y-edges.bottomEdge.y));
		height = (2*area) /(edges.leftEdge.x-edges.rightEdge.x);
		return height;
	}


	//return the min value of images
	private double[] getMin(double[][] res) {
		double min[] = new double [res.length];
			min[0] = res[0][0];
		for(int i = 0 ; i < res.length ; i ++){
			for(int j = 0 ; j < res.length ; j++)
		if(res[i][j] < min[0] && res[i][j] != -1){
			min[0] = res[i][j];
			min[1] = i;
			min[2] = j;
		System.out.println("test" + res[i][j]);
		}
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
	         System.out.println("the sum is:" +sum);
	         return sum;
		}
		catch (IOException e){
			return -1;
		}

	}
	
}
