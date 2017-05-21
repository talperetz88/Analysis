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
import Class.TriangleEdges;
import Class.TriangleUtils;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
public class classifyImages extends JFrame{
	
	private JComboBox comboBox = null;
	private JPanel panel = null, panel1 =null;
	private JLabel lblDistanceFunction,lblClassifyMethods,lblChoose,lblHistogramMethods,lblBlockMethods;
	public JCheckBox MADCheckBox,MSECheckBox;
	private JCheckBox chckbxRgb,chckbxHsv,bhattCheckBox,intersectionCheckBox,chiSquareCheckBox,correlationCheckBox;
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
			panel.setBounds(45, 201, 480, 272);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			correlationCheckBox = new JCheckBox("Correlation");
			correlationCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(correlationCheckBox.isSelected()){
						chckbxRgb.setVisible(true);
						chckbxHsv.setVisible(true);
					}
					if(!chiSquareCheckBox.isSelected() && !intersectionCheckBox.isSelected() && !correlationCheckBox.isSelected() && !bhattCheckBox.isSelected()){
						chckbxRgb.setVisible(false);
						chckbxHsv.setVisible(false);
					}
					if(bhattCheckBox.isSelected() && correlationCheckBox.isSelected() || correlationCheckBox.isSelected() && chiSquareCheckBox.isSelected() || correlationCheckBox.isSelected() && intersectionCheckBox.isSelected() ){
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			correlationCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COR = true;
					
				}
			});
			
			correlationCheckBox.setBounds(8, 22, 113, 25);
			panel.add(correlationCheckBox);
			
			chiSquareCheckBox = new JCheckBox("Chi-Square");
			chiSquareCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(chiSquareCheckBox.isSelected()){
						chckbxRgb.setVisible(true);
						chckbxHsv.setVisible(true);
					}
					if(!chiSquareCheckBox.isSelected() && !intersectionCheckBox.isSelected() && !correlationCheckBox.isSelected() && !bhattCheckBox.isSelected()){
						chckbxRgb.setVisible(false);
						chckbxHsv.setVisible(false);
					}
					if(bhattCheckBox.isSelected() && chiSquareCheckBox.isSelected() || chiSquareCheckBox.isSelected() && intersectionCheckBox.isSelected() || chiSquareCheckBox.isSelected() && correlationCheckBox.isSelected()){
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
					}	
				}
			});
			chiSquareCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CHI = true;
				}
			});

			chiSquareCheckBox.setBounds(8, 70, 113, 25);
			panel.add(chiSquareCheckBox);
			
			intersectionCheckBox = new JCheckBox("Intersection");
			intersectionCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(intersectionCheckBox.isSelected()){
						chckbxRgb.setVisible(true);
						chckbxHsv.setVisible(true);
					}
					if(!chiSquareCheckBox.isSelected() && !intersectionCheckBox.isSelected() && !correlationCheckBox.isSelected() && !bhattCheckBox.isSelected()){
						chckbxRgb.setVisible(false);
						chckbxHsv.setVisible(false);
					}
					if(bhattCheckBox.isSelected() && intersectionCheckBox.isSelected() || intersectionCheckBox.isSelected() && chiSquareCheckBox.isSelected() || intersectionCheckBox.isSelected() && correlationCheckBox.isSelected()){
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
					}	
				}
			});
			intersectionCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					INTER = true;
				}
			});
			intersectionCheckBox.setBounds(155, 22, 113, 25);
			panel.add(intersectionCheckBox);
			
			bhattCheckBox = new JCheckBox("Bhattacharyya distance");
			bhattCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(bhattCheckBox.isSelected()){
						chckbxRgb.setVisible(true);
						chckbxHsv.setVisible(true);
					}
					if(!chiSquareCheckBox.isSelected() && !intersectionCheckBox.isSelected() && !correlationCheckBox.isSelected() && !bhattCheckBox.isSelected()){
						chckbxRgb.setVisible(false);
						chckbxHsv.setVisible(false);
					}
					if(bhattCheckBox.isSelected() && chiSquareCheckBox.isSelected() || bhattCheckBox.isSelected() && intersectionCheckBox.isSelected() || bhattCheckBox.isSelected() && correlationCheckBox.isSelected()){
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			bhattCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BHATT = true;
				}
			});
			bhattCheckBox.setBounds(155, 70, 168, 25);
			panel.add(bhattCheckBox);
			
			chckbxRgb = new JCheckBox("RGB");
			chckbxRgb.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chckbxRgb.isSelected() && chckbxHsv.isSelected())
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			});
			chckbxRgb.setBounds(8, 125, 113, 25);
			chckbxRgb.setVisible(false);
			panel.add(chckbxRgb);
			
			chckbxHsv = new JCheckBox("HSV");
			chckbxHsv.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(chckbxRgb.isSelected() && chckbxHsv.isSelected())
						JOptionPane.showMessageDialog(null, "Plese choose one option in each time", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			});
			chckbxHsv.setBounds(155, 125, 113, 25);
			chckbxHsv.setVisible(false);
			panel.add(chckbxHsv);
		

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
				FocusMeasurement next = new FocusMeasurement();
				try {
					ImageList next1 = new ImageList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//ImageList next1 = new ImageList(open);// new displaySimilarityGroups(open);
			}
		});
		
		classifyImageNextBtn.setBounds(581, 433, 111, 40);
		//classifyImageNextBtn.setIcon(new ImageIcon("next.png"));
		//classifyImageNextBtn.setBorder(BorderFactory.createEmptyBorder());
		//classifyImageNextBtn.setContentAreaFilled(false);
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
		//Histogram his = new Histogram(image);
		File[] listOfFiles = folder.listFiles();
		
		if(comboBox.getSelectedItem().toString() == "Block matching"){
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
			}
		}
		}
		if(comboBox.getSelectedItem().toString() == "RGB histogram"){
			numOfGroup = 0;
			flagName = 0;
			byte [][]matrixg  = null;
			TriangleEdges edges = null;
			int heightTreshold = 5,numOfGroupA = 1, numOfGroupB = 1, numOfGroupC = 1 , numOfGroupD = 1 ;
			double res = 0 ,res1 = 0,treshold = 0.2;
			Histogram hist = null,hist1 = null;
			for (int i = 0; i < listOfFiles.length -1; i++){
				if (listOfFiles[i].isFile() && listOfFiles[i+1].isFile()){
					if(listOfFiles[i]==null)
						continue ;
					if(flagName == 0){
						fileName = listOfFiles[i].getName();
						flagName = 1;
						matrixg =CUtils.BlackWhiteImageToBinaryArray(CUtils.GetImagesDestPath() +"matlabRes\\" +"MatlabRes"+ fileName);
						edges = TriangleUtils.FindTriangleEdges(matrixg);
						try {
							hist = new Histogram(ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName)));
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					fileName1 = listOfFiles[i+1].getName();	
					try {
						hist1 = new Histogram(ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName1)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				    byte [][]matrixg2 = CUtils.BlackWhiteImageToBinaryArray(CUtils.GetImagesDestPath()+ "matlabRes\\" +"MatlabRes"+ fileName1);  
				    TriangleEdges edges1 = TriangleUtils.FindTriangleEdges(matrixg2);
					double height = block.culcHeightOfTriangle(edges);
					double height1 = block.culcHeightOfTriangle(edges1);
					
					if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\"))
						break;
					
						if(COR){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Correlation\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Correlation\\RGB\\"))
									if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Correlation\\HSV\\"))
										break;
							if(flag == 0){
								try {
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedImage out = image;
								if(chckbxRgb.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Correlation\\RGB\\" +numOfGroupA+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Correlation\\RGB\\"+numOfGroupA+"\\"+fileName);
									flag = 1;
								}
								if(chckbxHsv.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Correlation\\HSV\\" +numOfGroupA+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Correlation\\HSV\\"+numOfGroupA+"\\"+fileName);
									flag = 1;
								}
								
							}
							if(Math.abs(height - height1) < heightTreshold){
								if(chckbxRgb.isSelected()){
									res = hist.correlationRGB(hist, hist1);
								}
								if(chckbxHsv.isSelected()){
									res1 = hist.correlationHSV(hist, hist1);
								}
							}
							if((1-res) < treshold || (1-res1) < treshold){
								res = res1 = 0;
								try{
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName1));
									BufferedImage out = image;
									if(chckbxRgb.isSelected())
									CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Correlation\\RGB\\"+numOfGroupA+"\\"+fileName1);
									if(chckbxHsv.isSelected())
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Correlation\\HSV\\"+numOfGroupA+"\\"+fileName1);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								flagName = 0;
								flag = 0;
								numOfGroupA++;
							}
						}
						if(INTER){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Intersection\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Intersection\\RGB\\"))
									if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Intersection\\HSV\\"))
											break;
							if(flag == 0){
								try {
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedImage out = image;
									if(chckbxRgb.isSelected()){
										if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Intersection\\RGB\\" +numOfGroupB+"\\" ))
											CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Intersection\\RGB\\"+numOfGroupB+"\\"+fileName);
										flag = 1;
									}
									if(chckbxHsv.isSelected()){
										if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Intersection\\HSV\\" +numOfGroupB+"\\" ))
											CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Intersection\\HSV\\"+numOfGroupB+"\\"+fileName);
										flag = 1;
									}
							}
							if(Math.abs(height - height1) < heightTreshold){
								if(chckbxRgb.isSelected()){
									res = hist.intersectionRGB(hist, hist1);
								}
								if(chckbxHsv.isSelected()){
									res1 = hist.intersectionHSV(hist, hist1);
								}
							}
							if((1 - res) < treshold || (1-res1) < treshold){
								res = res1 = 0;
								try{
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName1));
									BufferedImage out = image;
									if(chckbxRgb.isSelected())
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Intersection\\RGB\\"+numOfGroupB+"\\"+fileName1);
									if(chckbxHsv.isSelected())
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Intersection\\HSV\\"+numOfGroupB+"\\"+fileName1);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								flagName = 0;
								flag = 0;
								numOfGroupB++;
							}
						}
						if(BHATT){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\HSV\\"))
									if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\RGB\\"))
										break;
							if(flag == 0){
								try {
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedImage out = image;
								if(chckbxRgb.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\RGB\\" +numOfGroupC+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\RGB\\"+numOfGroupC+"\\"+fileName);
									flag = 1;
								}
								if(chckbxHsv.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\HSV\\" +numOfGroupC+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\BhattacharyyaDistance\\HSV\\"+numOfGroupC+"\\"+fileName);
									flag = 1;
								}
							}
							if(Math.abs(height - height1) < heightTreshold){
								if(chckbxRgb.isSelected()){
									res = hist.BhattacharyyaDistanceRGB(hist, hist1);
								}
								if(chckbxHsv.isSelected()){
									res1 = hist.BhattacharyyaDistanceHSV(hist, hist1);
								}
							}
							if((1-res) < treshold || (1-res1) < treshold){
								res = res1 = 0;
								try{
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName1));
									BufferedImage out = image;
									if(chckbxRgb.isSelected()){
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() +"Histogram\\BhattacharyyaDistance\\RGB" +numOfGroupC+"\\"+fileName1);
										
									}
									if(chckbxHsv.isSelected()){
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() +"Histogram\\BhattacharyyaDistance\\HSV\\" +numOfGroupC+"\\"+fileName1);
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								flagName = 0;
								flag = 0;
								numOfGroupC++;
							}
						}
						if(CHI){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\HSV\\"))
									if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\RGB\\"))
								break;
							if(flag == 0){
								try {
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								BufferedImage out = image;
								if(chckbxRgb.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\HSV\\" +numOfGroupD+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\RGB\\"+numOfGroupD+"\\"+fileName);
								flag = 1;
								}
								if(chckbxHsv.isSelected()){
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\HSV\\" +numOfGroupD+"\\" ))
										CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\HSV\\"+numOfGroupD+"\\"+fileName);
									flag = 1;
								}
								
							}
							if(Math.abs(height - height1) < heightTreshold){
								if(chckbxRgb.isSelected()){
									res = hist.chiSquareRGB(hist, hist1);
								}
								if(chckbxHsv.isSelected()){
									res1 = hist.chiSquareHSV(hist, hist1);
								}
							}
							if(res < treshold || res1 < treshold){
								res = res1 = 0;
								try{
									image = ImageIO.read(new File(CUtils.GetImagesDestPath() +"goodImages\\" + fileName1));
									BufferedImage out = image;
									if(chckbxRgb.isSelected())
									CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\RGB\\"+numOfGroupD+"\\"+fileName1);
									if(chckbxHsv.isSelected())
									CUtils.SaveImage(out, CUtils.GetImagesDestPath() + "Histogram\\Chi-Square\\HSV\\"+numOfGroupD+"\\"+fileName1);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								flagName = 0;
								flag = 0;
								numOfGroupD++;
							}
						}
					
				}
			}
		
		}
		System.out.println("end loop");
	}	
		public void closeFrame(){
 			super.dispose();
 		}
}
