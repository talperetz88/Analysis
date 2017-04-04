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
public class openPage extends JFrame{
		public static Object openPage;
		private JButton openButten = null;
		private JButton btnSave = null;
		private JTextField dirictTextFilde = null;
		private JTextField textField = null;
		private JSpinner spinner = null;
		private JLabel label = null;
		private JButton nextButten = null;
		private JLabel lblName = null;
		private File file,file1;
		private openPage open;
		private String saveUrl;
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
		}

		public JButton getOpenButn() {
			if(openButten == null){
			openButten = new JButton("Open");
			openButten.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Files","avi","mp4","mkv");
		            chooser.setCurrentDirectory(new java.io.File("."));
		            chooser.setDialogTitle("Browse the folder to process");
		            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		            chooser.setFileFilter(filter);
		            chooser.setAcceptAllFileFilterUsed(true);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						file = chooser.getSelectedFile();
						dirictTextFilde.setText(chooser.getCurrentDirectory().toString());
		                System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
		                System.out.println("getSelectedFile() : "+ file);
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
 					c.setCurrentDirectory(new java.io.File("."));
 					c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 				      if (c.showSaveDialog(openPage.this) == JFileChooser.APPROVE_OPTION) {
 				    	 file1 = c.getCurrentDirectory();
 				    	 saveUrl = c.getCurrentDirectory().toString();
 				    	 textField.setText(c.getCurrentDirectory().toString());
 				    	 System.out.println("na na" + c.getCurrentDirectory());
 				      }else{
 				       // filename.setText("You pressed cancel");
 				     //   dir.setText("");
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
			spinner.setModel(new SpinnerNumberModel(0.5, 0.5, 2.0, 0.1));
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
	 					closeFrame();
	 					classifyImages next = new classifyImages(open);
	 					
	 				}
	 			});
				nextButten.setBounds(399, 368, 116, 33);
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
 		
