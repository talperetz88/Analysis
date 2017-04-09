package Class;

public class Image extends ImageData{
	

	public Image(){
		
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
