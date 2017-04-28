package Class;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImproveFocus {

	    

	
	public void blur(int isv){
		BufferedImage image = null;
		 int radius = 1;
		    int size = radius * 2 + 1;
		    float [] data = new float[]{0.077847f,0.123317f,0.077847f,0.123317f,0.195346f,0.123317f,0.077847f,0.123317f,0.077847f};
		    float weight =  (1.0f / (size * size));
		    try {
				image = ImageIO.read(new File("4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    //for(int i = 0 ; i < 6 ; i ++)
		 	  //  data[i] = weight;
			Kernel kernel = new Kernel(3, 3, data);
			BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
			BufferedImage blurred = op.filter(image, null);
			//CUtils.SaveImage(blurred, "C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"blur.png");
			CUtils.SaveImage(blurred,"C:\\Users\\omri\\Desktop\\New folder\\" + "blur.png");
	}
	
	public float[] makeKernelGuassian(float sigma,int size){
		float res = 0,exp = 0,fgf=0;
		float [] matrix = new float[size*size];
		int count =0;
		for(int i = 0 ; i < size; i ++){
			System.out.println();
			for(int j = 0 ; j < size ; j ++){
				
				//res = (float) (Math.exp((Math.pow(i, 2)+Math.pow(j, 1))/2*Math.pow(sigma, 2))/(2*Math.PI*Math.pow(sigma, 2)));
				res = (float) (Math.PI*Math.pow(sigma,2));
				//res *= 0.5;
				exp = (float) Math.exp(-(Math.pow(i, 2)+Math.pow(j,2))/(2*Math.pow(sigma,2)));
				res = exp/ (2*res);
				System.out.print(res+"\t");
				matrix[count]=res;
				fgf+=res;
				res=0;
				count++;
			}
		}
		System.out.println(fgf);
		return matrix;
		
	}
}


