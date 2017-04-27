package Class;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Check extends Thread{
	
	
		public void run(){
			//read image from dir
			BufferedImage image;
			File folder = new File("C:\\Users\\talpe\\Desktop\\img\\img\\2\\");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++){
				if (listOfFiles[i].isFile()){
					if(listOfFiles[i]==null)
						continue ;
					String fileName = listOfFiles[i].getName();
					try {
						image = ImageIO.read(new File("C:\\Users\\talpe\\Desktop\\img\\img\\2\\" + fileName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					Matlab.ExecuteMatlabCode(fileName);
			
					byte [][]matrixg = CUtils.BlackWhiteImageToBinaryArray("C:\\Users\\talpe\\Desktop\\img\\img\\2\\res\\"+"MatlabRes"+fileName);
					if(matrixg == null)
						continue;
					TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
					if(!TriangleUtils.AreValidTriangleEdges(edges)){
						System.out.println("Triangle edges are not valid");
						//Delete matlab result
						CUtils.DeleteFileByPath("C:\\Users\\talpe\\Desktop\\img\\img\\2\\res\\" + "MatlabRes" + fileName);
						continue;
					}
					if(!TriangleUtils.IsTriangleExist(matrixg, 0.4, 0.4, edges)){
						System.out.println("Triangle not found in image #" + fileName);
						CUtils.DeleteFileByPath("C:\\Users\\talpe\\Desktop\\img\\img\\2\\res\\" + "MatlabRes"+fileName);
					}else{
						try{
							Files.move(Paths.get("C:\\Users\\talpe\\Desktop\\img\\img\\2\\" + fileName),Paths.get("C:\\Users\\talpe\\Desktop\\img\\img\\2\\goodImage\\" + fileName),REPLACE_EXISTING);
						}catch (IOException e) {
							System.err.println(e);
						}
						continue;
					}
					
				} 
			 }
			}
			

}
