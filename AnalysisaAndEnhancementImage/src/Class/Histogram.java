package Class;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Histogram {
	
	 int[] redBin = new int[256];
	 int[] blueBin = new int[256];
	 int[] greenBin = new int[256];
	 int[] HBin = new int[360];
	 int[] SBin = new int[256];
	 int sumRed,sumGreen,sumBlue,sumS,sumH, meanRed , meanGreen , meanBlue , meanS, meanH; 
	 
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
					int[] HS = RGBtoHSV(color);
					SBin[HS[0]]++;
					if(HS[1]>=360)
						System.out.println("fuck me"+HS[1]);
					else
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
				meanS=meanS/256;
				meanH=meanH/180;
			
	 }
	
		// function that returns the H and s value from the RGB components S is the first in the array and H is the second 
		public int[] RGBtoHSV(int color){
			int blue = color & 0xff;
			int green = (color & 0xff00) >> 8;
			int red = (color & 0xff0000) >> 16;
		    int min = Math.min(Math.min(red, green), blue);
		    int max = Math.max(Math.max(red, green), blue);
		    int delta = max-min;
		    int s,h;
		    if(max==0)
		    	s=0;
		    else
		    	s=delta/max;
				if(max==min){
					h=0;
					//System.out.println("RGB "+red+" "+green+" "+blue+"   "+h);
					return new int[] {s,h};
				}
				if(max==red){
					h=60*(green-blue)/(max-min); 
					if(h<0)
						h+=360;
					//System.out.println("RGB "+red+" "+green+" "+blue+"   "+h);
					return new int [] {s,h};
				}
				if(max==green){
					h= 60*(2+blue-red)/(max-min);
					if(h<0)
						h+=360;
					//System.out.println("RGB "+red+" "+green+" "+blue+"   "+h);
					return new int [] {s,h};
				}
				h= 60*(4+red-green)/(max-min);
				if(h<0)
					h+=360;
				//System.out.println("RGB "+red+" "+green+" "+blue+"   "+h);
				return new int [] {s,h};
					
			
		}

		public double IntersectionHSV(Histogram hist){
			double sum=0;
			for(int i=0;i<360;i++)
				sum+=Math.min(this.HBin[i],hist.HBin[i]);
			System.out.println("the sum"+sum+" "+this.sumH);
			return sum/((double)(this.sumH+hist.sumH));
			
		}
		
		public double IntersectionRGB(Histogram hist){
			double sum=0;
			for(int i=0;i<256;i++){
				sum+=Math.min(this.redBin[i],hist.redBin[i]);
				sum+=Math.min(this.greenBin[i],hist.greenBin[i]);
				sum+=Math.min(this.blueBin[i],hist.blueBin[i]);
			}
			System.out.println("the sum is "+sum+" "+this.sumRed+hist.sumRed+this.sumGreen+hist.sumGreen+this.sumBlue+hist.sumBlue);
			return sum/((double)(this.sumRed+hist.sumRed+this.sumGreen+hist.sumGreen+this.sumBlue+hist.sumBlue));
			
		}
		
}
