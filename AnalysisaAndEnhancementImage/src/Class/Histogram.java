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
	 float hTop,redTop,greenTop,blueTop;
	 
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
			}
			redTop=sumRed/256;
			greenTop=sumGreen/256;
			blueTop=sumBlue/256;
				 for(int i=0;i<360;i++)
					 	sumH+=HBin[i];
				 hTop=sumH/360;
			
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
		
		//the function is in the book page 4 
		public double intersectionHSV(Histogram hist){
			double sum=0;
			for(int i=0;i<360;i++){
				sum+=Math.min(this.HBin[i],hist.HBin[i]);
			}
			return sum/((double)(this.sumH));
			
		}
		
		//the function is in the book page 4 
		public double intersectionRGB(Histogram hist){
			double sum=0;
			for(int i=0;i<256;i++){
				sum+=Math.min(this.redBin[i],hist.redBin[i]);
				sum+=Math.min(this.greenBin[i],hist.greenBin[i]);
				sum+=Math.min(this.blueBin[i],hist.blueBin[i]);
			}
			return sum/((double)(this.sumRed+this.sumGreen+this.sumBlue));
			
		}
		
		//the function is in the book page 4 
		public double correlationHSV(Histogram hist){
			
			double sumTop=0,sumDownFirst=0,sumDownSec=0;
			for(int i=0;i<360;i++){
				sumTop+=((this.HBin[i]-this.hTop)*(hist.HBin[i]-hist.hTop));
				sumDownFirst+=Math.pow((this.HBin[i]-this.hTop),2);
				sumDownSec+=Math.pow((hist.HBin[i]-hist.hTop),2);
			}
			double temp = Math.sqrt((sumDownFirst*sumDownSec));
			return sumTop/temp;
			
		}
		
		//function thats gets index , the index will indicate which component the function will calculate 1 for red 
		//2 for blue 3 for green
		public double helpToCorrelationRGB(int index,Histogram hist){ // צריך שם יותר טוב 
			double sumTop=0,sumDownFirst=0,sumDownSec=0;
			if(index==1){
				for(int i=0;i<256;i++){
					sumTop+=((this.redBin[i]-this.redTop)*(hist.redBin[i]-hist.redTop));
					sumDownFirst+=Math.pow((this.redBin[i]-this.redTop),2);
					sumDownSec+=Math.pow((hist.redBin[i]-hist.redTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
			}
			if(index==2){
				for(int i=0;i<256;i++){
					sumTop+=((this.greenBin[i]-this.greenTop)*(hist.greenBin[i]-hist.greenTop));
					sumDownFirst+=Math.pow((this.greenBin[i]-this.greenTop),2);
					sumDownSec+=Math.pow((hist.greenBin[i]-hist.greenTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
				
			}
			if(index==3){
					for(int i=0;i<256;i++){
					sumTop+=((this.blueBin[i]-this.blueTop)*(hist.blueBin[i]-hist.blueTop));
					sumDownFirst+=Math.pow((this.blueBin[i]-this.blueTop),2);
					sumDownSec+=Math.pow((hist.blueBin[i]-hist.blueTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
			}
			return -1; //in case of error 

			
		}
		
		//the function is in the book page 4 
		public double correlationRGB(Histogram hist){
			double [] sumComponent = new double [3];
			double sum=0;
			for(int j=0;j<3;j++){
				sum+=sumComponent[j]=helpToCorrelationRGB(j+1,hist);
			}
			return sum/3;
			
		}
		
		//the function is in the book page 4 
		public double chiSquareHSV(Histogram hist){
			double sumTop=0,sumDown=0,sum=0;
			for(int i=0;i<360;i++){
					sumTop=(Math.pow((this.HBin[i]-hist.HBin[i]),2));
					sumDown=this.HBin[i]+hist.HBin[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}

			double sumH =  sum;
			
			sumTop=0;sumDown=0;sum=0;
			for(int i=0;i<256;i++){
					sumTop=(Math.pow((this.SBin[i]-hist.SBin[i]),2));
					sumDown=this.SBin[i]+hist.SBin[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}
			double sumS =  sum;
			return 0.5*sumH+0.5*sumS;
		}
		
		public double chiSquareRGB(Histogram hist){
			double sumTop=0,sumDown=0,sum=0;
			for(int i=0;i<256;i++){
					sumTop=(Math.pow((this.redBin[i]-hist.redBin[i]),2));
					sumDown=this.redBin[i]+hist.redBin[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}
			double red=sum;
			sumTop=sumDown=sum=0;
			for(int i=0;i<256;i++){
				sumTop=(Math.pow((this.greenBin[i]-hist.greenBin[i]),2));
				sumDown=this.greenBin[i]+hist.greenBin[i];
				if(sumDown != 0)
					sum+=sumTop/sumDown;
				sumTop=sumDown=0;
		}
			double green=sum;
			
			sumTop=sumDown=sum=0;
			for(int i=0;i<256;i++){
				sumTop=(Math.pow((this.blueBin[i]-hist.blueBin[i]),2));
				sumDown=this.blueBin[i]+hist.blueBin[i];
				if(sumDown != 0)
					sum+=sumTop/sumDown;
				sumTop=sumDown=0;
		}
			double blue=sum;
			sum = 0.33333*red+0.33333*green+0.3333*blue;
			return sum;
		}
		
		//the function is in the book page 4 
		public double BhattacharyyaDistanceHSV(Histogram hist){
			double sum=0;
			double temp=(1/(Math.sqrt(this.hTop*hist.hTop*360*360)));
			for(int i=0;i<360;i++){
				sum+=Math.sqrt((this.HBin[i]*hist.HBin[i]));
			}
			if(1-(temp*sum)<=0) // in case 1-(temp*sum) is equal to zero or negative 
				return 0;
			else 
			return Math.sqrt(1-(temp*sum));
		}
		
		//the function is in the book page 4 
		public double BhattacharyyaDistanceRGB(Histogram hist){
			double sumRedComponent=0,sumGreenComponent=0,sumBlueComponent=0;
			double sqrtRed,sqrtGreen,sqrtBlue;
			double red=(1/(Math.sqrt(this.redTop*hist.redTop*256*256)));
			double green=(1/(Math.sqrt(this.greenTop*hist.greenTop*256*256)));
			double blue=(1/(Math.sqrt(this.blueTop*hist.blueTop*256*256)));
			for(int i=0;i<256;i++){
				sumRedComponent+=Math.sqrt((this.redBin[i]*hist.redBin[i]));
				sumGreenComponent+=Math.sqrt((this.greenBin[i]*hist.greenBin[i]));
				sumBlueComponent+=Math.sqrt((this.blueBin[i]*hist.blueBin[i]));
			}
			if(1-(red*sumRedComponent)<=0) // in case 1-(red*sumRedComponent) is equal to zero or negative 
				 sqrtRed = 0;
			else 
				 sqrtRed = Math.sqrt(1-(red*sumRedComponent));
			
			if(1-(green*sumGreenComponent)<=0) 
				 sqrtGreen = 0;
			else 
				 sqrtGreen = Math.sqrt(1-(green*sumGreenComponent));
			
			if(1-(green*sumBlueComponent)<=0) 
				 sqrtBlue = 0;
			else 
				 sqrtBlue = Math.sqrt(1-(blue*sumBlueComponent));
			System.out.println("red   "+sqrtRed+" green    "+sqrtGreen+" blue   "+sqrtBlue);
			return (sqrtRed+sqrtGreen+sqrtBlue)/3;
		}

}
