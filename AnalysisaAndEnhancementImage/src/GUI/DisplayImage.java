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
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import Class.CUtils;
import Class.Check;
public class DisplayImage extends JFrame{
		public static Object openPage;
	    private JLabel photographLabel = new JLabel();
	    private JToolBar buttonBar = new JToolBar();
		public DisplayImage() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Icon Demo: Please Select an Image");
	        
	        // A label for displaying the pictures
	        photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
	        photographLabel.setHorizontalTextPosition(JLabel.CENTER);
	        photographLabel.setHorizontalAlignment(JLabel.CENTER);
	        photographLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        
	        // We add two glue components. Later in process() we will add thumbnail buttons
	        // to the toolbar inbetween thease glue compoents. This will center the
	        // buttons in the toolbar.
	        buttonBar.add(Box.createGlue());
	        buttonBar.add(Box.createGlue());
	        
	        add(buttonBar, BorderLayout.SOUTH);
	        add(photographLabel, BorderLayout.CENTER);
	        
	        setSize(400, 300);
	        
	        // this centers the frame on the screen
	        setLocationRelativeTo(null);
	        
	        // start the image loading SwingWorker in a background thread
	        //loadimages.execute();
	    
			this.setVisible(true);
		}
		
		

	}
 		
