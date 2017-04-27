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
		    float [] data = new float[size*size];
		    float weight =  (1.0f / (size * size));
		    try {
				image = ImageIO.read(new File("4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    for(int i = 0 ; i < 6 ; i ++)
		 	    data[i] = weight;
			Kernel kernel = new Kernel(3, 3, data);
			BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
			BufferedImage blurred = op.filter(image, null);
			CUtils.SaveImage(blurred, "C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"blur.png");
	}
}


