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
			File folder = new File("C:\\project\\images\\10\\");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++){
				if (listOfFiles[i].isFile()){
					if(listOfFiles[i]==null)
						continue ;
					String fileName = listOfFiles[i].getName();
					try {
						image = ImageIO.read(new File("C:\\project\\images\\10\\" + fileName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(CUtils.CropAndSaveImage("C:\\project\\images\\10\\"+ fileName, "C:\\project\\images\\10\\"+ fileName, 0, 0, 400, 400)){
					Matlab.ExecuteMatlabCode(fileName);
			
					byte [][]matrixg = CUtils.BlackWhiteImageToBinaryArray("C:\\project\\images\\10\\matlabRes\\"+"MatlabRes"+fileName);
					if(matrixg == null)
						continue;
					TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
					if(!TriangleUtils.AreValidTriangleEdges(edges)){
						System.out.println("Triangle edges are not valid");
						//Delete matlab result
						CUtils.DeleteFileByPath("C:\\project\\images\\10\\matlabRes\\" + "MatlabRes" + fileName);
						CUtils.DeleteFileByPath("C:\\project\\images\\10\\" +fileName);
						continue;
					}
					if(!TriangleUtils.IsTriangleExist(matrixg, 0.65, 0.65, edges)){
						System.out.println("Triangle not found in image #" + fileName);
						CUtils.DeleteFileByPath("C:\\project\\images\\10\\matlabRes\\" + "MatlabRes"+fileName);
						CUtils.DeleteFileByPath("C:\\project\\images\\10\\" +fileName);
					}else{
						try{
							Files.move(Paths.get("C:\\project\\images\\10\\" + fileName),Paths.get("C:\\project\\images\\10\\goodImages\\" + fileName),REPLACE_EXISTING);
						}catch (IOException e) {
							System.err.println(e);
						}
						continue;
					}
					
				} 
			  }
			 }
			}
			

}
