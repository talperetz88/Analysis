package Class;

import java.awt.image.BufferedImage;

public class Histogram {
	
	 int[] redBin = new int[256];
	 int[] blueBin = new int[256];
	 int[] greenBin = new int[256];
	 int[] HBin = new int[180];
	 int[] SBin = new int[256];
	 int sumRed,sumGreen,sumBlue,sumS,sumH, meanRed , meanGreen ,  meanBlue; 
	 
	 public Histogram(BufferedImage img){
			for(int i=0;i<img.getWidth();i++)
				for(int j=0;j<img.getHeight();j++){
					int color = img.getRGB(i,j);
					int blue = color & 0xff;
					int green = (color & 0xff00) >> 8;
					int red = (color & 0xff0000) >> 16;
					blueBin[blue]++;
					redBin[red]++;
					greenBin[green]++;
					int[] HS = RGBtoH(color);
					SBin[HS[0]]++;
					HBin[HS[1]]++;
				}
			 sumRed=0;  
			 sumGreen=0;  
			 sumBlue=0;
			 sumS=0;
			 sumH=0;
			for(int i=0;i<256;i++){
				
				 sumRed+=redBin[i];  
				 sumGreen+=greenBin[i];  
				 sumBlue+=blueBin[i];  
				 sumS+=SBin[i];
				 if(i<180)
					 sumH+=HBin[i];
				 
			}
				meanRed=sumRed/256;
				meanGreen=sumGreen/256;
				meanBlue=sumBlue/256;	
			
	 }
	 
		// function that returns the H and s value from the RGB components S is the first in the array and H is the second 
		public int[] RGBtoH(int color){
			int[] arr = new int[2];
			int blue = color & 0xff;
			int green = (color & 0xff00) >> 8;
			int red = (color & 0xff0000) >> 16;
		    int min = Math.min(Math.min(red, green), blue);
		    int max = Math.max(Math.max(red, green), blue);
		    int s = 1-(min/max);
		    arr[0]=s;
				if(max==min){
					arr[1]=0;
					return arr;
				}
				if(max==red){
					arr[1]=60*(green-blue)/(max-min); ;
					return arr;
				}
				if(max==green){
					arr[1]= 60*(2+blue-red)/(max-min);
					return arr;
				}
				arr[1]= 60*(4+red+green)/(max-min);
				return arr;
					
			
		}

}
