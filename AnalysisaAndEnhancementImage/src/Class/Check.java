package Class;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Check extends Thread{
	
	
		public void run(){
			BufferedImage image;
			
			File folder = new File(CUtils.GetImagesDestPath());
			File[] listOfFiles = folder.listFiles();
	        final JFrame frame = new JFrame("JProgress");
	        
	        // creates progress bar
	        final JProgressBar pb = new JProgressBar();
	        pb.setMinimum(0);
	        pb.setMaximum(listOfFiles.length);
	        pb.setStringPainted(true);
	 
	        // add progress bar
	        frame.setLayout(new FlowLayout());
	        frame.getContentPane().add(pb);
	 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 200);
	        frame.setVisible(true);
			
			//read image from dir

			for (int i = 0; i < listOfFiles.length; i++){
				final  int currentValue = i;
				 try {
		                SwingUtilities.invokeLater(new Runnable() {
		                    public void run() {
		                        pb.setValue(currentValue);
		                    }
		                });
		                java.lang.Thread.sleep(100);
		            } catch (InterruptedException e) {
		                JOptionPane.showMessageDialog(frame, e.getMessage());
		            }
	
				if (listOfFiles[i].isFile()){
					if(listOfFiles[i]==null)
						continue ;
					String fileName = listOfFiles[i].getName();
					try {
						image = ImageIO.read(new File(CUtils.GetImagesDestPath() + fileName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(CUtils.CropAndSaveImage(CUtils.GetImagesDestPath()+ fileName, CUtils.GetImagesDestPath()+ fileName, 0, 0, 400, 400)){
					Matlab.ExecuteMatlabCode(fileName);
					
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath()+"matlabRes\\")){
						System.out.println("Directory is OK");
					}
					
					byte [][]matrixg = CUtils.BlackWhiteImageToBinaryArray(CUtils.GetImagesDestPath()+"matlabRes\\"+"MatlabRes"+fileName);
					if(matrixg == null)
						continue;
					TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
					if(!TriangleUtils.AreValidTriangleEdges(edges)){
						System.out.println("Triangle edges are not valid");
						//Delete matlab result
						CUtils.DeleteFileByPath(CUtils.GetImagesDestPath()+"matlabRes\\" + "MatlabRes" + fileName);
						CUtils.DeleteFileByPath(CUtils.GetImagesDestPath() +fileName);
						continue;
					}
					if(!TriangleUtils.IsTriangleExist(matrixg, 0.5, 0.5, edges)){
						System.out.println("Triangle not found in image #" + fileName);
						CUtils.DeleteFileByPath(CUtils.GetImagesDestPath()+"matlabRes\\" + "MatlabRes"+fileName);
						CUtils.DeleteFileByPath(CUtils.GetImagesDestPath() +fileName);
					}else{
						if(CUtils.CreateDirectory(CUtils.GetImagesDestPath()+"goodImages\\"))
						try{
							Files.move(Paths.get(CUtils.GetImagesDestPath() + fileName),Paths.get(CUtils.GetImagesDestPath()+"goodImages\\" + fileName),REPLACE_EXISTING);
						}catch (IOException e) {
							System.err.println(e);
						}
						continue;
					}
					
				} 
			  }
			 }
			pb.setValue(100);
			}
			

}
