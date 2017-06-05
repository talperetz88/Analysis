package GUI;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import Class.CUtils;
import Class.Check;
public class openPage extends JFrame{
		public static Object openPage;
		private JButton openButten = null;
		private JButton btnSave = null;
		private JTextField dirictTextFilde = null;
		private JTextField textField = null;
		private JSpinner spinner = null,spinner_1 = null,spinner_2 = null;
		private JLabel label = null;
		private JButton nextButten = null;
		private JLabel lblName = null;
		private File file,file1;
		private openPage open;
		private String saveUrl,openUrl;
		private Process start;
		public openPage() {
			this.open = this;
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBounds(100,100,722,533);
			getContentPane().setLayout(null);
			getContentPane().add(getOpenButn());
			getContentPane().add(getOpenDirict());
			getContentPane().add(getSaveButn());
			getContentPane().add(getSaveDirict());
			getContentPane().add(getSpinner());
			getContentPane().add(getnextBtn());
			getContentPane().add(getFramesLabel());
			getContentPane().add(getCutLable());
			this.setVisible(true);
			
			JLabel lblWelcom = new JLabel("Welcome");
			lblWelcom.setFont(new Font("Arial", Font.BOLD, 20));
			lblWelcom.setBounds(35, 13, 273, 58);
			getContentPane().add(lblWelcom);
			
			JLabel lblTriangleClean = new JLabel("Triangle clean");
			lblTriangleClean.setFont(new Font("Arial", Font.BOLD, 16));
			lblTriangleClean.setBounds(24, 316, 173, 16);
			getContentPane().add(lblTriangleClean);
			
			JLabel lblPhase = new JLabel("phase 1:");
			lblPhase.setBounds(24, 353, 56, 16);
			getContentPane().add(lblPhase);
			
			JLabel lblPhase_1 = new JLabel("Phase 2:");
			lblPhase_1.setBounds(24, 392, 56, 16);
			getContentPane().add(lblPhase_1);
			
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(new Float(0.01), new Float(0.01), new Float(1), new Float(0.01)));
			spinner_1.setBounds(92, 350, 62, 22);
			getContentPane().add(spinner_1);
			
			spinner_2 = new JSpinner();
			spinner_2.setModel(new SpinnerNumberModel(new Float(0.01), new Float(0.01), new Float(1), new Float(0.01)));
			spinner_2.setBounds(92, 389, 62, 22);
			getContentPane().add(spinner_2);
		}

		public JButton getOpenButn() {
			if(openButten == null){
			openButten = new JButton("Open");
			openButten.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Files","avi","mp4","mkv");
		            chooser.setCurrentDirectory(new java.io.File("C:\\Project\\"));
		            chooser.setDialogTitle("Select the video file");
		            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		            chooser.setFileFilter(filter);
		            chooser.setAcceptAllFileFilterUsed(true);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						file = chooser.getSelectedFile();
						openUrl = chooser.getSelectedFile().getAbsolutePath();
						dirictTextFilde.setText(openUrl);
		                System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
		                System.out.println("getSelectedFile() : "+ file);
		                CUtils.SetImagesSourcePath(openUrl);;
		            } else {
		                System.out.println("No Selection ");
		            }
				}
			});
			openButten.setBounds(385, 99, 130, 33);
			}
			return openButten;
		}
	
 		public JButton getSaveButn(){
 			if(btnSave == null){
 			btnSave = new JButton("Save");
 			btnSave.addActionListener(new ActionListener() {
 				public void actionPerformed(ActionEvent arg0) {
 					JFileChooser c = new JFileChooser();
		            c.setCurrentDirectory(new java.io.File("C:\\Project\\"));
		            c.setDialogTitle("Select the working folder");
 					c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 					c.setAcceptAllFileFilterUsed(false);
 				      if (c.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
 				    	 file1 = c.getSelectedFile();
 				    	 saveUrl = c.getSelectedFile().getAbsolutePath();
 				    	 textField.setText(saveUrl);
 				    	System.out.println("getCurrentDirectory(): " + c.getCurrentDirectory());
 				    	System.out.println("getSelectedFile() : " + c.getSelectedFile());
 				    	CUtils.SetImagesDestinationPath(saveUrl);
 				      }else{
 				    	 System.out.println("No Selection ");;
 				      }
 				}
 			});
			btnSave.setBounds(385, 167, 130, 33);
 			}
			return btnSave;
 		}
 		public JSpinner getSpinner(){
 			if(spinner == null){
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
			spinner.setBounds(153, 270, 99, 19);
 			}
			return spinner;
 		}
 		public JTextField getSaveDirict(){
 			if(textField == null){
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(24, 170, 329, 27);
 			}
 			return textField;
 		}
 		public JButton getnextBtn(){
 			if(nextButten == null){
	 			nextButten = new JButton("Next");
	 			nextButten.addActionListener(new ActionListener() {
	 				public void actionPerformed(ActionEvent arg0) {
	 					if(textField.getText().isEmpty()){
	 						JOptionPane.showMessageDialog(null, "Plese choose workspace folder", "Warning",JOptionPane.WARNING_MESSAGE);
	 					}else{
	 					double frameRatio = Double.parseDouble(spinner.getValue().toString());
	 					ProcessBuilder pb = new ProcessBuilder(CUtils.GetVlcPath(), dirictTextFilde.getText(), "--video-filter=scene", "--scene-ratio="+frameRatio, "--scene-prefix=img-", "--scene-path="+CUtils.GetImagesDestPath(), "vlc://quit");
	 					try {
	 						
							 start = pb.start();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	 					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "goodImages\\"))
							if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "matlabRes\\"))
								System.out.println("eror");
	 					
	 					closeFrame();
	 					while(start.isAlive());
	 					Check thread = new Check((float)spinner_1.getValue(),(float)spinner_2.getValue());					
	 					thread.start();
	 					//while(thread.isAlive());
	 					classifyImages next = new classifyImages();
	 					}
	 					
	 				}
	 			});
				nextButten.setBounds(581, 433, 111, 40);
	 		}
 			return nextButten;
 		}
 		public JLabel getFramesLabel(){
 			if(lblName== null){
 				lblName = new JLabel("Frame per second:");
 				lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
 				lblName.setBounds(25, 273, 129, 16);
 			}
 			return lblName;
 		}
  		public JTextField getOpenDirict(){
 		if(dirictTextFilde == null){
		dirictTextFilde = new JTextField();
		dirictTextFilde.setBounds(24, 102, 329, 27);
		dirictTextFilde.setColumns(10);
 		}
		return dirictTextFilde;
 		}
 		public JLabel getCutLable(){
 		if (label == null){
			label = new JLabel("Cut video into frames");
			label.setFont(new Font("Arial", Font.BOLD, 16));
			label.setBounds(22, 246, 175, 21);
 		}
 		return label;
 		}
 		public void closeFrame(){
 			super.dispose();
 		}
 		public String getSaveUrl(){
 			return saveUrl;
 		}
}
 		
