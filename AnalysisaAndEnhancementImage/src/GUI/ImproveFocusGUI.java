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
	private int sigma,ker;
	private JPanel panel_1;
	private JLabel label;
	private JComboBox comboBox_2;
	public ImproveFocusGUI(){
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
		executeBtn.setBounds(43, 241, 187, 40);
		getContentPane().add(getExecuteBtn());
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
		
				if(methodCombo.getSelectedItem().toString() == "Unsharp masking" || methodCombo.getSelectedItem().toString() == "Spitial filter"){
					if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\"))
						if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\blurImage\\"))
							if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\maskImages\\"))
								if(CUtils.CreateDirectory(CUtils.GetImagesDestPath() + "ImproveFocus\\sharpImages\\"))
					improve.blur(CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName,ker,sigma);
					improve.mask(CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName);
					improve.improveFocus(CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\maskImages\\", fileName);
				}
				
				if(methodCombo.getSelectedItem().toString() == "Spitial filter"){
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
				
			}
		});
			nextImprovebtn.setBounds(581, 433, 111, 40);
		}
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
