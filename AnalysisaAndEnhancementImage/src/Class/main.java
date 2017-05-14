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
		
		CUtils.SetImagesDestinationPath("C:\\project\\images\\101\\");
	//	Check thread = new Check();
		//thread.start();
		
		ImproveFocus t = new ImproveFocus();
		Focus f =new Focus();
		File folder = new File("C:\\project\\images\\101\\goodImages\\");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				if(listOfFiles[i]==null)
					continue ;
				String fileName = listOfFiles[i].getName();
				System.out.println("start bluring");
		t.blur("C:\\project\\images\\101\\goodImages\\","C:\\project\\images\\101\\goodImages\\blurImage\\",fileName);
		System.out.println("end bluring and start improving");


		t.mask("C:\\project\\images\\101\\goodImages\\","C:\\project\\images\\101\\goodImages\\blurImage\\",fileName);
		
		t.improveFocus("C:\\project\\images\\101\\goodImages\\", "C:\\project\\images\\101\\goodImages\\maskImages\\", fileName);
		t.laplacianMask("C:\\project\\images\\101\\goodImages\\sharpImages\\", "C:\\project\\images\\101\\goodImages\\laplacianImages\\", fileName, 1);
		t.improveFocusLaplacian("C:\\project\\images\\101\\goodImages\\", "C:\\project\\images\\101\\goodImages\\laplacianImages\\", fileName);
		double fr = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\images\\101\\goodImages\\"+fileName);
		double se = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\images\\101\\goodImages\\sharpImages\\"+"sharp_"+fileName);
		double th = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\images\\101\\goodImages\\sharpLaplacian\\"+"sharp_"+fileName);
		System.out.println("the orignalImage is = " +fr +"the sharper Image is =" +se+"laplacian image is ="+th);
		//t.cMask("C:\\project\\images\\1\\goodImages\\","C:\\project\\images\\1\\goodImages\\blurImage\\",fileName);
			}
		}
		//CUtils.CropAndSaveImage("matlab.png","matlab.png",78,28,400,400);
		//t.blur(C:\Users\talpe\git\\AnalysisaAndEnhancementImage\\AnalysisaAndEnhancementImage\\", "C:\\Users\\talpe\\Desktop\\kjh\\test", "4.png");
		//System.out.println("matlab "+ f.FocusMeasuresBasedOnImageDifferentiationA("matlab.png"));

		//System.out.println(f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\Users\\omri\\Desktop\\New folder\\" + "blur.png", 1));
		//System.out.println(f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("4.png", 1));

		//t.makeKernelGuassian(1, 3);

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
		
	//	Histogram hist = new Histogram(img);
		//Histogram hist1 = new Histogram(img1);
		
//		System.out.println("HSV Intersection "+hist.intersectionHSV(hist1));
	//	System.out.println("RGB Intersection "+hist.intersectionRGB(hist1));
	//	System.out.println("HSV Correlation "+hist.correlationHSV(hist1));
	//	System.out.println("RGB Correlation "+hist.correlationRGB(hist1));
	//	System.out.println("HSV chiSquare "+hist.chiSquareHSV(hist1));
	//	System.out.println("RGB chiSquare "+hist.chiSquareRGB(hist1));

		
		//System.out.println("HSV BhattacharyyaDistanceHSV "+hist.BhattacharyyaDistanceHSV(hist1));

		//System.out.println("HSV BhattacharyyaDistanceRGB "+hist.BhattacharyyaDistanceRGB(hist1));
		

		
		//BlockMatching blockMatching = new BlockMatching();
		//Point point = new Point(89,30);
		
		//System.out.println("the MAD is "+blockMatching.identifyTheRequirArea("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\","MatlabRes124.jpg","MatlabRes125.jpg","MAD",10,5,5));

		//System.out.println("the MES is "+blockMatching.identifyTheRequirArea("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\","MatlabRes124.jpg","MatlabRes125.jpg","MES",10,5,5));

		
		 
		
		 
	}
	
	

}
