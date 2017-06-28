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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import Class.CUtils;
import Class.Focus;
import Class.BlockMatching;
import Class.Histogram;
import Class.ImproveFocus;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
public class ImproveFocusGUI extends JFrame{
	private JComboBox methodCombo;
	private JLabel titleLbl,methodsLbl;
	private JButton nextImprovebtn,executeBtn;
	private ImproveFocus improve = new ImproveFocus();
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblChosseTheSize;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private int sigma,ker,builder =0;
	private JPanel panel_1;
	private JLabel label;
	private JComboBox comboBox_2;
	private ArrayList<String> needImprove,notNeedImprove;
	public  ArrayList<String> needImproveLocal=new ArrayList <String>();
	private String path;
	private String focusFunc;
	private JButton executeBtn1;//
	
	public ImproveFocusGUI(String path,String focusFunc){
		this.path = path;
		builder = 1;
		this.focusFunc=focusFunc;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);
		getContentPane().setLayout(null);
		this.setVisible(true);
		//methodCombo = new JComboBox();
		//getContentPane().add(getMethods());
		
		titleLbl = new JLabel("Focus measurement");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(43, 13, 267, 44);
		getContentPane().add(titleLbl);
		
		methodsLbl = new JLabel("Improving methods");
		methodsLbl.setBounds(43,70,129,33);
		getContentPane().add(methodsLbl);
		
		executeBtn1 = new JButton("Execute");
		executeBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//herre
				nextImprovebtn.setEnabled(true);
				File folder = new File(path);
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length; i++){
					if (listOfFiles[i].isDirectory()){
						if(listOfFiles[i]==null||listOfFiles[i].getName().toString().equalsIgnoreCase("display"))
							continue ;
						//System.out.println("i am "+listOfFiles[i].getPath());
						File temp = new File(listOfFiles[i].getPath()+"\\Best");
						File[] listTemp = temp.listFiles();
						checkIfNeedToImprove(listTemp);//adding all the unfocused images to the list 
						//test(listTemp);
						improvList(); //sending the list to improve the focus 
						needImproveLocal.clear();//clearing the list 
					}
				}

					}
		
			
		});
		executeBtn1.setBounds(43, 241, 187, 40);
		getContentPane().add(executeBtn1);
		getContentPane().add(getNextBtn());
		
	methodCombo = new JComboBox();
	methodCombo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(methodCombo == null)
				methodCombo = new JComboBox();
			
			if(methodCombo.getSelectedItem().toString() == "Unsharp masking"){
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
			if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
			if(methodCombo.getSelectedItem().toString() == ""){
				panel.setVisible(false);
				panel_1.setVisible(false);
			}
		}
	});
	methodCombo.setModel(new DefaultComboBoxModel(new String[]{"","Unsharp masking","Spitial filter"}));
	methodCombo.setBounds(169, 71, 214, 31);
	getContentPane().add(methodCombo);
	
	panel = new JPanel();
	panel.setBounds(43, 117, 363, 111);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	lblNewLabel = new JLabel("Choose the size of sigma:");
	lblNewLabel.setBounds(0, 13, 157, 16);
	panel.add(lblNewLabel);
	
	lblChosseTheSize = new JLabel("Chosse the size of kernel:");
	lblChosseTheSize.setBounds(0, 68, 182, 16);
	panel.add(lblChosseTheSize);
	panel.setVisible(false);
	comboBox = new JComboBox();
	comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedItem().toString() == "1")
				sigma = 1;
			if(comboBox.getSelectedItem().toString() == "2")
				sigma = 2;
		}
	});
	comboBox.setBounds(169, 10, 63, 22);
	panel.add(comboBox);
	comboBox.setModel(new DefaultComboBoxModel(new String[]{"","1","2"}));
	
	comboBox_1 = new JComboBox();
	comboBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedItem().toString() == "5X5")
				ker = 2;
			if(comboBox_1.getSelectedItem().toString() == "3X3")
				ker = 1;
		}
	});
	comboBox_1.setBounds(169, 65, 63, 22);
	comboBox_1.setModel(new DefaultComboBoxModel(new String[]{"","5X5","3X3"}));
	panel.add(comboBox_1);
	
	panel_1 = new JPanel();
	panel_1.setBounds(43, 116, 353, 123);
	getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	label = new JLabel("Chosse the size of kernel:");
	label.setBounds(0, 16, 182, 16);
	panel_1.add(label);
	panel_1.setVisible(false);
	comboBox_2 = new JComboBox();
	comboBox_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_2.getSelectedItem().toString() == "5X5")
				ker = 3;
			if(comboBox_2.getSelectedItem().toString() == "3X3")
				ker = 2;
			if(comboBox_2.getSelectedItem().toString() == "3X3_90")
				ker = 1;
		}
	});
	comboBox_2.setBounds(169, 13, 63, 22);
	comboBox_2.setModel(new DefaultComboBoxModel(new String[]{"","5X5","3X3","3X3_90"}));
	panel_1.add(comboBox_2);
	}
	
	public ImproveFocusGUI(ArrayList<String> needToImproveImages, ArrayList<String> notNeedToImproveImages){
		needImprove = needToImproveImages;
		notNeedImprove = notNeedToImproveImages;
		builder = 2;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);
		getContentPane().setLayout(null);
		this.setVisible(true);
		//methodCombo = new JComboBox();
		//getContentPane().add(getMethods());
		
		titleLbl = new JLabel("Focus measurement");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(43, 13, 267, 44);
		getContentPane().add(titleLbl);
		
		methodsLbl = new JLabel("Improving methods");
		methodsLbl.setBounds(43,70,129,33);
		getContentPane().add(methodsLbl);
		
		executeBtn = new JButton("Execute");
		executeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextImprovebtn.setEnabled(true);
				File folder = new File(CUtils.GetImagesDestPath()+"goodImages\\");
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < needImprove.size(); i++){
				/*	if (listOfFiles[i].isFile()){
						if(listOfFiles[i]==null)
							continue ;
						String fileName = listOfFiles[i].getName();*/
					String fileName = needImprove.get(i);
						if(methodCombo.getSelectedItem().toString() == "Unsharp masking"){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\blurImage\\"))
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\maskImages\\"))
										if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpImages\\"))
											if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\"))
												break;
							improve.blur(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName,ker,sigma);
							improve.mask(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName);
							improve.improveFocus(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\maskImages\\", fileName);
							Path src = Paths.get(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpImages\\" + "sharp_"+fileName);
							Path des = Paths.get(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\" +  "sharp_" + fileName);
						System.out.println(src +" "+ des);
							try {
								Files.copy(src,des,REPLACE_EXISTING);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
						
						if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\laplacianMask\\"))
									if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpLaplacian\\"))
										if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\blurImage\\"))
											if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\maskImages\\"))
												if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpImages\\"))
													if(!CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\"))
														break;
							improve.blur(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName,2,2);
							improve.mask(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName);
							improve.improveFocus(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\maskImages\\", fileName);
							improve.laplacianMask(CUtils.GetImagesDestPath()+ "ImproveFocus\\sharpImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\laplacianMask\\", fileName, ker);
							improve.improveFocusLaplacian(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\laplacianMask\\", fileName);
							Path src = Paths.get(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpLaplacian\\" + "sharp_"+fileName);
							Path des = Paths.get(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\" +  "sharp_" + fileName);
						System.out.println(src +" "+ des);
							try {
								Files.copy(src,des,REPLACE_EXISTING);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					//}
				}
				if(!notNeedToImproveImages.isEmpty())
					for(int i = 0 ; i < notNeedToImproveImages.size() ; i ++){
						String fileName = notNeedToImproveImages.get(i);
						Path src = Paths.get(CUtils.GetImagesDestPath() + "goodImages\\" +fileName);
						Path des = Paths.get(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\" + fileName);
					System.out.println(src +" "+ des);
						try {
							Files.copy(src,des,REPLACE_EXISTING);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
			}
		});
		executeBtn.setBounds(43, 241, 187, 40);
		getContentPane().add(executeBtn);
		getContentPane().add(getNextBtn());
		
	methodCombo = new JComboBox();
	methodCombo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(methodCombo == null)
				methodCombo = new JComboBox();
			
			if(methodCombo.getSelectedItem().toString() == "Unsharp masking"){
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
			if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
			if(methodCombo.getSelectedItem().toString() == ""){
				panel.setVisible(false);
				panel_1.setVisible(false);
			}
		}
	});
	methodCombo.setModel(new DefaultComboBoxModel(new String[]{"","Unsharp masking","Spitial filter"}));
	methodCombo.setBounds(169, 71, 214, 31);
	getContentPane().add(methodCombo);
	
	panel = new JPanel();
	panel.setBounds(43, 117, 363, 111);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	lblNewLabel = new JLabel("Choose the size of sigma:");
	lblNewLabel.setBounds(0, 13, 157, 16);
	panel.add(lblNewLabel);
	
	lblChosseTheSize = new JLabel("Chosse the size of kernel:");
	lblChosseTheSize.setBounds(0, 68, 182, 16);
	panel.add(lblChosseTheSize);
	panel.setVisible(false);
	comboBox = new JComboBox();
	comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedItem().toString() == "1")
				sigma = 1;
			if(comboBox.getSelectedItem().toString() == "2")
				sigma = 2;
		}
	});
	comboBox.setBounds(169, 10, 63, 22);
	panel.add(comboBox);
	comboBox.setModel(new DefaultComboBoxModel(new String[]{"","1","2"}));
	
	comboBox_1 = new JComboBox();
	comboBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedItem().toString() == "5X5")
				ker = 2;
			if(comboBox_1.getSelectedItem().toString() == "3X3")
				ker = 1;
		}
	});
	comboBox_1.setBounds(169, 65, 63, 22);
	comboBox_1.setModel(new DefaultComboBoxModel(new String[]{"","5X5","3X3"}));
	panel.add(comboBox_1);
	
	panel_1 = new JPanel();
	panel_1.setBounds(43, 116, 353, 123);
	getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	label = new JLabel("Chosse the size of kernel:");
	label.setBounds(0, 16, 182, 16);
	panel_1.add(label);
	panel_1.setVisible(false);
	comboBox_2 = new JComboBox();
	comboBox_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_2.getSelectedItem().toString() == "5X5")
				ker = 3;
			if(comboBox_2.getSelectedItem().toString() == "3X3")
				ker = 2;
			if(comboBox_2.getSelectedItem().toString() == "3X3_90")
				ker = 1;
		}
	});
	comboBox_2.setBounds(169, 13, 63, 22);
	comboBox_2.setModel(new DefaultComboBoxModel(new String[]{"","5X5","3X3","3X3_90"}));
	panel_1.add(comboBox_2);

		
	}
	
	public JButton getExecuteBtn1(){
		if(executeBtn1 == null)
			executeBtn1 = new JButton();
		//here 
		/*
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isDirectory()){
				if(listOfFiles[i]==null||listOfFiles[i].getName().toString().equalsIgnoreCase("display"))
					continue ;
				//System.out.println("i am "+listOfFiles[i].getPath());
				File temp = new File(listOfFiles[i].getPath()+"\\Best");
				File[] listTemp = temp.listFiles();
				checkIfNeedToImprove(listTemp);//adding all the unfocused images to the list 
				//while(true){
					//if(methodCombo==null)
				//		break;
				//}
				//improvList(); //sending the list to improve the focus 
				//needImproveLocal.clear();//clearing the list 
			}
		}
		*/

		return executeBtn1;
	}
	
	public void improvList(){
		
		ImproveFocus improv = new ImproveFocus();
		if(CUtils.CreateDirectory("C:\\tempfororj"))
			for(int i=0;i<needImproveLocal.size();i++){
				if(methodCombo.getSelectedItem().toString().equalsIgnoreCase("Unsharp masking")){
					File file = new File (needImproveLocal.get(i));
					improv.blurLocal(file.getPath(), file.getName(),ker, sigma);
					improv.maskLocal(file.getPath(), "C:\\tempfororj\\"+"blurred"+file.getName(), file.getName());
					improv.improveFocusLocal(file.getPath(), "C:\\tempfororj\\"+"mask"+file.getName(), file.getName(), file.getParent()+"\\display\\");
					
				}
				if(methodCombo.getSelectedItem().toString().equalsIgnoreCase("Spitial filter")){
					File file = new File (needImproveLocal.get(i));
					//improv.blurLocal(file.getPath(), file.getName(),ker, sigma);
					//improv.maskLocal(file.getPath(), "C:\\tempfororj\\"+"blurred"+file.getName(), file.getName());
					improv.laplacianMaskLocal(file.getPath(), "C:\\tempfororj\\"+"laplacian_mask"+file.getName(), file.getName(), ker);
					improv.improveFocusLaplacianLocal(file.getPath(), "C:\\tempfororj\\"+"laplacian_mask"+file.getName(), file.getName(), file.getParent()+"\\display\\");
				}
			
			
			
				}
		
		try {
			CUtils.DeleteDirectory("C:\\tempfororj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
	
	public void checkIfNeedToImprove(File[] list){
		Focus focus= new Focus();
		for(int i=0;i<list.length;i++){
			if(list[i].getName().toString().equalsIgnoreCase("display"))
				continue ;
		//	System.out.println("in the func "+list[i].getPath());
			//System.out.println("in the getParent "+list[i].getParent());
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageDifferentiationA")){
				if(focus.FocusMeasuresBasedOnImageDifferentiationA(list[i].getPath(),9700)<60195.6457779598)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageDifferentiationB")){
				if(focus.FocusMeasuresBasedOnImageDifferentiationB(list[i].getPath(),9700)<3615107.80110014)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FunctionBasedOnDepthOfPeaksAndValleysC")){
				if(focus.FunctionBasedOnDepthOfPeaksAndValleysC(list[i].getPath(),120)<1.1509)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			
			if(focusFunc.equalsIgnoreCase("FunctionBasedOnDepthOfPeaksAndValleysB")){
				if(focus.FunctionBasedOnDepthOfPeaksAndValleysB(list[i].getPath(),120)<5478.94792)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FunctionBasedOnDepthOfPeaksAndValleysA")){
				if(focus.FunctionBasedOnDepthOfPeaksAndValleysA(list[i].getPath(),120)<786937.384575268)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageStatisticsVariance1")){
				if(focus.FocusMeasuresBasedOnImageStatisticsVariance(list[i].getPath(),1)<1098.289) 
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					System.out.println(src+" "+des);
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageStatisticsNormalizedVariance1")){
				if(focus.FocusMeasuresBasedOnImageStatisticsNormalizedVariance(list[i].getPath(),1)<83.516)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageStatisticsVariance2")){
				
				if(focus.FocusMeasuresBasedOnImageStatisticsVariance(list[i].getPath(),2)<1102.413)
					needImproveLocal.add(list[i].getPath());
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					System.out.println(src+" "+des);
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			if(focusFunc.equalsIgnoreCase("FocusMeasuresBasedOnImageStatisticsNormalizedVariance2")){
				
				if(focus.FocusMeasuresBasedOnImageStatisticsNormalizedVariance(list[i].getPath(),2)<83.672){
					needImproveLocal.add(list[i].getPath());
				}
				else{
					Path src = Paths.get(list[i].getPath());
					Path des = Paths.get(list[i].getParent()+"\\display\\"+list[i].getName());
					try {
						Files.copy(src,des,REPLACE_EXISTING);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			}
			
		}

		
	}
	
	public JButton getExecuteBtn(){
		if(executeBtn == null)
			executeBtn = new JButton();
		File folder = new File(CUtils.GetImagesDestPath()+"goodImages\\");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				if(listOfFiles[i]==null)
					continue ;
				String fileName = listOfFiles[i].getName();
		
				if(methodCombo.getSelectedItem().toString() == "Unsharp masking"){
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\"))
						if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\blurImage\\"))
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\maskImages\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpImages\\"))
					improve.blur(CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName,ker,sigma);
					improve.mask(CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName);
					improve.improveFocus(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\maskImages\\", fileName);
				}
				
				if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\"))
						if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\laplacianMask\\"))
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpLaplacian\\"))
					improve.laplacianMask(CUtils.GetImagesDestPath()+"ImproveFocus\\sharpImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\laplacianMask\\", fileName, ker);
					improve.improveFocusLaplacian(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\laplacianMask\\", fileName);
				}
			}
		}
		return executeBtn;
	}
	public JButton getNextBtn(){
		if(nextImprovebtn == null){
			nextImprovebtn = new JButton("Next");

			nextImprovebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DisplayAreasOfInterestGUI disArea;
				 DisplayImages dis;
				if(builder == 1)
					disArea = new DisplayAreasOfInterestGUI(path);
				if(builder == 2)
					dis = new DisplayImages(CUtils.GetImagesDestPath() + "ImproveFocus\\best\\");
			}
		});
			nextImprovebtn.setBounds(581, 433, 111, 40);
		}
		nextImprovebtn.setEnabled(false);
		return nextImprovebtn;
	}
	public JComboBox getMethods(){
		
		if(methodCombo == null)
			methodCombo = new JComboBox();
		
		if(methodCombo.getSelectedItem().toString() == "Unsharp masking"){
			panel.setVisible(true);
			panel_1.setVisible(false);
		}
		if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
			panel.setVisible(false);
			panel_1.setVisible(true);
		}else{
			panel.setVisible(false);
			panel_1.setVisible(false);
		}
		methodCombo.setModel(new DefaultComboBoxModel(new String[]{"","Unsharp masking","Spitial filter"}));
		methodCombo.setBounds(169, 71, 214, 31);
		
		return methodCombo;
	}
	public void closeFrame(){
			super.dispose();
		}
}
