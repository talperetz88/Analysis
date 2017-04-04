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

public class classifyImages extends JFrame{
	
	private JComboBox comboBox = null;
	private JPanel panel = null, panel1 =null;
	private JLabel lblDistanceFunction,lblClassifyMethods,lblChoose,lblHistogramMethods,lblBlockMethods;
	private JCheckBox MADCheckBox,MSECheckBox;
	private JButton btnNewButton = null;
	private openPage open;
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
		lblHistogramMethods.setBounds(45, 149, 132, 16);
		getContentPane().add(lblHistogramMethods);
		lblHistogramMethods.setVisible(false);
		
		
		lblBlockMethods = new JLabel("Block matching method");
		lblBlockMethods.setBounds(45, 149, 132, 16);
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
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Correlation");
			chckbxNewCheckBox.setBounds(8, 22, 113, 25);
			panel.add(chckbxNewCheckBox);
			
			JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Chi-Square");
			chckbxNewCheckBox_1.setBounds(8, 70, 113, 25);
			panel.add(chckbxNewCheckBox_1);
			
			JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Intersection");
			chckbxNewCheckBox_2.setBounds(155, 22, 113, 25);
			panel.add(chckbxNewCheckBox_2);
			
			JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Bhattacharyya distance");
			chckbxNewCheckBox_3.setBounds(155, 70, 168, 25);
			panel.add(chckbxNewCheckBox_3);
			

		}
		return panel;
	}
	public JPanel getPanelBlock(){
		if(panel1 == null){
		panel1 = new JPanel();
		panel1.setBounds(45, 201, 368, 115);
		
		panel1.setLayout(null);
		
		MADCheckBox = new JCheckBox("MAD");
		MADCheckBox.setBounds(8, 22, 113, 25);
		panel1.add(MADCheckBox);
		
		MSECheckBox = new JCheckBox("MSE");
		MSECheckBox.setBounds(8, 70, 113, 25);
		panel1.add(MSECheckBox);
		JSpinner spinner = new JSpinner();
		spinner.setBounds(45, 360, 30, 22);
		panel1.add(spinner);
		
		JLabel lblPixel = new JLabel("Pixel");
		lblPixel.setBounds(105, 363, 56, 16);
		panel1.add(lblPixel);
		
		JLabel lblChosee = new JLabel("Chosee the size of paramter P");
		lblChosee.setBounds(45, 331, 196, 16);
		panel1.add(lblChosee);
		}
		return panel1;
	}
	public JButton getNextbtn(){
		if(btnNewButton == null){
		btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				//ImageList next1 = new ImageList(open);// new displaySimilarityGroups(open);
			}
		});
		btnNewButton.setBounds(537, 375, 111, 40);
		}
		return btnNewButton;
	}
	
		public void closeFrame(){
 			super.dispose();
 		}
}
