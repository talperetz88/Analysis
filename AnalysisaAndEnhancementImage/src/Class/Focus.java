package Class;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Focus {
	
	
	
	
	
	
	//function that gets RGB pixel and returns the grayscale value 
	public float grayscale(int color){
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		return (float) ((0.21*red)+(0.72*green)+(0.07*blue));
	}
	
	//function that gets RGB pixel and returns the luminance value 
	public float Luminance(int color){
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		return (float) ((0.2126*red)+(0.7152*green)+(0.0722*blue));
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
	
	
	//function 2.5.2 [9]
	public double FunctionBasedOnDepthOfPeaksAndValleysA(String imageName , float threshold){
		
		double sum=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 if(temp>=threshold)
					 sum+=temp;
				 
			 }
		 return sum;
		
	}
	
	//function 2.5.2 [10]
	public double FunctionBasedOnDepthOfPeaksAndValleysB(String imageName , float threshold){
		
		int  sum=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 if(temp>=threshold)
					 sum++;
				 
			 }
		 return sum;
		
	}
	
	//function 2.5.2 [11]
	public double FunctionBasedOnDepthOfPeaksAndValleysC(String imageName , float threshold){
		
		int  sum=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 if(temp>=threshold)
					 sum+=Math.pow(temp, 2);
				 
			 }
		 return sum;
		
	}
	
}
