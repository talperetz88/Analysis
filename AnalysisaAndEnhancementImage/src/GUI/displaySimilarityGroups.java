package GUI;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.awt.List;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class displaySimilarityGroups extends JFrame {
	
	private Image [] images = new Image[] {};
    static final String[] EXTENSIONS = new String[]{"JPG", "png", "bmp", "jpg" };
    private Icon displayPhoto;
    private JLabel photographLabel = new JLabel();
    private JToolBar buttonBar = new JToolBar();
    private String [] imageCaptions  = new String[]{};
    private String[] imageFileNames = new String[]{};
    private String [] imageName = new String[] {};
    private String imagedir;
    //private MissingIcon placeholderIcon = new MissingIcon();
	//private openPage open;
	public displaySimilarityGroups(openPage open){
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,100,722,533);
        setTitle("Icon Demo: Please Select an Image");
        imagedir = open.getSaveDirict().getText();
        File dir = new File(imagedir);
        getImageFromFolder(images,imageName,dir);
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
        getContentPane().add(photographLabel);
         setVisible(true);
        // this centers the frame on the screen
        setLocationRelativeTo(null);
         

/*
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);		
		getContentPane().setLayout(null);
		String dirUrl = open.getSaveDirict().getText();//open.getSaveUrl().toString();
		File dir = new File(dirUrl);
		getImageFromFolder(images,dir);

		ImageIcon image = new ImageIcon();
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(62, 58, 145, 48);
		getContentPane().add(lblNewLabel);*/
		
	}

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    public void getImageFromFolder(Image[] images,String [] imageName,File dir){
		int i =0;
		if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    // you probably want something more involved here
                    // to display in your UI
                    photographLabel = new JLabel(new ImageIcon(img));
                    System.out.println("image: " + f.getName());
                    System.out.println(" width : " + img.getWidth());
                    System.out.println(" height: " + img.getHeight());
                    System.out.println(" size  : " + f.length());
                } catch (final IOException e) {
                    // handle errors here
                	System.out.println("eror1");
                }
            }
		}else System.out.println("eror");
		
	}



}
