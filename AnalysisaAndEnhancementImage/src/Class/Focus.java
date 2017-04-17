package Class;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Focus {
	
	
	
	
	
	
	//function that gets RGB pixel and returns the grayscale value 
	public float Grayscale(int color){
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
	
	public double MeanGrayscale(String imageName){
		
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
					 sum += Grayscale(img.getRGB(i,j));
				 }
			 return sum/(double)(img.getHeight()*img.getWidth());
	}
	
	public double MeanLuminance(String imageName){
		
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
					 sum += Luminance(img.getRGB(i,j));
				 }
			 return sum/(double)(img.getHeight()*img.getWidth());
	}
	
	//function 2.5.1 [7] in case the index is 1 it means grayscale , if the index is 2 it means Luminance
	public double FocusMeasuresBasedOnImageStatisticsVariance(String imageName , int index){
			
			double mean,sum=0;
			if(index==1)
				mean=MeanGrayscale(imageName);
			else
				mean=MeanLuminance(imageName);
			BufferedImage img = null;
			 try {
				 img = ImageIO.read(new File(imageName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(index==1){
				 for(int i =0;i<img.getWidth();i++)
					 for(int j=0;j<img.getHeight()-1;j++){
						 sum+=Math.pow((Grayscale(img.getRGB(i,j)))-(mean), 2);
					 
					 	}
			 }
			 else
				 for(int i =0;i<img.getWidth();i++)
					 for(int j=0;j<img.getHeight()-1;j++){
						 sum+=Math.pow((Luminance(img.getRGB(i,j)))-(mean), 2);
					 
					 	}
			
			return sum/(double)(img.getHeight()*img.getWidth());
			
		}
	
	//function 2.5.1 [8] in case the index is 1 it means grayscale , if the index is 2 it means Luminance
	public double FocusMeasuresBasedOnImageStatisticsNormalizedVariance(String imageName , int index){
			double mean,sum=0;
			if(index==1)
				mean=MeanGrayscale(imageName);
			else
				mean=MeanLuminance(imageName);
			BufferedImage img = null;
			 try {
				 img = ImageIO.read(new File(imageName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(index==1){
				 for(int i =0;i<img.getWidth();i++)
					 for(int j=0;j<img.getHeight()-1;j++){
						 sum+=Math.pow((Grayscale(img.getRGB(i,j)))-(mean), 2);
					 
					 	}
			 }
			 else
				 for(int i =0;i<img.getWidth();i++)
					 for(int j=0;j<img.getHeight()-1;j++){
						 sum+=Math.pow((Luminance(img.getRGB(i,j)))-(mean), 2);
					 
					 	}
			
			return sum/((double)(img.getHeight()*img.getWidth())*mean);
			
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
	
	//function 2.5.2 [12]
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
					float gray1 = Grayscale(img.getRGB(i,j));
					float gray2 = Grayscale(img.getRGB(i,j+1));
					double temp =Math.abs(gray2-gray1);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		return sum;
	}
	
	//function 2.5.2 [13]
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
					float gray1 = Grayscale(img.getRGB(i,j));
					float gray2 = Grayscale(img.getRGB(i,j+1));
					double temp = Math.pow((gray2-gray1),2);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		return sum;
	}
	
	

	
	
	
	
	
	


}
