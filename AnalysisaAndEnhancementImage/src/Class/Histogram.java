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
	 float sTop,hTop,redTop,greenTop,blueTop;
	 
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
				sTop=sumS/256;
				meanRed=sumRed/256;
				meanGreen=sumGreen/256;
				meanBlue=sumBlue/256;	
				meanS=meanS/256;
				meanH=meanH/360;
			
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
		public double intersectionHSV(Histogram histA , Histogram histB){
			double sum=0;
			for(int i=0;i<360;i++){
				sum+=Math.min(histA.HBin[i],histB.HBin[i]);
			}
			return sum/((double)(this.sumH));
			
		}
		
		//the function is in the book page 4 
		public double intersectionRGB(Histogram histA,Histogram histB){
			double sum=0;
			for(int i=0;i<256;i++){
				sum+=Math.min(histA.redBin[i],histB.redBin[i]);
				sum+=Math.min(histA.greenBin[i],histB.greenBin[i]);
				sum+=Math.min(histA.blueBin[i],histB.blueBin[i]);
			}
			return sum/((double)(this.sumRed+this.sumGreen+this.sumBlue));
			
		}
		
		//the function is in the book page 4 
		public double correlationHSV(Histogram histA , Histogram histB){
			
			double sumTop=0,sumDownFirst=0,sumDownSec=0;
			for(int i=0;i<360;i++){
				sumTop+=((histA.HBin[i]-histA.hTop)*(histB.HBin[i]-histB.hTop));
				sumDownFirst+=Math.pow((histA.HBin[i]-histA.hTop),2);
				sumDownSec+=Math.pow((histB.HBin[i]-histB.hTop),2);
			}
			double temp = Math.sqrt((sumDownFirst*sumDownSec));
			return sumTop/temp;
			
		}
		
		//function thats gets index , the index will indicate which component the function will calculate 1 for red 
		//2 for blue 3 for green
		public double helpToCorrelationRGB(int index , Histogram histA , Histogram histB){ // צריך שם יותר טוב 
			double sumTop=0,sumDownFirst=0,sumDownSec=0;
			if(index==1){
				for(int i=0;i<256;i++){
					sumTop+=((histA.redBin[i]-histA.redTop)*(histB.redBin[i]-histB.redTop));
					sumDownFirst+=Math.pow((histA.redBin[i]-histA.redTop),2);
					sumDownSec+=Math.pow((histB.redBin[i]-histB.redTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
			}
			if(index==2){
				for(int i=0;i<256;i++){
					sumTop+=((histA.greenBin[i]-histA.greenTop)*(histB.greenBin[i]-histB.greenTop));
					sumDownFirst+=Math.pow((histA.greenBin[i]-histA.greenTop),2);
					sumDownSec+=Math.pow((histB.greenBin[i]-histB.greenTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
				
			}
			if(index==3){
					for(int i=0;i<256;i++){
					sumTop+=((histA.blueBin[i]-this.blueTop)*(histB.blueBin[i]-histB.blueTop));
					sumDownFirst+=Math.pow((histA.blueBin[i]-histA.blueTop),2);
					sumDownSec+=Math.pow((histB.blueBin[i]-histB.blueTop),2);
				}
				double temp = Math.sqrt((sumDownFirst*sumDownSec));
				return sumTop/temp;
			}
			return -1; //in case of error 

			
		}
		
		//the function is in the book page 4 
		public double correlationRGB(Histogram histA , Histogram histB ){
			double [] sumComponent = new double [3];
			double sum=0;
			for(int j=0;j<3;j++){
				sum+=sumComponent[j]=helpToCorrelationRGB(j+1,histA,histB);
			}
			return sum/3;
			
		}
		
		//the function is in the book page 4 
		public double chiSquareHSV(Histogram histA , Histogram histB){
			 float [] H1 = new float[360];
			 float [] S1 = new float[256]; 
			 float [] H2 = new float[360];
			 float [] S2 = new float[256];  
			double sumTop=0,sumDown=0,sum=0,sumh=0,sums=0;
			for(int i=0;i<360;i++){
				sumh+=histA.HBin[i];
				if(i<256)
					sums+=histA.SBin[i];
				
			}
			for(int i=0;i<360;i++){
				H1[i]= (float) (histA.HBin[i]/sumh);
				if(i<256)
					S1[i]=(float)(histA.SBin[i]/sums);
				
			}
			sums=0;sumh=0;
			for(int i=0;i<360;i++){
				sumh+=histB.HBin[i];
				if(i<256)
					sums+=histB.SBin[i];
				
			}
			for(int i=0;i<360;i++){
				H2[i]=(float) (histB.HBin[i]/sumh);
				if(i<256)
					S2[i]=(float)(histB.SBin[i]/sums);
				
			}
				
			for(int i=0;i<360;i++){
					sumTop=(Math.pow((H1[i]-H2[i]),2));
					sumDown=H1[i]+H2[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}

			double sumH =  sum;
			
			sumTop=0;sumDown=0;sum=0;
			for(int i=0;i<256;i++){
					sumTop=(Math.pow((S1[i]-S1[i]),2));
					sumDown=S1[i]+S2[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}
			double sumS =  sum;
			return 0.5*sumH+0.5*sumS;
		}
		
		public double chiSquareRGB(Histogram histA , Histogram histB ){
			double sumTop=0,sumDown=0,sum=0;
			for(int i=0;i<256;i++){
					sumTop=(Math.pow((histA.redBin[i]-histB.redBin[i]),2));
					sumDown=histA.redBin[i]+histB.redBin[i];
					if(sumDown != 0)
						sum+=sumTop/sumDown;
					sumTop=sumDown=0;
			}
			double red=sum;
			sumTop=sumDown=sum=0;
			for(int i=0;i<256;i++){
				sumTop=(Math.pow((histA.greenBin[i]-histB.greenBin[i]),2));
				sumDown=histA.greenBin[i]+histB.greenBin[i];
				if(sumDown != 0)
					sum+=sumTop/sumDown;
				sumTop=sumDown=0;
		}
			double green=sum;
			
			sumTop=sumDown=sum=0;
			for(int i=0;i<256;i++){
				sumTop=(Math.pow((histA.blueBin[i]-histB.blueBin[i]),2));
				sumDown=histA.blueBin[i]+histB.blueBin[i];
				if(sumDown != 0)
					sum+=sumTop/sumDown;
				sumTop=sumDown=0;
		}
			double blue=sum;
			sum = 0.33333*red+0.33333*green+0.3333*blue;
			return sum;
		}
		
		//the function is in the book page 4 
		public double BhattacharyyaDistanceHSV(Histogram histA , Histogram histB){
			double sum=0;
			double temp=(1/(Math.sqrt(histA.hTop*histB.hTop*360*360)));
			for(int i=0;i<360;i++){
				sum+=Math.sqrt((histA.HBin[i]*histB.HBin[i]));
			}
			if(1-(temp*sum)<=0) // in case 1-(temp*sum) is equal to zero or negative 
				return 0;
			else 
			return Math.sqrt(1-(temp*sum));
		}
		
		//the function is in the book page 4 
		public double BhattacharyyaDistanceRGB(Histogram histA , Histogram histB){
			double sumRedComponent=0,sumGreenComponent=0,sumBlueComponent=0;
			double sqrtRed,sqrtGreen,sqrtBlue;
			double red=(1/(Math.sqrt(histA.redTop*histB.redTop*256*256)));
			double green=(1/(Math.sqrt(histA.greenTop*histB.greenTop*256*256)));
			double blue=(1/(Math.sqrt(histA.blueTop*histB.blueTop*256*256)));
			for(int i=0;i<256;i++){
				sumRedComponent+=Math.sqrt((histA.redBin[i]*histB.redBin[i]));
				sumGreenComponent+=Math.sqrt((histA.greenBin[i]*histB.greenBin[i]));
				sumBlueComponent+=Math.sqrt((histA.blueBin[i]*histB.blueBin[i]));
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
