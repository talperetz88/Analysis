package Class;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.*;
public class main {

	public static void main(String[] arg) throws IOException{
		
		//startGUI open = new startGUI();
		
		//loding an image 
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("f.jpg"));
		} catch (IOException e) {
			System.err.println(e);
		}
		//loding an image 
		BufferedImage img1 = null;
		try {
		   img1 = ImageIO.read(new File("u.jpg"));
		} catch (IOException e) {
			System.err.println(e);
		}
		//
		
		System.out.println("the edeg is:");
		
		Histogram hist = new Histogram(img);
		Histogram hist1 = new Histogram(img1);
		System.out.println("HSV Intersection "+hist.intersectionHSV(hist1));
		System.out.println("RGB Intersection "+hist.intersectionRGB(hist1));
		System.out.println("HSV Correlation "+hist.correlationHSV(hist1));
		System.out.println("RGB Correlation "+hist.correlationRGB(hist1));
		System.out.println("HSV chiSquare "+hist.chiSquareHSV(hist1));
		System.out.println("HSV BhattacharyyaDistanceHSV "+hist.BhattacharyyaDistanceHSV(hist1));
		System.out.println("HSV BhattacharyyaDistanceRGB "+hist.BhattacharyyaDistanceRGB(hist1));
		BlockMatching blockMatching = new BlockMatching();
		//Point point = new Point(89,30);
		
		System.out.println("the MAD is "+blockMatching.identifyTheRequirArea("MAD",10));
		//System.out.println("the MES is "+blockMatching.identifyTheRequirArea("MES"));
	}

}
