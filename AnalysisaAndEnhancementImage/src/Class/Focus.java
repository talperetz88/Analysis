package Class;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Focus {
	
	
	
	
	
	
	
	public float grayscale(int color){
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		return (float) ((0.21*red)+(0.72*green)+(0.07*blue));
	}
	
	//the function is on page 8
	public double FocusMeasuresBasedOnImageDifferentiationA(String imageName){
		
		double sum=0,v=0;//v is the gradient threshold
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
					float gray1 = grayscale(img.getRGB(i,j));
					float gray2 = grayscale(img.getRGB(i,j+1));
					double temp =Math.abs(gray2-gray1);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		return sum;
	}
	
	//the function is on page 8
	public double FocusMeasuresBasedOnImageDifferentiationB(String imageName){
		
		double sum=0,v=0;//v is the gradient threshold
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
					float gray1 = grayscale(img.getRGB(i,j));
					float gray2 = grayscale(img.getRGB(i,j+1));
					double temp = Math.pow((gray2-gray1),2);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		return sum;
	}

}
