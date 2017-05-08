package Class;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
		    img = ImageIO.read(new File("a.jpg"));
		} catch (IOException e) {
			System.err.println(e);
		}
		//loding an image 
		BufferedImage img1 = null;
		try {
		   img1 = ImageIO.read(new File("117.jpg"));
		} catch (IOException e) {
			System.err.println(e);
		}
		
/*
		ImproveFocus t = new ImproveFocus();
		Focus f =new Focus();
		File folder = new File("C:\\project\\images\\1\\goodImages\\");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				if(listOfFiles[i]==null)
					continue ;
				String fileName = listOfFiles[i].getName();
				System.out.println("start bluring");
		t.blur("C:\\project\\images\\1\\goodImages\\","C:\\project\\images\\1\\goodImages\\blurImage\\",fileName);
		System.out.println("end bluring and start improving");


		t.mask("C:\\project\\images\\1\\goodImages\\","C:\\project\\images\\1\\goodImages\\blurImage\\",fileName);
		t.improveFocus("C:\\project\\images\\1\\goodImages\\", "C:\\project\\images\\1\\goodImages\\maskImages\\", fileName);
		double fr = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\images\\1\\goodImages\\"+fileName);
		double se = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\images\\1\\goodImages\\sharpImages\\"+"sharp_"+fileName);
		System.out.println("the orignalImage is = " +fr +"the sharper Image is =" +se);
		//t.cMask("C:\\project\\images\\1\\goodImages\\","C:\\project\\images\\1\\goodImages\\blurImage\\",fileName);
			}
		}
		CUtils.CropAndSaveImage("matlab.png","matlab.png",78,28,400,400);
		//t.blur(C:\Users\talpe\git\\AnalysisaAndEnhancementImage\\AnalysisaAndEnhancementImage\\", "C:\\Users\\talpe\\Desktop\\kjh\\test", "4.png");
		System.out.println("matlab "+ f.FocusMeasuresBasedOnImageDifferentiationA("matlab.png"));*/
		//.....
		

		
		//AreasOfInterest tr = new AreasOfInterest(10);
		//byte [][]matrixg =CUtils.BlackWhiteImageToBinaryArray("MatlabResimg-01255.png");
	    //TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
	    //tr.Area6(edges, 75);
	    
	    //System.out.println(tr.leftEdge2.get(0).x+" "+tr.leftEdge2.get(0).y);
	    //System.out.println(tr.leftEdge2.get(1).x+" "+tr.leftEdge2.get(1).y);
	    //System.out.println(tr.leftEdge2.get(2).x+" "+tr.leftEdge2.get(2).y);
	  //  System.out.println(tr.leftEdge2.get(3).x+" "+tr.leftEdge2.get(3).y);
	    /*
	    int lx= tr.rightEdge2.get(0).x; 
	    int ly= tr.rightEdge2.get(0).y; 
	    int rx = tr.rightEdge2.get(3).x;
	    int ry = tr.rightEdge2.get(2).y;
	    int w = Math.abs(rx - lx);
	    int h = Math.abs(ry -ly);
	    BufferedImage image3,out;
	    image3 = ImageIO.read(new File("img-01255.png"));
		AffineTransform tx = new AffineTransform();
	    tx.rotate(0.2, image3.getWidth() / 2, image3.getHeight() / 2);
	    AffineTransformOp op = new AffineTransformOp(tx,
	    AffineTransformOp.TYPE_BILINEAR);
	    image3 = op.filter(image3, null);		
	    CUtils.SaveImage(image3, "C:\\Users\\omri\\Desktop\\img\\rotate.png");
	    out = image3.getSubimage(195,145, 200, 100);
	    CUtils.SaveImage(out, "C:\\Users\\omri\\Desktop\\img\\area3.png");
	    //.....
	    */
		
		
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
		
		Histogram hist = new Histogram(img);
		Histogram hist1 = new Histogram(img1);
		
		System.out.println("HSV Intersection "+hist.intersectionHSV(hist1));
		System.out.println("RGB Intersection "+hist.intersectionRGB(hist1));
		System.out.println("HSV Correlation "+hist.correlationHSV(hist1));
		System.out.println("RGB Correlation "+hist.correlationRGB(hist1));
		System.out.println("HSV chiSquare "+hist.chiSquareHSV(hist1));
		System.out.println("RGB chiSquare "+hist.chiSquareRGB(hist1));

		
		//System.out.println("HSV BhattacharyyaDistanceHSV "+hist.BhattacharyyaDistanceHSV(hist1));

		//System.out.println("HSV BhattacharyyaDistanceRGB "+hist.BhattacharyyaDistanceRGB(hist1));
		

		
		//BlockMatching blockMatching = new BlockMatching();
		//Point point = new Point(89,30);
		
		//System.out.println("the MAD is "+blockMatching.identifyTheRequirArea("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\","MatlabRes124.jpg","MatlabRes125.jpg","MAD",10,5,5));

		//System.out.println("the MES is "+blockMatching.identifyTheRequirArea("C:\\Users\\talpe\\Desktop\\img\\img\\1\\res\\","MatlabRes124.jpg","MatlabRes125.jpg","MES",10,5,5));

		
		 
		
		 
	}
	
	

}
