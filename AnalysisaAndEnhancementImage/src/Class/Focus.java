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
	
	public double MeanGrayscale(String path){
		
		double sum=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
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
	
	public double MeanLuminance(String path){
		
		double sum=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
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
	public double FocusMeasuresBasedOnImageStatisticsVariance(String path , int index){
			
			double mean,sum=0;
			if(index==1)
				mean=MeanGrayscale(path);
			else
				mean=MeanLuminance(path);
			BufferedImage img = null;
			 try {
				 img = ImageIO.read(new File(path));
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
	public double FocusMeasuresBasedOnImageStatisticsNormalizedVariance(String path , int index){
			double mean,sum=0;
			if(index==1)
				mean=MeanGrayscale(path);
			else
				mean=MeanLuminance(path);
			BufferedImage img = null;
			 try {
				 img = ImageIO.read(new File(path));
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
	public double FunctionBasedOnDepthOfPeaksAndValleysA(String path , float threshold){
		
		double sum=0,sumtest=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 sumtest+=temp;
				 if(temp>=threshold)
					 sum+=temp;
				 
			 }
		 sumtest=sumtest/(img.getWidth()*img.getHeight());
		 //System.out.println(sumtest);
		 return sum;
		
	}
	
	//function 2.5.2 [10]
	public double FunctionBasedOnDepthOfPeaksAndValleysB(String path , float threshold){
		
		int  sum=0,sumtest=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 sumtest+=temp;
				 if(temp>=threshold)
					 sum++;
			 }
		 sumtest=sumtest/(img.getHeight()*img.getWidth());
		 //System.out.println("sum test"+sumtest);
		 return sum;
		
	}
	
	//function 2.5.2 [11]
	public double FunctionBasedOnDepthOfPeaksAndValleysC(String path , float threshold){
		
		int  sum=0,sumtest=0;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
				 float temp = Luminance(img.getRGB(i,j));
				 sumtest+=temp;
				 if(temp>=threshold)
					 sum+=Math.pow(temp, 2);
				 
			 }
		 sumtest=sumtest/(img.getHeight()*img.getWidth());
		// System.out.println("the tset sum is  : "+sumtest);
		 return sum;
		
	}	
	
	//function 2.5.3 [12]
	//high value is better
	
	public double FocusMeasuresBasedOnImageDifferentiationA(String path,int threshold){
		double sum=0,sumtest=0,v;//v is the gradient threshold
		v=threshold;
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
					float gray1 = Grayscale(img.getRGB(i,j));
					float gray2 = Grayscale(img.getRGB(i,j+1));
					double temp =Math.abs(gray2-gray1);
					sumtest+=Math.pow(temp, 2);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		 sumtest=sumtest/(img.getHeight()*img.getWidth());
		// System.out.println("the test sum is "+sumtest);
		return sum;
	}
	
	//function 2.5.3 [13]
	public double FocusMeasuresBasedOnImageDifferentiationB(String path,int threshold){
		
		double sumtest=0,sum=0,v=threshold;//v is the gradient threshold
		BufferedImage img = null;
		 try {
			 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i =0;i<img.getWidth();i++)
			 for(int j=0;j<img.getHeight()-1;j++){
					float gray1 = Grayscale(img.getRGB(i,j));
					float gray2 = Grayscale(img.getRGB(i,j+1));
					double temp = Math.pow((gray2-gray1),2);
					sumtest+=Math.pow(temp, 2);
					if(Math.pow(temp, 2)>=v)
						sum+=temp;

			 }
		 sumtest=sumtest/(img.getHeight()*img.getWidth());
		// System.out.println("the sum test "+ sumtest);
		return sum;
	}
	


}
