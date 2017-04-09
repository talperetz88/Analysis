package Class;

public class Image extends ImageData{
	

	public Image(){
		
	}
	
	// function that returns the H value from the RGB components 
	public int RGBtoH(int color){
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
	    int min = Math.min(Math.min(red, green), blue);
	    int max = Math.max(Math.max(red, green), blue);
			if(max==min)
				return 0;
			if(max==red)
				return 60*(green-blue)/(max-min); 
			if(max==green)
				return 60*(2+blue-red)/(max-min);
			return 60*(4+red+green)/(max-min);
				
		
	}
	
	

}
