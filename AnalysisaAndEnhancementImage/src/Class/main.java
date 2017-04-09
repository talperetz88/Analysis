package Class;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.*;
public class main {

	public static void main(String[] arg){
		
		//startGUI open = new startGUI();
		
		//loding an image 
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("a.jpg"));
		} catch (IOException e) {
			System.err.println(e);
		}
		//
		Image image = new Image();
		int color = img.getRGB(0,0);
		System.out.println(image.RGBtoH(color));
		
	}

}
