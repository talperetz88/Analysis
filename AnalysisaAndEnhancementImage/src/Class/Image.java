package Class;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends CUtils{
	
	
	//@Override
	public static int[][] ColorImageTo2DArray(String image)
	{
		BufferedImage image1;
		try 
		{
			image1 = ImageIO.read(new File(image));
		
			int[][] pixels = new int[image1.getWidth()][];

			for (int x = 0; x < image1.getWidth(); x++) 
			{
			    pixels[x] = new int[image1.getHeight()];
	
			    for (int y = 0; y < image1.getHeight(); y++) 
			    {
			        pixels[x][y] = (byte) (image1.getRGB(x, y));
			    }
			}
			return pixels;
			
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
	
	

}
