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
public class ImproveFocus extends JFrame{
	private JLabel titleLbl;
	public ImproveFocus(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);
		getContentPane().setLayout(null);
		
		
		titleLbl = new JLabel("Focus measurement");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(43, 13, 267, 44);
		getContentPane().add(titleLbl);
		
	}

}
