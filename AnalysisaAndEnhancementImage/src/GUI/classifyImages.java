package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
import Class.BlockMatching;
import Class.Histogram;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
public class classifyImages extends JFrame{
	
	private JComboBox comboBox = null;
	private JPanel panel = null, panel1 =null;
	private JLabel lblDistanceFunction,lblClassifyMethods,lblChoose,lblHistogramMethods,lblBlockMethods;
	public JCheckBox MADCheckBox,MSECheckBox;
	private JButton classifyImageNextBtn = null;
	private openPage open;
	private JSpinner PSpinner,qSpinner;
	private JLabel lblPixel_1;
	private JLabel pSizeLbl,qPixelLabel;
	private JLabel qSizeLbl;
	private boolean MAD = false, MES = false, COR = false, CHI = false, BHATT = false, INTER = false;
	private JSpinner heightSpinner;
	private JLabel heightLbl;
	public classifyImages(openPage open){
		this.open = open;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);		
		getContentPane().setLayout(null);
		getContentPane().add(getMethods());
		this.setVisible(true);
		getContentPane().add(getPanelHistogram());
		panel.setVisible(false);
		getContentPane().add(getPanelBlock());
		panel1.setVisible(false);
		getContentPane().add(getNextbtn());
		
		lblHistogramMethods = new JLabel("Histogram method");
		lblHistogramMethods.setBounds(45, 149, 165, 16);
		getContentPane().add(lblHistogramMethods);
		lblHistogramMethods.setVisible(false);
		
		
		lblBlockMethods = new JLabel("Block matching method");
		lblBlockMethods.setBounds(45, 149, 179, 16);
		getContentPane().add(lblBlockMethods);
		lblBlockMethods.setVisible(false);
		

		
		
		lblClassifyMethods = new JLabel("Split to similarity groups");
		lblClassifyMethods.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClassifyMethods.setBounds(31, 13, 310, 57);
		getContentPane().add(lblClassifyMethods);
		//lblClassifyMethods.setVisible(false);
		
		lblDistanceFunction = new JLabel("Distance function");
		lblDistanceFunction.setBounds(45, 178, 165, 16);
		getContentPane().add(lblDistanceFunction);
		lblDistanceFunction.setVisible(false);
		
		lblChoose = new JLabel("Choose the methods you want to use");
		lblChoose.setBounds(45, 83, 227, 21);
		getContentPane().add(lblChoose);
		

		

	}
	
	
	public JComboBox getMethods(){
		if(comboBox == null){
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString() == "RGB histogram" || comboBox.getSelectedItem().toString() == "Grayscale histogram"){
					panel.setVisible(true);
					lblHistogramMethods.setVisible(true);
					lblDistanceFunction.setVisible(true);
					lblBlockMethods.setVisible(false);
				}
				else if(comboBox.getSelectedItem().toString() == "Block matching"){
					panel.setVisible(false);
					panel1.setVisible(true);
					lblDistanceFunction.setVisible(true);
					lblBlockMethods.setVisible(true);
					lblHistogramMethods.setVisible(false);
				}else {
					panel.setVisible(false);
					panel1.setVisible(false);
					lblHistogramMethods.setVisible(false);
					lblDistanceFunction.setVisible(false);
					lblBlockMethods.setVisible(false);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "RGB histogram", "Grayscale histogram", "Block matching"}));
		comboBox.setBounds(45, 105, 214, 31);
		}
	return comboBox;
	}
	public JPanel getPanelHistogram(){
		if(panel == null){
			panel = new JPanel();
			panel.setVisible(false);
			panel.setBounds(45, 201, 368, 115);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JCheckBox correlationCheckBox = new JCheckBox("Correlation");
			correlationCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COR = true;
					
				}
			});
			
			correlationCheckBox.setBounds(8, 22, 113, 25);
			panel.add(correlationCheckBox);
			
			JCheckBox chiSquareCheckBox = new JCheckBox("Chi-Square");
			chiSquareCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CHI = true;
				}
			});

			chiSquareCheckBox.setBounds(8, 70, 113, 25);
			panel.add(chiSquareCheckBox);
			
			JCheckBox intersectionCheckBox = new JCheckBox("Intersection");
			intersectionCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					INTER = true;
				}
			});
			intersectionCheckBox.setBounds(155, 22, 113, 25);
			panel.add(intersectionCheckBox);
			
			JCheckBox bhattCheckBox = new JCheckBox("Bhattacharyya distance");
			bhattCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BHATT = true;
				}
			});
			bhattCheckBox.setBounds(155, 70, 168, 25);
			panel.add(bhattCheckBox);
		

		}
		return panel;
	}

	public JPanel getPanelBlock(){
		if(panel1 == null){
		panel1 = new JPanel();
		panel1.setBounds(45, 201, 480, 272);
		
		panel1.setLayout(null);
		
		MADCheckBox = new JCheckBox("MAD");
		MADCheckBox.setBounds(8, 22, 113, 25);
		MADCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAD = true;
			}
		});
		panel1.add(MADCheckBox);
		
		MSECheckBox = new JCheckBox("MSE");
		MSECheckBox.setBounds(8, 70, 113, 25);
		MSECheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MES = true;
			}
		});
		panel1.add(MSECheckBox);
		
		PSpinner = new JSpinner();
		PSpinner.setBounds(12, 150, 48, 22);
		panel1.add(PSpinner);
		
		lblPixel_1 = new JLabel("Pixel");
		lblPixel_1.setBounds(65, 153, 56, 16);
		panel1.add(lblPixel_1);
		
		pSizeLbl = new JLabel("Chose the size of paramte P:");
		pSizeLbl.setBounds(8, 121, 205, 19);
		panel1.add(pSizeLbl);
		
		qSpinner = new JSpinner();
		qSpinner.setBounds(12, 223, 48, 25);
		panel1.add(qSpinner);
		
		qPixelLabel = new JLabel("Pixel");
		qPixelLabel.setBounds(65, 227, 56, 16);
		panel1.add(qPixelLabel);
		
		qSizeLbl = new JLabel("Chose the size of paramte q:");
		qSizeLbl.setBounds(8, 191, 205, 19);
		panel1.add(qSizeLbl);
		
		JLabel tresholdLbl = new JLabel("Chose how similer the triangle:");
		tresholdLbl.setBounds(229, 122, 197, 16);
		panel1.add(tresholdLbl);
		
		heightSpinner = new JSpinner();
		heightSpinner.setBounds(229, 153, 48, 22);
		panel1.add(heightSpinner);
		
		heightLbl = new JLabel("Height diffrents");
		heightLbl.setBounds(289, 156, 101, 16);
		panel1.add(heightLbl);
		}
		return panel1;
	}
	public JButton getNextbtn(){
		if(classifyImageNextBtn == null){
		classifyImageNextBtn = new JButton("Next");
		classifyImageNextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				checkBox();
				try {
					ImageList next1 = new ImageList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//ImageList next1 = new ImageList(open);// new displaySimilarityGroups(open);
			}
		});
		classifyImageNextBtn.setBounds(581, 433, 111, 40);
		}
		return classifyImageNextBtn;
	}
	
	public void checkBox() {
		//Object source = e.getItemSelectable();
		BlockMatching block = new BlockMatching(); 
		
		int numOfGroup = 1,flag = 0,flagName =0;
		String fileName = null,fileName1 = null;
		File folder = new File(CUtils.GetImagesDestPath() +"goodImages\\");
		BufferedImage image = null;
		Histogram his = new Histogram(image);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length -1; i++){
			if (listOfFiles[i].isFile()){
				if(listOfFiles[i]==null)
					continue ;
				if(flagName == 0){
					fileName = listOfFiles[i].getName();
					flagName = 1;
				}
				fileName1 = listOfFiles[i+1].getName();
				if(MAD){
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\")){
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"))
						break;
					}
						if(flag == 0)
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"+numOfGroup+"\\")){
							flag =1;
							break;
						}
					double res = block.identifyTheRequirArea(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"+numOfGroup+"\\", fileName, fileName1, "MAD", (int)PSpinner.getValue(), (int)qSpinner.getValue(), (int)heightSpinner.getValue());
					if(res == -1){
						flag =0;
						try {
							image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
							BufferedImage out = image;
							CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"+numOfGroup+"\\"+fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						numOfGroup ++;
						fileName = fileName1;
					}else if(res != -44){
						try{
							image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
							BufferedImage out = image;
							CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"+numOfGroup+"\\"+fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(MES){
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\")){
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\MES\\"))
						break;
					}
						if(flag == 0)
						if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "BloackMatching\\MES\\"+numOfGroup+"\\")){
							flag =1;
							break;
						}
					double res = block.identifyTheRequirArea(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath() + "BloackMatching\\MAD\\"+numOfGroup+"\\", fileName, fileName1, "MES", (int)PSpinner.getValue(), (int)qSpinner.getValue(), (int)heightSpinner.getValue());
					if(res == -1){
						flag =0;
						try {
							image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
							BufferedImage out = image;
							CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "BloackMatching\\MES\\"+numOfGroup+"\\"+fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						numOfGroup ++;
						fileName = fileName1;
					}else if(res != -44){
						try{
							image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
							BufferedImage out = image;
							CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "BloackMatching\\MES\\"+numOfGroup+"\\"+fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(COR){
					
				}
				if(INTER){
					
				}
				if(BHATT){
					
				}
				if(CHI){
					
				}
			}
		}
		System.out.println("end loop");
	}	
		public void closeFrame(){
 			super.dispose();
 		}
}
