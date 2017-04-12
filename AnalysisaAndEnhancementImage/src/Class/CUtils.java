import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;



public class CUtils {

	
	private static double heightCoef = 0.1;
	public static String GetVlcPath()
	{
		return "C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe";
	}
	
	private static String strRatio1Range;
	public static void SetRatio1Range(double min, double max)
	{
		strRatio1Range = min +"< ratio1 <" + max; 
	}
	
	public static String GetRatio1Range()
	{
		return strRatio1Range; 
	}
	
	private static String strRatio2Range;
	public static void SetRatio2Range(double min, double max)
	{
		strRatio2Range = min +"< ratio2 <" + max; 
	}
	
	public static String GetRatio2Range()
	{
		return strRatio2Range; 
	}
	
	private static String strImagesSourcepath;
	public static void SetImagesSourcePath(String path)
	{
		strImagesSourcepath = path +"\\"; 
	}
	
	public static String GetImagesSourcePath()
	{
		return strImagesSourcepath; 
	}
	
	private static String strImagesDestinationpath;
	public static void SetImagesDestinationPath(String path)
	{
		strImagesDestinationpath = path +"\\"; 
	}
	
	
	public static BufferedImage GetImageFromFile(String imagePath)
	{
		BufferedImage img = null;

        try {
        	File imageFile  = new File(imagePath);
            img = ImageIO.read(imageFile);

            return img;
        } catch (final IOException e) {
        	return null;
        }
	}
	
	public static String GetImagesDestPath()
	{
		return strImagesDestinationpath; 
	}
	
	public static BufferedImage ResizeImage(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	public static boolean CropAndSaveImage(String originalImagePath, String croppedImagePath, int x, int y, int w, int h)
	{
		File outputfile = null;
		try 
		{
			BufferedImage originalImgage = ImageIO.read(new File(originalImagePath));
			
			//System.out.println("Original Image Dimension: "+originalImgage.getWidth()+"x"+originalImgage.getHeight());

			BufferedImage SubImgage = originalImgage.getSubimage(x, y, w, h);
			//System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());

			outputfile = new File(croppedImagePath);
			ImageIO.write(SubImgage, "jpg", outputfile);

			

			return true;
		} 
		catch (IOException e) 
		{
			if(outputfile!=null)
				System.out.println("Error occurred Image cropped unsuccessfully: "+outputfile.getPath());
			return false;
		}
	}
			
	public static String GetMatlabCodePath()
	{
		return "C:\\Project\\Matlab_Code\\";
	}
	
	public static boolean CreateDirectory(String dirPath)
	{
		File directory = new File(dirPath);

		// if the directory does not exist, create it
		if (!directory.exists()) 
		{
		    try
		    {
		    	directory.mkdir();
		        return true;
		    } 
		    catch(SecurityException se)
		    {
		        return false;
		    }        
		}
		
		return true;
	}
	
	public static boolean CopyDirectoryToDirectory(String scr, String dst)
	{
		File srcDir = new File(scr);
		File destDir = new File(dst);
		try 
		{
			FileUtils.copyDirectory(srcDir, destDir);
			return true;
		} catch (IOException e1) 
		{
			return false;
		}

	}
	
	public static boolean RenameFilesInFolder(String dirPath)
	{
		File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        if (folder.exists()) 
        {
	        try
	        {
		        for (int i = 0; i < listOfFiles.length; i++) 
		        {
		
		            if (listOfFiles[i].isFile()) 
		            {
		
		                File f = new File(dirPath+"\\"+listOfFiles[i].getName()); 
		
		                f.renameTo(new File(dirPath+"\\"+i+".png"));
		            }
		        }
		        System.out.println("conversion is done");
		        return true;
	        }
	        catch(Exception ex)
	        {
	        	return false;
	        }
        }
        
        return false;
	}

	public static void DeleteAllFilesInDirectory(String dirPath)
	{
		String files; 
        File file = new File(dirPath);
        File[] listOfFiles = file.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if (listOfFiles[i].isFile()) 
            {
                if(!new File(listOfFiles[i].toString()).delete())
                	System.err.println("Error occurred, file cannot be deleted. File name - " + listOfFiles[i].getName());
            }
        }
	}
	
	public static void DeleteDirectory(String dirPath) throws IOException
	{

			File file = new File(dirPath);
	    	if(file.isDirectory()){

	    		//directory is empty, then delete it
	    		if(file.list().length==0)
	    		{

	    		   file.delete();
	    		   System.out.println("Directory is deleted : "
	                                                 + file.getAbsolutePath());

	    		}else
	    		{

	    		   //list all the directory contents
	        	   String files[] = file.list();

	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);

	        	      //recursive delete
	        	      DeleteDirectory(fileDelete.getAbsolutePath());
	        	   }

	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0)
	        	   {
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " + file.getAbsolutePath());
	        	   }
	    		}

	    	}else
	    	{
	    		//if file, then delete it
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }

	public static void WriteBooleanMarixToFile(String filename, boolean[][]x) throws IOException
	{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  int row = 0;
		  int col = 0;
		  for (int i = 0; i < x.length; i++) 
		  {
			    for (int j = 0; j < x[i].length; j++) 
			    {
			    	if((String.valueOf(x[i][j]) == "false"))
			    	{
			    		outputWriter.write(0 + " ");
			    	}
			    	else
			    	{
			    		outputWriter.write(1 + " ");
			    	}
			    	
			    		col++;
			    }
			    
			    row++;
			    outputWriter.newLine();
			}

		  outputWriter.newLine();
		  outputWriter.newLine();
		  
		  outputWriter.write(row + " ");
		  outputWriter.newLine();
		  outputWriter.write(col + " ");
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public static void WriteByteMarixToFile(String filename, byte[][]x, TriangleEdges edges) throws IOException
	{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  int row = 0;
		  int col = 0;
		  for (int i = 0; i < x.length; i++) 
		  {
			    for (int j = 0; j < x[i].length; j++) 
			    {
			    	if(i== edges.leftEdge.x & j == edges.leftEdge.y)
			    	{
			    		outputWriter.write("LEFT EDGE" + " ");
			    	}
			    	else if(i== edges.rightEdge.x & j == edges.rightEdge.y)
			    	{
			    		outputWriter.write("RIGHT EDGE" + " ");
			    	}
			    	else if(i== edges.bottomEdge.x & j == edges.bottomEdge.y)
			    	{
			    		outputWriter.write("BOTTOM EDGE" + " ");
			    	}
			    	else
			    	{
			    		outputWriter.write(x[j][i] + " ");
			    	}
			    	if(i==0)
			    	{
			    		col++;
			    	}
			    }
			    
			    row++;
			    outputWriter.newLine();
			}

		  outputWriter.newLine();
		  outputWriter.newLine();
		  
		  outputWriter.write(row + " ");
		  outputWriter.newLine();
		  outputWriter.write(col + " ");
		  outputWriter.newLine();
		  outputWriter.newLine();
		
		  outputWriter.write(edges.leftEdge.x + " ");
		  outputWriter.write(edges.leftEdge.y + " ");
		  outputWriter.newLine();
		  outputWriter.write(edges.rightEdge.x + " ");
		  outputWriter.write(edges.rightEdge.y + " ");
		  outputWriter.newLine();
		  outputWriter.write(edges.bottomEdge.x + " ");
		  outputWriter.write(edges.bottomEdge.y + " ");
		  outputWriter.newLine();
		  outputWriter.newLine();
		  outputWriter.newLine();
//		  byte borders[][] = TriangleUtils.get_triangle_border(x, 0, 0);
//		  for (int i = 0; i < borders.length; i++) 
//		  {
//			    for (int j = 0; j < borders[i].length; j++) 
//			    {
//			    	outputWriter.write(borders[i][j] + " ");
//			    	if(i==0)
//			    	{
//			    		col++;
//			    	}
//			    }
//			    
//			    row++;
//			    outputWriter.newLine();
//			}
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public static void WriteIntMarixToFile(String filename, int[][]x) throws IOException
	{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  int row = 0;
		  int col = 0;
		  for (int i = 0; i < x.length; i++) 
		  {
			    for (int j = 0; j < x[i].length; j++) 
			    {
			    	outputWriter.write(x[j][i] + " ");
			    
			    	if(i==0)
			    	{
			    		col++;
			    	}
			    }
			    
			    row++;
			    outputWriter.newLine();
			}

		  outputWriter.newLine();
		  outputWriter.newLine();
		  
		 
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public static void WriteRectIntMarixToFile(String filename, int[][]x, RectangleEdges edges,List<Point> r_thikPointsLocations) throws IOException
	{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  int row = 0;
		  int col = 0;
		  for (int i = 0; i < x.length; i++) 
		  {
			    for (int j = 0; j < x[i].length; j++) 
			    {
			    	if(edges.leftUpEdge.x==i && edges.leftUpEdge.y==j)
			    		outputWriter.write(x[j][i] + "!!!!leftUpEdge!!!!");
			    	else
			    	if(edges.leftDownEdge.x==i && edges.leftDownEdge.y==j)
			    		outputWriter.write(x[j][i] + "!!!!leftDownEdge!!!!");
			    	else
			    	if(edges.rightUpEdge.x==i && edges.rightUpEdge.y==j)
			    		outputWriter.write(x[j][i] + "!!!!rightUpEdge!!!!");
			    	else
			    	if(edges.rightDownEdge.x==i && edges.leftUpEdge.y==j)
			    		outputWriter.write(x[j][i] + "!!!!rightDownEdge!!!!");
			    	else
			    	{
			    		outputWriter.write(x[j][i] + " ");
			    	}
			    	
			    	for(int m=0; m<r_thikPointsLocations.size();m++)
					 {
						 if(r_thikPointsLocations.get(m).x == i && r_thikPointsLocations.get(m).y==j)
							 outputWriter.write(x[j][i] + " POINT ");
					 }
			    	
			    	if(i==0)
			    	{
			    		col++;
			    	}
			    }
			    
			    row++;
			    outputWriter.newLine();
			}

		  outputWriter.newLine();
		  outputWriter.newLine();
		  
		 
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public static byte[][] BlackWhiteImageToBinaryArray(String imagePath)
	{
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(imagePath));
		
			byte[][] pixels = new byte[image.getHeight()][];

			for (int x = 0; x < image.getHeight(); x++) 
			{
			    pixels[x] = new byte[image.getWidth()];
	
			    for (int y = 0; y < image.getWidth(); y++) 
			    {
			        pixels[x][y] = (byte) (image.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}

	public static byte[][] GetTrasposedByteMatrix(byte[][] matrix)
	{
	    int m = matrix.length;
	    int n = matrix[0].length;

	    byte[][] trasposedMatrix = new byte[n][m];

	    for(int x = 0; x < n; x++)
	    {
	        for(int y = 0; y < m; y++)
	        {
	            trasposedMatrix[x][y] = matrix[y][x];
	        }
	    }

	    return trasposedMatrix;
	}

	public static int[][] GetTrasposedIntMatrix(int[][] matrix)
	{
	    int m = matrix.length;
	    int n = matrix[0].length;

	    int[][] trasposedMatrix = new int[n][m];

	    for(int x = 0; x < n; x++)
	    {
	        for(int y = 0; y < m; y++)
	        {
	            trasposedMatrix[x][y] = matrix[y][x];
	        }
	    }

	    return trasposedMatrix;
	}
	
	public static int[][] ColorImageTo2DArray(String imagePath)
	{
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(imagePath));
		
			int[][] pixels = new int[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) 
			{
			    pixels[x] = new int[image.getHeight()];
	
			    for (int y = 0; y < image.getHeight(); y++) 
			    {
			        pixels[x][y] = (byte) (image.getRGB(x, y));
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
		
	public static int[][] Get_G_FromImageTo2DArray(String imagePath)
	{
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(imagePath));
		
			int[][] pixels = new int[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) 
			{
			    pixels[x] = new int[image.getHeight()];
	
			    for (int y = 0; y < image.getHeight(); y++) 
			    {
			        pixels[x][y] = (byte) ((image.getRGB(x, y)>> 8) & 0xFF);
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
		
	public static int[][] Get_R_FromImageTo2DArray(String imagePath)
	{
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(imagePath));
		
			int[][] pixels = new int[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) 
			{
			    pixels[x] = new int[image.getHeight()];
	
			    for (int y = 0; y < image.getHeight(); y++) 
			    {
			        pixels[x][y] = (byte) ((image.getRGB(x, y)>> 16) & 0xFF);
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
		
	public static int[][] Get_B_FromImageTo2DArray(String imagePath)
	{
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(imagePath));
		
			int[][] pixels = new int[image.getWidth()][];

			for (int x = 0; x < image.getWidth(); x++) 
			{
			    pixels[x] = new int[image.getHeight()];
	
			    for (int y = 0; y < image.getHeight(); y++) 
			    {
			        pixels[x][y] = (byte) ((image.getRGB(x, y)>> 0) & 0xFF);
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
	
	public static BufferedImage ArrayToColorImage(int[][] matrix) 
	{
	    BufferedImage image = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < matrix.length; x++) {
	        for (int y = 0; y < matrix[0].length; y++) {
	            image.setRGB(x, y, matrix[x][y]);
	        }
	    }

	    return image;
	}
	
	public static BufferedImage CropColorImageByMatrix(int[][] colorImageMatrix, byte[][] matlabResultMatrix) 
	{
		int[][] croppedImageMatrix = new int[matlabResultMatrix.length][matlabResultMatrix[0].length];
		for (int i = 0; i < croppedImageMatrix.length; i++) 
		  {
			    for (int j = 0; j < croppedImageMatrix[i].length; j++) 
			    {
			    	croppedImageMatrix[i][j] = GetIntFromColor(255,255,0);
			    }
		  }
		
		for (int i = 0; i < matlabResultMatrix.length; i++) 
		  {
			    for (int j = 0; j < matlabResultMatrix[i].length; j++) 
			    {
			    	if(matlabResultMatrix[i][j]==0)
			    		croppedImageMatrix[i][j]  = colorImageMatrix[i][j];
			    	else
			    	{
			    		croppedImageMatrix[i][j]  = GetIntFromColor(255,255,0);
			    	}
			    }
		  }
	   
	    return ArrayToColorImage(croppedImageMatrix);
	}
	
	public static BufferedImage CropColorImageByMatrixAndTriangleEdges(int[][] colorImageMatrix, byte[][] matlabResultMatrix, TriangleEdges edges) 
	{
		
		Barycentric barycentricCheck = new Barycentric(edges.leftEdge, edges.rightEdge, edges.bottomEdge);
		int[][] croppedImageMatrix = new int[matlabResultMatrix.length][matlabResultMatrix[0].length];
		for (int i = 0; i < croppedImageMatrix.length; i++) 
		  {
			  for (int j = 0; j < croppedImageMatrix[i].length; j++) 
			  {
				  croppedImageMatrix[i][j] = GetIntFromColor(255,255,0);
			  }
		  }
		
		for (int i = 0; i < matlabResultMatrix.length; i++) 
		  {
			    for (int j = 0; j < matlabResultMatrix[i].length; j++) 
			    {
			    	
			    	if(barycentricCheck.contains(new Point(i,j)) || matlabResultMatrix[i][j]==0 )
			    		croppedImageMatrix[i][j]  = colorImageMatrix[j][i];
			    	else
			    	{
			    		croppedImageMatrix[i][j]  = GetIntFromColor(255,255,0);
			    	}
			    }
		  }
	   
	    return ArrayToColorImage(croppedImageMatrix);
	}
	
	private static int GetIntFromColor(float Red, float Green, float Blue){
	    int R = Math.round(255 * Red);
	    int G = Math.round(255 * Green);
	    int B = Math.round(255 * Blue);

	    R = (R << 16) & 0x00FF0000;
	    G = (G << 8) & 0x0000FF00;
	    B = B & 0x000000FF;

	    return 0xFF000000 | R | G | B;
	}
	
	public static boolean SaveImage(BufferedImage image, String path)
	{
		File ImageFile = new File(path);
	    try {
	        ImageIO.write(image, "png", ImageFile);
	        System.out.println("Image saved successfully: "+path);
	        return true;
	    } catch (IOException e) 
	    {
	    	 System.out.println("Error occurred cannot save Image - "+path);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static void DeleteFileByPath(String filePath)
	{
		try{

    		File file = new File(filePath);

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

    	}catch(Exception e){

    		e.printStackTrace();

    	}

	}
	
	public static int[][] ConvertTo2DWithoutUsingGetRGB(String imagePath) throws IOException 
	{

		BufferedImage image;
		
		image = ImageIO.read(new File(imagePath));
		
			
		
		  final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		  final int width = image.getWidth();
		  final int height = image.getHeight();
		  final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		  int[][] result = new int[height][width];
		  if (hasAlphaChannel) {
		     final int pixelLength = 4;
		     for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
		        int argb = 0;
		        argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
		        argb += ((int) pixels[pixel + 1] & 0xff); // blue
		        argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
		        argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
		        result[row][col] = argb;
		        col++;
		        if (col == width) {
		           col = 0;
		           row++;
		        }
		     }
		  } else {
		     final int pixelLength = 3;
		     for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
		        int argb = 0;
		        argb += -16777216; // 255 alpha
		        argb += ((int) pixels[pixel] & 0xff); // blue
		        argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
		        argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
		        result[row][col] = argb;
		        col++;
		        if (col == width) {
		           col = 0;
		           row++;
		        }
		     }
		  }

		  return result;
		 }
	
	
	
	public static boolean IsThickExist(int[][] R_ImageMatrix, int[][] G_ImageMatrix, int[][] B_ImageMatrix, double thresholdValue1, double thresholdValue2, double thresholdValue3)
	{
		
		//Green image
		int g_average = 0;
		int numberOfPoints = 0;
		for (int i = 0; i < G_ImageMatrix.length; i++) 
		{
			for (int j = 0; j < G_ImageMatrix[i].length; j++)
			{
				if(G_ImageMatrix[i][j]<0)
				{
					numberOfPoints++;
					g_average+=j;
				}
			}
		}
		if(numberOfPoints==0)
			return false;
		 g_average = g_average/numberOfPoints; //approximate Y Location of relevant points in G image
		
		 
		 //Blue image
		 int b_average = 0;
		 numberOfPoints = 0;
		 for (int i = 0; i < B_ImageMatrix.length; i++) 
		 {
			 for (int j = 0; j < B_ImageMatrix[i].length; j++)
			 {
				 if(B_ImageMatrix[i][j]<0)
				 {
					 numberOfPoints++;
					 b_average+=j;
				 }
			 }
		 }
		 if(numberOfPoints==0)
				return false;
		 b_average = b_average/numberOfPoints; //approximate Y Location of relevant points in B image
		 
		 
		 //Red image
		 int r_average = 0;
		 numberOfPoints = 0;
		 List<Point> r_thikPointsLocations = new ArrayList<Point>();
		 for (int i = 0; i < R_ImageMatrix.length; i++) 
		 {
			 for (int j = 0; j < R_ImageMatrix[i].length; j++)
			 {
				 if(R_ImageMatrix[i][j]<0)
				 {
					 numberOfPoints++;
					// if(Math.abs(j-g_average)<10 && Math.abs(j-b_average)<10 )
					 //{
						 r_thikPointsLocations.add(new Point(i,j));
						 r_average+=j;
					 //}
				 }
			 }
		 }
		 if(numberOfPoints==0)
				return false;
		 r_average = r_average/numberOfPoints; //approximate Y Location of relevant points in R image
		 
		 System.out.println("g_average - "+g_average);
		 System.out.println("b_average - "+b_average);
		 System.out.println("r_average - "+r_average);
		 if((Math.abs(r_average-g_average))>10 || (Math.abs(r_average-b_average))>10)
		 {
			 r_average = ((g_average+b_average)/2);
			 
			 System.out.println("r_average changed to - "+r_average);
		 }
		 
		 Collections.sort(r_thikPointsLocations, (Point p1, Point p2) -> p1.x-p2.x);
		
		 //Build Rectangle
		 RectangleEdges rectEdges = RectangleUtils.FindRectangleEdges(r_thikPointsLocations);
		
		 int height = rectEdges.leftDownEdge.y - rectEdges.leftUpEdge.y;
		 int width = rectEdges.rightUpEdge.x - rectEdges.leftUpEdge.x;
		 int coefheight = 0;
		 if(height>10)
		 {
			 coefheight = (int) (height*heightCoef);
		 }
		 else
		 {
			 coefheight = height;
		 }
		 System.out.println("Rectangle height - " + height);
		 System.out.println("rectangle width - " + width);
		
		 
		 Rectangle rect = new Rectangle(rectEdges.leftUpEdge.x, rectEdges.leftUpEdge.y, width, height );
		
		 int numberOfClusterRectanglePoints = 0;
		 for(int i=0; i<r_thikPointsLocations.size();i++)
		 {
			 if(rect.contains(r_thikPointsLocations.get(i).x, r_thikPointsLocations.get(i).y))
				 numberOfClusterRectanglePoints++;
		 }
		
		 System.out.println("numberOfClusterRectanglePoints - " + numberOfClusterRectanglePoints + "/numberOfTotalRectanglePoints- "+(width*coefheight) );
		 //Clean phase 1 
		 double ratio1 = (double)((double)numberOfClusterRectanglePoints/(double)(width*coefheight));
			
		 System.out.println("numberOfClusterRectanglePoints - " + numberOfClusterRectanglePoints + "/numberOfClusterPoints- "+r_thikPointsLocations.size() );
		 //Clean phase 2 - Cluster bigger than rectangle
		 double ratio2 = (double)((double)numberOfClusterRectanglePoints/(double)r_thikPointsLocations.size());
		    
		 System.out.println("Rect ratio1 - "+ratio1);
		 System.out.println("Rect ratio2 - "+ratio2);
		    
		    
		    if(thresholdValue1>=ratio1) 
		    {
		    	System.out.println("TEM not found");
		    	return false;
		    }
		    if(thresholdValue2>=ratio2) 
		    {
		    	System.out.println("TEM not found");
		    	return false;
		    }
		    
		    System.out.println();
		
		//Calculate distance between borders
		List<Point> leftBorderPoints = new ArrayList<Point>();
		List<Point> rightBorderPoints = new ArrayList<Point>();
		
		int y_value = r_average;
		

		int x_leftPoint = 0;
		int x_rightPoint = 0;
		for (int i = 0; i < R_ImageMatrix.length; i++)
		{
			if(R_ImageMatrix [i][r_average] != 1)
			{
				x_leftPoint = i;
				break;
			}
		}
		
		for (int i = R_ImageMatrix.length-1; i >0 ; i--)
		{
			if(R_ImageMatrix [i][r_average] != 1)
			{
				x_rightPoint = i;
				break;
			}
		}
		
		
		
		 System.out.println("x_leftPoint - "+x_leftPoint);
		 System.out.println("x_rightPoint - "+x_rightPoint);
		 double triangleWidth = (x_rightPoint - x_leftPoint);
		 double ratio3 = (width/triangleWidth);
		 System.out.println("Rect ratio3 - "+ratio3);
		if(thresholdValue3>ratio3) 
		{
	    	System.out.println("TEM not found");
	    	return false;
	    }
		
		
		System.out.println("TEM found");
		return true;
	}
	
	public static boolean IsGranulomaExist(int[][] R_ImageMatrix, int[][] G_ImageMatrix, int[][] B_ImageMatrix, double thresholdValue1)
	{
		
		//Green image
		int g_xAverage = 0;
		int g_yAverage = 0;
		int numberOfPoints = 0;
		for (int i = 0; i < G_ImageMatrix.length; i++) 
		{
			for (int j = 0; j < G_ImageMatrix[i].length; j++)
			{
				if(G_ImageMatrix[i][j]<0)
				{
					numberOfPoints++;
					g_xAverage +=i;
					g_yAverage +=j;
				}
			}
		}
		if(numberOfPoints==0)
			return false;
		g_xAverage = g_xAverage/numberOfPoints; //approximate X Location of relevant points in G image
		g_yAverage = g_yAverage/numberOfPoints; //approximate Y Location of relevant points in G image
		 
		 //Blue image
		int b_xAverage = 0;
		int b_yAverage = 0;
		 numberOfPoints = 0;
		 for (int i = 0; i < B_ImageMatrix.length; i++) 
		 {
			 for (int j = 0; j < B_ImageMatrix[i].length; j++)
			 {
				 if(B_ImageMatrix[i][j]<0)
				 {
					 numberOfPoints++;
					 b_xAverage +=i;
					 b_yAverage +=j;
				 }
			 }
		 }
		 if(numberOfPoints==0)
				return false;
		 b_xAverage = b_xAverage/numberOfPoints; //approximate X Location of relevant points in B image
		 b_yAverage = b_yAverage/numberOfPoints; //approximate Y Location of relevant points in B image
		 
		 
		 //Red image
		 int r_xAverage = 0;
		 int r_yAverage = 0;
		 numberOfPoints = 0;
		 List<Point> r_granulomaPointsLocations = new ArrayList<Point>();
		 for (int i = 0; i < R_ImageMatrix.length; i++) 
		 {
			 for (int j = 0; j < R_ImageMatrix[i].length; j++)
			 {
				 if(R_ImageMatrix[i][j]<0)
				 {
					 numberOfPoints++;
					 r_granulomaPointsLocations.add(new Point(i,j));
					 r_xAverage+=i;
					 r_yAverage+=j;
					 
				 }
			 }
		 }
		 
		 
		 if(numberOfPoints==0)
				return false;
		 r_xAverage = r_xAverage/numberOfPoints; //approximate X Location of relevant points in R image
		 r_yAverage = r_yAverage/numberOfPoints; //approximate Y Location of relevant points in R image
		 
		 
		 for(int i = 0; i < r_granulomaPointsLocations.size(); i++)
		 {
			 if(r_granulomaPointsLocations.get(i).x-r_xAverage>70)
			 {
				 r_granulomaPointsLocations.remove(i);
			 }
		 }
		 
		 System.out.println();
		 System.out.println("g_xAverage - "+g_xAverage);
		 System.out.println("g_yAverage - "+g_yAverage);
		 System.out.println();
		 System.out.println("b_xAverage - "+b_xAverage);
		 System.out.println("b_yAverage - "+b_yAverage);
		 System.out.println();
		 System.out.println("r_xAverage - "+r_xAverage);
		 System.out.println("r_yAverage - "+r_yAverage);

		 
		 int x_LeftBorderAverage = 0;
		 List<Point> leftBorderPoints = new ArrayList<Point>();
		 for (int i = 0; i < R_ImageMatrix.length; i++)
		 {
			 for (int j = 0; j < R_ImageMatrix[0].length; j++)
			 {
				 if(R_ImageMatrix [i][j] != 1)
				 {
					 x_LeftBorderAverage+=i;
					 leftBorderPoints.add(new Point(i,j)) ;
					 break;
				 }
			 }
		 }
		 x_LeftBorderAverage = x_LeftBorderAverage/leftBorderPoints.size();
		 
		 int difLeft1 = Math.abs(r_xAverage - x_LeftBorderAverage);
		 
		 int x_RightBorderAverage = 0;
		 List<Point> rightBorderPoints = new ArrayList<Point>();
		 
		
		 //find right border
		 for (int i = R_ImageMatrix.length-1; i >0 ; i--)
		 {
			 for (int j = R_ImageMatrix[0].length-1; j > 0; j--)
			 {
				 if(R_ImageMatrix [i][j] != 1)
				 {
					 x_RightBorderAverage+=i;
					 rightBorderPoints.add(new Point(i,j)) ;
					 break;
				 }
			 }
		 }
		 
		 
		 
		 x_RightBorderAverage = x_RightBorderAverage/leftBorderPoints.size();
					 
		 int difRight2 = Math.abs(r_xAverage - x_RightBorderAverage);
		 
		 
		 boolean leftBorderStatus = false;
		 if(difLeft1>difRight2)
		 {
			 //Granuloma is on the right border
			 leftBorderStatus = false;
		 }
		 else
		 {
			 //Granuloma is on the left border
			 leftBorderStatus = true;
		 }
		 
		 CircleData circleData = CircleUtils.FindCircleEdges(r_granulomaPointsLocations,leftBorderStatus);
		 int r_granulomaPointsInCircle = 0;
		 for (int i = 0; i < r_granulomaPointsLocations.size(); i++) 
		 {
			 if(CircleUtils.IsPointInCircle(circleData, r_granulomaPointsLocations.get(i)))
			 {
				 r_granulomaPointsInCircle ++;
			 }
		 }
		 
		 double ratio = (double)r_granulomaPointsInCircle/(double)numberOfPoints;
		 System.out.println("ratio - (double)r_granulomaPointsInCircle"+r_granulomaPointsInCircle+"/(double)numberOfPoints"+numberOfPoints+" = "+ratio);
		 if(thresholdValue1>ratio)
		 {
			 System.out.println();
			 System.out.println("Granuloma not found");
			 return false;
		 }
		 
		 System.out.println("Granuloma found");
		 return true;
	}
	
	
}
