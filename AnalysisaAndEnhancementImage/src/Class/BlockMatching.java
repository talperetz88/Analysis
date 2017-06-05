package Class;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockMatching {

	
	
	public double identifyTheRequirArea(String path,String savePath,String name1,String name2,String func,int p,int q,int treshold){
		BufferedImage image,image2;
		double [][] res = new double [q][q];
		int flag = 0,rightCorX = 0,rightCorY = 0,leftCorX = 0, leftCorY = 0,widthX = 0 ,heightY = 0;
		double height = 0, height2 = 0,width = 0 ,tres = 1.5;
		try{
		 image = ImageIO.read(new File(path +"goodImages\\" + name1));
		 image2 = ImageIO.read(new File(path +"goodImages\\"+name2));

	      byte [][]matrixg =CUtils.BlackWhiteImageToBinaryArray(path +"matlabRes\\" +"MatlabRes"+ name1);
	      byte [][]matrixg2 = CUtils.BlackWhiteImageToBinaryArray(path+ "matlabRes\\" +"MatlabRes"+ name2);
	      
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
					
					widthX =(rightCorX + leftCorX  > 400)?400 - leftCorX:rightCorX;
					heightY = (rightCorY + leftCorY > 400 )?400 - leftCorY:rightCorY; 
					
				}else{
					width = Math.abs(edges1.leftEdge.x - edges1.rightEdge.x);
					width *= tres;
					height2 *= tres;
					leftCorX = edges1.leftEdge.x - p;
					leftCorY= edges1.leftEdge.y - p;
					rightCorX = leftCorX + (int)width;
					rightCorY = leftCorY + (int)height2;
					
					widthX =(rightCorX + leftCorX  > 400)?400 - leftCorX:rightCorX;
					heightY = (rightCorY + leftCorY > 400 )?400 - leftCorY:rightCorY;
				}
			}else{
				return -1.0;
			}
			
			
			//crop image q is how far we going to search for a similarity between the images
			for(int qw = 0 ; qw < q;qw++)
				for(int qh = 0 ; qh < q ; qh++){
			if(flag ==  0){
			//BufferedImage out = image.getSubimage(leftCorX ,leftCorY,rightCorX,rightCorY );
			CUtils.CropAndSaveImage(path + "goodImages\\" + name1, savePath + "BlockMatching" + name1,leftCorX ,leftCorY,widthX,heightY);
			//CUtils.SaveImage(out, savePath + "BlockMatching" + name1);
			flag = 1;
			}
			int startX =  ((leftCorX + qw)+ widthX > 400)?(400 - (leftCorX + qw)):widthX;
			int startY = ((leftCorY - qh)+ heightY > 400)?(400 - (leftCorY - qh)):heightY;
			BufferedImage out1 = image2.getSubimage(leftCorX + qw ,leftCorY - qh,startX,startY);
			CUtils.SaveImage(out1, savePath + "BlockMatching" +name2);
			
			if(func == "MAD"){
			 res[qw][qh] = MAD(savePath + "BlockMatching" + name1,savePath + "BlockMatching" + name2);

			}
			else{
				res[qw][qh] = MES(savePath + "BlockMatching" + name1,savePath + "BlockMatching" + name2);
			}
			}
		}catch (IOException e){
			System.out.println("Eror");
			return -44.0;
		}
		double[] result = getMin(res);
		int startX =  ((leftCorX + (int)result[1]) + widthX > 400)?(400 - (leftCorX + (int)result[1])):widthX;
		int startY = ((leftCorY - (int) result[2]) + heightY> 400)?(400 - (leftCorY - (int) result[2])):heightY;
		BufferedImage out1 = image2.getSubimage(leftCorX + (int)result[1] ,leftCorY - (int) result[2],startX,startY);
		CUtils.SaveImage(out1, savePath + "BlockMatching" +name2);
		CUtils.DeleteFileByPath(savePath + "BlockMatching" +name2);
		CUtils.DeleteFileByPath(savePath + "BlockMatching" + name1);
		return result [0];
	}
	
	
	//culc the height of triangle using an area
	public double culcHeightOfTriangle(TriangleEdges edges) {
		double area = 0,height = 0;
		area = 0.5*((edges.leftEdge.x-edges.rightEdge.x)*(edges.leftEdge.y - edges.rightEdge.y)-(edges.leftEdge.x-edges.bottomEdge.x)*(edges.leftEdge.y-edges.bottomEdge.y));
		height = (2*area) /(edges.leftEdge.x-edges.rightEdge.x);
		return height;
	}


	//return the min value of images
	private double[] getMin(double[][] res) {
		double min[] = new double [3];
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
			return -44;
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
	        	 if(row <= height-1)
	        		 sum += Math.pow(fr[row][col]-sec[row][col],2.0);
	         sum =  (1/Math.pow(width, 2.0))*sum;
	         System.out.println("the sum is:" +sum);
	         return sum;
		}
		catch (IOException e){
			return -44;
		}

	}
	
}
