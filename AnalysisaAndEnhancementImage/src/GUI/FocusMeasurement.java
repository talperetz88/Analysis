package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Class.AreasOfInterest;
import Class.CUtils;
import Class.Focus;
import Class.TriangleEdges;
import Class.TriangleUtils;

import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.JOptionPane;
public class FocusMeasurement extends JFrame {
	
	private JComboBox comboBox = null;
	private JPanel globalPanel = null, panel =null, localPanel = null;
	private JLabel  titleLbl,approchLbl;
	private openPage open;
	private JButton executeBtn = null;
	private JCheckBox chckbxArea_1,chckbxArea_2,chckbxArea_3,chckbxArea_4,chckbxAllAreas,chckbxArea_8,chckbxArea_7,chckbxArea_6,chckbxArea_5;
	private JCheckBox checkBox,checkBox_1,checkBox_2;
	private int approch;
	private JPanel methodsPanel;
	private JCheckBox LuminanceCheckBox;
	private JCheckBox grayScaleCheckBox;
	private JCheckBox normalCheckBox;
	private JPanel depthPanel;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxImagePower;
	private JSpinner spinner;
	private JLabel lblTreshold;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JPanel differentiationPanel;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JLabel lblNewLabel;
	private JSpinner spinner_3;
	private JButton btnNewButton;
	public FocusMeasurement(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);
		getContentPane().setLayout(null);
		
		titleLbl = new JLabel("Focus measurement");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(43, 13, 267, 44);
		getContentPane().add(titleLbl);
		
		approchLbl = new JLabel("Select approch:");
		approchLbl.setBounds(43, 75, 98, 16);
		getContentPane().add(approchLbl);
		
		
		getContentPane().add(getApproch());
		this.setVisible(true);
		//getContentPane().add(getLocal());
		
		panel = new JPanel();
		panel.setBounds(43, 240, 377, 233);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		JLabel areaslbl = new JLabel("Areas of intrest");
		areaslbl.setBounds(12, 13, 121, 16);
		panel.add(areaslbl);
		
		chckbxArea_1 = new JCheckBox("Area 1");
		chckbxArea_1.setBounds(12, 51, 113, 25);
		panel.add(chckbxArea_1);
		
		chckbxArea_2 = new JCheckBox("Area 2");
		chckbxArea_2.setBounds(12, 93, 113, 25);
		panel.add(chckbxArea_2);
		
		chckbxArea_3 = new JCheckBox("Area 3");
		chckbxArea_3.setBounds(12, 133, 113, 25);
		panel.add(chckbxArea_3);
		
		chckbxArea_4 = new JCheckBox("Area 4");
		chckbxArea_4.setBounds(12, 174, 113, 25);
		panel.add(chckbxArea_4);
		
		chckbxAllAreas = new JCheckBox("All areas");
		chckbxAllAreas.setBounds(96, 208, 113, 25);
		panel.add(chckbxAllAreas);
		
		chckbxArea_8 = new JCheckBox("Area 8");
		chckbxArea_8.setBounds(193, 174, 113, 25);
		panel.add(chckbxArea_8);
		
		chckbxArea_7 = new JCheckBox("Area 7");
		chckbxArea_7.setBounds(193, 133, 113, 25);
		panel.add(chckbxArea_7);
		
		chckbxArea_6 = new JCheckBox("Area 6");
		chckbxArea_6.setBounds(193, 93, 113, 25);
		panel.add(chckbxArea_6);
		
		chckbxArea_5 = new JCheckBox("Area 5");
		chckbxArea_5.setBounds(193, 51, 113, 25);
		panel.add(chckbxArea_5);
		
		//NextBtn = new JButton("Next");
		
		getContentPane().add(getExecute());
		
		globalPanel = new JPanel();
		globalPanel.setBounds(43, 104, 611, 158);
		getContentPane().add(globalPanel);
		globalPanel.setLayout(null);
		globalPanel.setVisible(false);
		
		checkBox = new JCheckBox("Image statistics");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//OptionPane.showMessageDialog(this, "thank you for using java");
				if(checkBox.isSelected())
					methodsPanel.setVisible(true);
				else
					methodsPanel.setVisible(false);
				if(checkBox_2.isSelected() && checkBox.isSelected() || checkBox_1.isSelected() && checkBox.isSelected())
					JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		checkBox.setBounds(0, 43, 154, 25);
		globalPanel.add(checkBox);
		
		JLabel label = new JLabel("Focus function mesure:");
		label.setBounds(0, 13, 154, 16);
		globalPanel.add(label);
		
		checkBox_1 = new JCheckBox("Depth of peaks and valleys");
		checkBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox_1.isSelected())
					depthPanel.setVisible(true);
				else
					depthPanel.setVisible(false);
				if(checkBox_2.isSelected() && checkBox_1.isSelected() || checkBox_1.isSelected() && checkBox.isSelected())
					JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
					
			}
		});
		checkBox_1.setBounds(179, 43, 202, 25);
		globalPanel.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("Image differentiation");
		checkBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(checkBox_2.isSelected())
					differentiationPanel.setVisible(true);
				else
					differentiationPanel.setVisible(false);
				if(checkBox_2.isSelected() && checkBox_1.isSelected() || checkBox_2.isSelected() && checkBox.isSelected())
					JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		checkBox_2.setBounds(399, 43, 166, 25);
		globalPanel.add(checkBox_2);
		
		methodsPanel = new JPanel();
		methodsPanel.setBounds(0, 77, 468, 61);
		globalPanel.add(methodsPanel);
		methodsPanel.setLayout(null);
		methodsPanel.setVisible(false);
		
		LuminanceCheckBox = new JCheckBox("Luminance");
		LuminanceCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(LuminanceCheckBox.isSelected())
					normalCheckBox.setVisible(true);
				if(!LuminanceCheckBox.isSelected() && !grayScaleCheckBox.isSelected())
					normalCheckBox.setVisible(false);
			}
		});
		LuminanceCheckBox.setBounds(8, 20, 113, 25);
		methodsPanel.add(LuminanceCheckBox);
		
		grayScaleCheckBox = new JCheckBox("GrayScale");
		grayScaleCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(grayScaleCheckBox.isSelected())
					normalCheckBox.setVisible(true);
				if(!grayScaleCheckBox.isSelected() && !LuminanceCheckBox.isSelected())
					normalCheckBox.setVisible(false);
			}
			
		});
		grayScaleCheckBox.setBounds(168, 20, 113, 25);
		methodsPanel.add(grayScaleCheckBox);
		
		normalCheckBox = new JCheckBox("Normalized");
		normalCheckBox.setBounds(308, 20, 113, 25);
		normalCheckBox.setVisible(false);
		methodsPanel.add(normalCheckBox);
		
		depthPanel = new JPanel();
		depthPanel.setBounds(0, 73, 601, 72);
		globalPanel.add(depthPanel);
		depthPanel.setLayout(null);
		depthPanel.setVisible(false);
		
		chckbxNewCheckBox = new JCheckBox("Image threshold content");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxNewCheckBox.isSelected()){
				lblTreshold.setVisible(true);
				spinner.setVisible(true);
				}
				else
				spinner.setVisible(false);
				if(!chckbxImagePower.isSelected() && !chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox.isSelected())
					lblTreshold.setVisible(false);
			}
		});
		chckbxNewCheckBox.setBounds(0, 0, 167, 25);
		depthPanel.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("pixel count");
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxNewCheckBox_1.isSelected()){
				lblTreshold.setVisible(true);
				spinner_1.setVisible(true);
				}
				else
					spinner_1.setVisible(false);
				if(!chckbxImagePower.isSelected() && !chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox.isSelected())
					lblTreshold.setVisible(false);
			}
		});
		chckbxNewCheckBox_1.setBounds(180, 0, 113, 25);
		depthPanel.add(chckbxNewCheckBox_1);
		
		chckbxImagePower = new JCheckBox("Image power");
		chckbxImagePower.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxImagePower.isSelected()){
				lblTreshold.setVisible(true);
				spinner_2.setVisible(true);
				}
				else
				spinner_2.setVisible(false);
				if(!chckbxImagePower.isSelected() && !chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox.isSelected())
					lblTreshold.setVisible(false);
			}
		});
		chckbxImagePower.setBounds(312, 0, 113, 25);
		depthPanel.add(chckbxImagePower);
		
		spinner = new JSpinner();
		spinner.setBounds(87, 37, 40, 22);
		depthPanel.add(spinner);
		spinner.setVisible(false);
		
		lblTreshold = new JLabel("Treshold");
		lblTreshold.setBounds(19, 40, 56, 16);
		depthPanel.add(lblTreshold);
		lblTreshold.setVisible(false);
		
		spinner_1 = new JSpinner();
		spinner_1.setBounds(180, 37, 40, 22);
		depthPanel.add(spinner_1);
		spinner_1.setVisible(false);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(312, 37, 40, 22);
		depthPanel.add(spinner_2);
		
		differentiationPanel = new JPanel();
		differentiationPanel.setBounds(0, 77, 468, 61);
		globalPanel.add(differentiationPanel);
		differentiationPanel.setLayout(null);
		differentiationPanel.setVisible(false);
		
		chckbxNewCheckBox_3 = new JCheckBox("differences");
		chckbxNewCheckBox_3.setBounds(0, 0, 113, 25);
		differentiationPanel.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_2 = new JCheckBox("squar differences");
		chckbxNewCheckBox_2.setBounds(195, 0, 168, 25);
		differentiationPanel.add(chckbxNewCheckBox_2);
		
		lblNewLabel = new JLabel("Treshold:");
		lblNewLabel.setBounds(23, 43, 56, 16);
		differentiationPanel.add(lblNewLabel);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(91, 40, 44, 20);
		differentiationPanel.add(spinner_3);
		
		btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
				ImproveFocusGUI next = new ImproveFocusGUI();
			}
		});
		btnNewButton.setBounds(581, 433, 111, 40);
		getContentPane().add(btnNewButton);
		btnNewButton.setVisible(true);
		spinner_2.setVisible(false);
		
	/*	JPanel Localpanel1 = new JPanel();
		panel.setBounds(32, 207, 444, 197);
		getContentPane().add(panel);
		panel.setLayout(null);*/
		
		
	}
	
	
	public JComboBox getApproch() {
		
		if(comboBox == null)
			comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString() == "Local approch"){
					panel.setVisible(true);
					globalPanel.setVisible(true);
					executeBtn.setVisible(true);
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\"))
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\"));
						approch = 1;
					
				}else if(comboBox.getSelectedItem().toString() == "GlobalApproch"){
					panel.setVisible(false);
					globalPanel.setVisible(true);
					executeBtn.setVisible(true);
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\"))
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\globalApproch\\"));
					approch = 2;
				}else{
					panel.setVisible(false);
					globalPanel.setVisible(false);
					executeBtn.setVisible(false);
				}
			}
		});
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {" ", "Local approch","GlobalApproch"}));
		comboBox.setBounds(153, 75, 120, 22);
		//comboBox.setVisible(true);
		return comboBox;
	}
	
	public JButton getExecute(){
		if(executeBtn == null)
			executeBtn = new JButton("Execute");
		executeBtn.setBounds(505, 261, 149, 44);
		getContentPane().add(executeBtn);
		executeBtn.setVisible(false);
		CUtils.SetImagesDestinationPath("C:\\project\\images\\1\\");//dont forget to delete
		executeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(approch == 1){
				BufferedImage image = null;
				String fileName;
				File folder = new File(CUtils.GetImagesDestPath() +"goodImages\\");
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length -1; i++){
					if (listOfFiles[i].isFile()){
						if(listOfFiles[i]==null)
							continue ;
						fileName = listOfFiles[i].getName();
						byte [][]matrixg =CUtils.BlackWhiteImageToBinaryArray(CUtils.GetImagesDestPath() +"matlabRes\\" +"MatlabRes" +fileName);
				        TriangleEdges edges = TriangleUtils.FindTriangleEdges(matrixg);
						AreasOfInterest tr = new AreasOfInterest();
						try {
							image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if(chckbxArea_1.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 1\\")){
							BufferedImage res = tr.Area1(image, 65, edges);
							CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 1\\" +"area_1"+fileName);
							
							System.out.println("test1");
							}
						}
						if(chckbxArea_2.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 2\\")){
								BufferedImage res = tr.Area2(image, 65, edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 2\\" +"area_1"+fileName);
								
							System.out.println("test2");
							}
						}
						if(chckbxArea_3.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 3\\")){
								BufferedImage res = tr.Area3(image, edges,10);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 3\\" +"area_1"+fileName);
								
							System.out.println("test3");
							}
						}
						if(chckbxArea_4.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 4\\")){
								BufferedImage res = tr.Area4(image, edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 4\\" +"area_1"+fileName);
								
							System.out.println("test4");
							}
						}
						if(chckbxArea_5.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 5\\")){
								BufferedImage res = tr.Area5(image, edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 5\\" +"area_1"+fileName);
								
							System.out.println("test5");
							}
						}
						if(chckbxArea_6.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 6\\")){
								BufferedImage res = tr.Area6(image, edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 6\\" +"area_1"+fileName);
								
							System.out.println("test6");
							}
						}
						if(chckbxArea_7.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 7\\")){
								BufferedImage res = tr.Area7(image,edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 7\\" +"area_1"+fileName);
								
							System.out.println("test7");
							}
						}
						if(chckbxArea_8.isSelected() || chckbxAllAreas.isSelected()){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "FocusMeasurement\\localApproch\\Area 8\\")){
								BufferedImage res = tr.Area8(image,edges);
								CUtils.SaveImage(res, CUtils.GetImagesDestPath()+"FocusMeasurement\\localApproch\\"+"Area 8\\" +"area_1"+fileName);
								
						
							System.out.println("test8");
							}
						}
					}
				}	
			}
			
			if(approch == 2){
				BufferedImage image = null;
				String fileName;
				File folder = new File(CUtils.GetImagesDestPath() +"goodImages\\");
				File[] listOfFiles = folder.listFiles();
				Focus func = new Focus();
				int index;
				for (int i = 0; i < listOfFiles.length -1; i++){
					if (listOfFiles[i].isFile()){
						if(listOfFiles[i]==null)
							continue ;
						fileName = listOfFiles[i].getName();
				
						if(checkBox.isSelected()){
							if(LuminanceCheckBox.isSelected()){
								if(normalCheckBox.isSelected()){
								func.FocusMeasuresBasedOnImageStatisticsNormalizedVariance(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, 2);	
								}else{
								//the second value of index is: 1 it means grayscale , and 2 it means Luminance
								func.FocusMeasuresBasedOnImageStatisticsVariance(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, 2);
								}
							}
							if(grayScaleCheckBox.isSelected()){
								if(normalCheckBox.isSelected()){
									func.FocusMeasuresBasedOnImageStatisticsNormalizedVariance(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, 1);	
									}else{
									//the second value of index is: 1 it means grayscale , and 2 it means Luminance
									func.FocusMeasuresBasedOnImageStatisticsVariance(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, 1);
									}
							}
							
						}
						if(checkBox_1.isSelected()){
							if(chckbxNewCheckBox.isSelected())
								func.FunctionBasedOnDepthOfPeaksAndValleysA(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, (int)spinner.getValue());
							if(chckbxNewCheckBox_1.isSelected())
								func.FunctionBasedOnDepthOfPeaksAndValleysB(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, (int)spinner_1.getValue());
							if(chckbxImagePower.isSelected())
								func.FunctionBasedOnDepthOfPeaksAndValleysC(CUtils.GetImagesDestPath() + "goodImages\\" + fileName, (int)spinner_2.getValue());
						}
						if(checkBox_2.isSelected()){
							if(chckbxNewCheckBox_2.isSelected())
								func.FocusMeasuresBasedOnImageDifferentiationB(CUtils.GetImagesDestPath() + "goodImages\\" + fileName);
							if(chckbxNewCheckBox_3.isSelected())
								func.FocusMeasuresBasedOnImageDifferentiationA(CUtils.GetImagesDestPath() + "goodImages\\" + fileName);
						}
					}
				}
			}
			}
		});
		return executeBtn;
	}
	
		public void closeFrame(){
 			super.dispose();
 		}
}
