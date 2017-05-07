package Class;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImproveFocus {

	public void mask(String pathOriginal,String pathBlur,String fileName){
		BufferedImage orignalImage = null, blurImage = null,maskimg = null,sharpImg = null;
		try{
		orignalImage =  ImageIO.read(new File(pathOriginal + fileName));
		blurImage =  ImageIO.read(new File(pathBlur +"blur_"+fileName));
				
		}catch (IOException e){
			e.printStackTrace();
		}
		int [][] diff = new int[orignalImage.getWidth()][orignalImage.getHeight()];
		for(int i = 0 ; i < orignalImage.getWidth(); i ++)
			for(int j = 0; j < orignalImage.getHeight(); j++){
				int argb0 = orignalImage.getRGB(i, j);
				int argb1 = blurImage.getRGB(i,j );
				int a0 = (argb0 >> 24) & 0xFF;
				int r0 = (argb0 >> 16) & 0xFF;
				int g0 = (argb0 >>  8) & 0xFF;
				int b0 = (argb0      ) & 0xFF;

				int a1 = (argb1 >> 24) & 0xFF;
				int r1 = (argb1 >> 16) & 0xFF;
				int g1 = (argb1 >>  8) & 0xFF;
				int b1 = (argb1      ) & 0xFF;

				int aDiff = Math.abs(a1 - a0);
				int rDiff = Math.abs(r1 - r0);
				int gDiff = Math.abs(g1 - g0);
				int bDiff = Math.abs(b1 - b0);
				
				int A = (aDiff < 0 )?0:aDiff;
				int R = (rDiff < 0 )?0:rDiff;
				int G = (gDiff < 0)?0:gDiff;
				int B = (bDiff < 0)?0:bDiff;
				diff[i][j] = (A << 24) | (R << 16) | (G << 8) | B;
				
			}
		maskimg = CUtils.ArrayToColorImage(diff);
		CUtils.SaveImage(maskimg, pathOriginal+ "maskImages\\" +"mask_"+fileName);
				
	}

	public void improveFocus(String pathOriginal,String pathMask,String fileName){
		BufferedImage orignalImage = null, blurImage = null,maskimg = null,sharpImg = null;
		try{
			orignalImage =  ImageIO.read(new File(pathOriginal + fileName));
			maskimg =  ImageIO.read(new File(pathMask +"mask_"+fileName));
					
			}catch (IOException e){
				e.printStackTrace();
			}
		int [][] diff = new int[orignalImage.getWidth()][orignalImage.getHeight()];
		for(int i = 0 ; i < orignalImage.getWidth(); i ++)
			for(int j = 0; j < orignalImage.getHeight(); j++){
				int argb0 = orignalImage.getRGB(i, j);
				int argb1 = maskimg.getRGB(i,j );
				int a0 = (argb0 >> 24) & 0xFF;
				int r0 = (argb0 >> 16) & 0xFF;
				int g0 = (argb0 >>  8) & 0xFF;
				int b0 = (argb0      ) & 0xFF;

				int a1 = (argb1 >> 24) & 0xFF;
				int r1 = (argb1 >> 16) & 0xFF;
				int g1 = (argb1 >>  8) & 0xFF;
				int b1 = (argb1      ) & 0xFF;

				int aDiff = Math.abs(a1 + a0);
				int rDiff = Math.abs(r1 + r0);
				int gDiff = Math.abs(g1 + g0);
				int bDiff = Math.abs(b1 + b0);
				
				int A = (aDiff > 255 )?255:aDiff;
				int R = (rDiff > 255 )?255:rDiff;
				int G = (gDiff > 255)?255:gDiff;
				int B = (bDiff > 255)?255:bDiff;
				diff[i][j] = (A << 24) | (R << 16) | (G << 8) | B;
				
			}
		sharpImg = CUtils.ArrayToColorImage(diff);
		CUtils.SaveImage(sharpImg, pathOriginal +"sharpImages\\" +"sharp_"+fileName);
	}
	public void cMask(String pathOriginal,String pathBlur,String fileName){
		BufferedImage orignalImage = null, blurImage = null,maskimg = null,sharpImg = null;
		try{
		orignalImage =  ImageIO.read(new File(pathOriginal + fileName));
		blurImage =  ImageIO.read(new File(pathBlur +"blur_"+fileName));
		}catch (IOException e){
			e.printStackTrace();
		}
		
		int[][] blurMat =  new int[400][400];
		int[][] originalMat = new int[400][400];
		int [][] mask = new int[originalMat.length][originalMat.length];
		int [][] sharpImage = new int[originalMat.length][originalMat.length];
		int treshold = 10 ;
		for(int i = 0 ; i < originalMat.length; i ++)
			for(int j =	0 ; j < originalMat.length; j ++){
				int pixel= orignalImage.getRGB(i, j) - blurImage.getRGB(i, j);
			//	if(pixel < treshold)
				//	mask[i][j]=0;
			//	else
					mask[i][j] = pixel;
			}
		maskimg = CUtils.ArrayToColorImage(mask);
		CUtils.SaveImage(maskimg, pathOriginal+ "maskImages\\" +"mask_"+fileName);
		for(int i = 0 ; i < originalMat.length; i ++)
			for(int j =	0 ; j < originalMat.length; j ++){
			sharpImage[i][j] = orignalImage.getRGB(i, j) + maskimg.getRGB(i, j);
			}
		sharpImg = CUtils.ArrayToColorImage(sharpImage);
		CUtils.SaveImage(sharpImg, pathOriginal+ "sharpImages\\" +"sharp_"+fileName);
	}
	public void creatMask(String pathOriginal,String pathBlur,String fileName){
		BufferedImage image = null, image1 = null,maskimg = null;
		try{
		image =  ImageIO.read(new File("4.png"));
		image1 =  ImageIO.read(new File("C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"blur.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
		int[][] blurMat =  new int[400][400];
		/*try {
			blurMat = CUtils.ConvertTo2DWithoutUsingGetRGB("C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"blur.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		int[][] originalMat = new int[400][400];
		/*try {
			originalMat = CUtils.ConvertTo2DWithoutUsingGetRGB("4.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int [][] mask = new int[originalMat.length][originalMat.length];
		int [][] sharpImage = new int[originalMat.length][originalMat.length];
		
		for(int i = 0 ; i < originalMat.length; i ++)
			for(int j =	0 ; j < originalMat.length; j ++){
				/*int blue = blurMat[i][j] & 0xff;
				int green = (blurMat[i][j] & 0xff00) >> 8;
				int red = (blurMat[i][j] & 0xff0000) >> 16;
				int blue1 = originalMat[i][j] & 0xff;
				int green1 = (originalMat[i][j] & 0xff00) >> 8;
				int red1 = (originalMat[i][j] & 0xff0000) >> 16;*/
				int blue = image1.getRGB(i, j) & 0xff;
				int green = (image1.getRGB(i, j) & 0xff00) >> 8;
				int red = (image1.getRGB(i, j) & 0xff0000) >> 16;
				int blue1 = image.getRGB(i, j) & 0xff;
				int green1 = (image.getRGB(i, j) & 0xff00) >> 8;
				int red1 = (image.getRGB(i, j) & 0xff0000) >> 16;
				int R = red1-red;
				int G = green1-green;
				int B = blue1-blue;
			    R = (R << 16) & 0x00FF0000;
			    G = (G << 8) & 0x0000FF00;
			    B = B & 0x000000FF;
				mask[i][j] = 0xFF000000 | R | G | B;
			}
		maskimg = CUtils.ArrayToColorImage(mask);
		CUtils.SaveImage(maskimg, "C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"mask.png");
		try {
			maskimg =  ImageIO.read(new File("C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"mask.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ; i < originalMat.length; i ++)
			for(int j =0 ; j < originalMat.length; j ++){
				/*int blue = mask[i][j] & 0xff;
				int green = (mask[i][j] & 0xff00) >> 8;
				int red = (mask[i][j] & 0xff0000) >> 16;
				int blue1 = originalMat[i][j] & 0xff;
				int green1 = (originalMat[i][j] & 0xff00) >> 8;
				int red1 = (originalMat[i][j] & 0xff0000) >> 16;*/
				int blue = maskimg.getRGB(i, j) & 0xff;
				int green = (maskimg.getRGB(i, j) & 0xff00) >> 8;
				int red = (maskimg.getRGB(i, j) & 0xff0000) >> 16;
			    int alpha = ((maskimg.getRGB(i,j)) & 0xff000000) >>> 24;
				int blue1 = image.getRGB(i, j) & 0xff;
				int green1 = (image.getRGB(i, j) & 0xff00) >> 8;
				int red1 = (image.getRGB(i, j) & 0xff0000) >> 16;
				int alpha1 = (image.getRGB(i,j) & 0xff000000) >>> 24;
				int R = red1+red;
				if(R > 255)
					R =255;
				int G = green1+green;
				if(G > 255)
					G =255;
				int B = blue1+blue;
				if(B > 255)
					B =255;
				int A = alpha1 + alpha;
				A = (A << 24 ) & 0xff;
			    R = (R << 16) & 0x00FF0000;
			    G = (G << 8) & 0x0000FF00;
			    B = B & 0x000000FF;
				//sharpImage[i][j] = 0xFF000000 | R | G | B; 
			    sharpImage[i][j] = alpha|R|G|B;
			}
		
		image = CUtils.ArrayToColorImage(sharpImage);
		CUtils.SaveImage(image, "C:\\Users\\talpe\\Desktop\\kjh\\images\\" +"sharp.png");
		
	}

	
	public void blur(String pathOriginal,String pathBlur,String fileName){
		BufferedImage orignalImage = null;
		 int radius = 1;
		    int size = radius * 2 + 1;
		   // float [] data = new float[] {0,-1,0,-1,4,-1,0,-1,0};
		    float [] sigma2_3X3 = new float[] {0.102059f,0.115349f,0.102059f,
		    		0.115349f,0.130371f,0.115349f,
		    		0.102059f,0.115349f,0.102059f};
		    float [] sigma1_3X3 = new float[]{0.077847f,0.123317f,0.077847f,
		    		0.123317f,0.195346f,0.123317f,
		    		0.077847f,0.123317f,0.077847f};
		    float [] sigma2_5X5 = new float[]{0.023528f,0.033969f,0.038393f,0.033969f,0.023528f,
		    		0.033969f,0.049045f,0.055432f,0.049045f,0.033969f,
		    		0.038393f,0.055432f,0.062651f,0.055432f,0.038393f,
		    		0.033969f,0.049045f,0.055432f,0.049045f,0.033969f,
		    		0.023528f,0.033969f,0.038393f,0.033969f,0.023528f};
		    float[] sigma1_5X5 = new float[]{0.003765f,0.015019f,0.023792f,0.015019f,0.003765f,
		    		0.015019f,0.059912f,0.094907f,0.059912f,0.015019f,
		    		0.023792f,0.094907f,0.150342f,0.094907f,0.023792f,
		    		0.015019f,0.059912f,0.094907f,0.059912f,0.015019f,
		    		0.003765f,0.015019f,0.023792f,0.015019f,0.003765f};
		    
		    //  float weight =  (1.0f / (size * size));
		    try {
				orignalImage = ImageIO.read(new File(pathOriginal+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    //for(int i = 0 ; i < 6 ; i ++)
		 	  //  data[i] = weight;
			Kernel kernel = new Kernel(5, 5, sigma2_5X5);
			BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			BufferedImage blurred = op.filter(orignalImage, null);
			CUtils.SaveImage(blurred, pathBlur+"blur_"+fileName);
			//CUtils.SaveImage(blurred,"C:\\Users\\omri\\Desktop\\New folder\\" + "blur.png");
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
				
				exp = (float) Math.exp(-1*(Math.pow(i, 2)+Math.pow(j,2))/(2*Math.pow(sigma,2)));
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


