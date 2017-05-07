package Class;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;
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
		
		//Focus f =new Focus();
		//ImproveFocus t = new ImproveFocus();
		//t.blur(4);
		//t.creatMask("pathOriginal", "dbsaj");
		//t.cMask();
		//System.out.println(f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\Users\\omri\\Desktop\\New folder\\" + "blur.png", 1));
		//System.out.println(f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("4.png", 1));
	//	t.makeKernelGuassian(1, 3);
		//Check thread = new Check();
		//Check t2 = new Check();
		//thread.start();
		//t2.start();
		
		/*
		//read image from dir
		BufferedImage image;
		File folder = new File("C:\\Users\\talpe\\Desktop\\img\\img\\1\\");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				if(listOfFiles[i]==null)
					continue ;
				String fileName = listOfFiles[i].getName();
				image = ImageIO.read(new File("C:\\Users\\talpe\\Desktop\\img\\img\\1\\" + fileName));
				Matlab.ExecuteMatlabCode(fileName);
				byte [][]matrixg = CUtils.BlackWhiteImageToBinaryArray("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\"+"MatlabRes"+fileName);
				TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
				if(!TriangleUtils.AreValidTriangleEdges(edges)){
					System.out.println("Triangle edges are not valid");
					//Delete matlab result
					CUtils.DeleteFileByPath("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\" + "MatlabRes" + fileName);
					continue;
				}
				if(!TriangleUtils.IsTriangleExist(matrixg, 0.4, 0.4, edges)){
					System.out.println("Triangle not found in image #" + fileName);
					CUtils.DeleteFileByPath("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\" + "MatlabRes"+fileName);
				}else{
					try{
						Files.move(Paths.get("C:\\Users\\talpe\\Desktop\\img\\img\\1\\" + fileName),Paths.get("C:\\Users\\talpe\\Desktop\\img\\img\\1\\goodImage\\" + fileName),REPLACE_EXISTING);
					}catch (IOException e) {
						System.err.println(e);
					}
					continue;
				}
				
			} 
		}*/
	
		//System.out.println("the edeg is:");
		
		Histogram hist = new Histogram(img);
		Histogram hist1 = new Histogram(img1);
		
		System.out.println("HSV Intersection "+hist.intersectionHSV(hist1));
		System.out.println("RGB Intersection "+hist.intersectionRGB(hist1));
		System.out.println("HSV Correlation "+hist.correlationHSV(hist1));
		System.out.println("RGB Correlation "+hist.correlationRGB(hist1));
		System.out.println("HSV chiSquare "+hist.chiSquareHSV(hist1));
		System.out.println("RGB chiSquare "+hist.chiSquareRGB(hist1));

		
		System.out.println("HSV BhattacharyyaDistanceHSV "+hist.BhattacharyyaDistanceHSV(hist1));
		System.out.println("HSV BhattacharyyaDistanceRGB "+hist.BhattacharyyaDistanceRGB(hist1));
		
		//BlockMatching blockMatching = new BlockMatching();
		//Point point = new Point(89,30);
		
		//System.out.println("the MAD is "+blockMatching.identifyTheRequirArea("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\","MatlabRes124.jpg","MatlabRes125.jpg","MAD",10,5,5));
		//System.out.println("the MES is "+blockMatching.identifyTheRequirArea("MES"));
		
		 
		
		 
	}
	
	

}
