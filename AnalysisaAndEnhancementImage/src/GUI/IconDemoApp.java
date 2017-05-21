package GUI;


/*
* Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
*   - Redistributions of source code must retain the above copyright
*     notice, this list of conditions and the following disclaimer.
*
*   - Redistributions in binary form must reproduce the above copyright
*     notice, this list of conditions and the following disclaimer in the
*     documentation and/or other materials provided with the distribution.
*
*   - Neither the name of Oracle or the names of its
*     contributors may be used to endorse or promote products derived
*     from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/ 


import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import java.awt.Scrollbar;
import java.awt.Component;

//work 

public class IconDemoApp extends JFrame {

private JLabel photographLabel = new JLabel();
JScrollPane scroll = new JScrollPane();
private JToolBar buttonBar = new JToolBar();

private String imagedir = "C:\\תמונות\\תמנות\\דרום אמריקה\\";//C:\\project\\images\\1\\goodImages\\sharpLaplacian\\";//"C:\\Users\\omri\\git\\Analysis\\AnalysisaAndEnhancementImage\\";//

//private MissingIcon placeholderIcon = new MissingIcon();

/**
* List of all the descriptions of the image files. These correspond one to
* one with the image file names
*/
private String[] imageCaptions = { "2", " 3",
"6", "7", "8"};

/**
* List of all the image files to load.
*/
private String[] imageFileNames ={"2.jpg", "3.jpg" ,"6.jpg" , "7.jpg" , "8.jpg"} ;

/**
* Main entry point to the demo. Loads the Swing elements on the "Event
* Dispatch Thread".
*
* @param args
*/
public static void main(String args[]) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
IconDemoApp app = new IconDemoApp();
app.setVisible(true);
}
});
}

/**
* Default constructor for the demo.
*/
public IconDemoApp() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//setBounds(100,100,722,533);
setTitle("Icon Demo: Please Select an Image");



// A label for displaying the pictures
photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
photographLabel.setHorizontalTextPosition(JLabel.CENTER);
photographLabel.setHorizontalAlignment(JLabel.CENTER);
photographLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




// We add two glue components. Later in process() we will add thumbnail buttons
// to the toolbar inbetween thease glue compoents. This will center the
// buttons in the toolbar.



buttonBar.add(Box.createGlue());
buttonBar.add(Box.createGlue());



getContentPane().add(buttonBar, BorderLayout.SOUTH);
getContentPane().add(photographLabel, BorderLayout.CENTER);

setSize(1000, 1000);

// this centers the frame on the screen
setLocationRelativeTo(null);

// start the image loading SwingWorker in a background thread
loadimages.execute();
}

/**
* SwingWorker class that loads the images a background thread and calls publish
* when a new one is ready to be displayed.
*
* We use Void as the first SwingWroker param as we do not need to return
* anything from doInBackground().
*/
private SwingWorker<Void, ThumbnailAction> loadimages = new SwingWorker<Void, ThumbnailAction>() {

/**
* Creates full size and thumbnail versions of the target image files.
*/
@Override
protected Void doInBackground() throws Exception {
	
	File folder = new File(imagedir);
	File[] listOfFiles = folder.listFiles();
	String fileName = null;
	for (int i = 0; i < listOfFiles.length -1; i++){
		if (listOfFiles[i].isFile()){
			if(listOfFiles[i]==null)
				continue ;
			fileName = listOfFiles[i].getName();
//for (int i = 0; i < imageCaptions.length; i++) {
ImageIcon icon;
icon = createImageIcon(imagedir + fileName, fileName);

ThumbnailAction thumbAction;
if(icon != null){
    
    ImageIcon thumbnailIcon = new ImageIcon(getScaledImage(icon.getImage(), 32, 32));
    
    thumbAction = new ThumbnailAction(icon, thumbnailIcon, fileName);
    
}else{
    Icon placeholderIcon = null;
	// the image failed to load for some reason
    // so load a placeholder instead
    thumbAction = new ThumbnailAction(placeholderIcon, placeholderIcon, fileName);
}
publish(thumbAction);
}
	}
// unfortunately we must return something, and only null is valid to
// return when the return type is void.
return null;
}

/**
* Process all loaded images.
*/
@Override
protected void process(List<ThumbnailAction> chunks) {
for (ThumbnailAction thumbAction : chunks) {
JButton thumbButton = new JButton(thumbAction);
// add the new button BEFORE the last glue
// this centers the buttons in the toolbar
buttonBar.add(thumbButton, buttonBar.getComponentCount() - 1);
}
}
};

/**
* Creates an ImageIcon if the path is valid.
* @param String - resource path
* @param String - description of the file
*/
protected ImageIcon createImageIcon(String path,String description) {
	
//java.net.URL imgURL = getClass().getResource(path);
	//loding an image 
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File(path));
	   
	} catch (IOException e) {
		System.err.println(e);
	}
if (img != null) {
return new ImageIcon(img, description);
} else {
System.err.println("Couldn't find file: " + path);
return null;
}
}

/**
* Resizes an image using a Graphics2D object backed by a BufferedImage.
* @param srcImg - source image to scale
* @param w - desired width
* @param h - desired height
* @return - the new resized image
*/
private Image getScaledImage(Image srcImg, int w, int h){
BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
Graphics2D g2 = resizedImg.createGraphics();
g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
g2.drawImage(srcImg, 0, 0, w, h, null);
g2.dispose();
return resizedImg;
}

/**
* Action class that shows the image specified in it's constructor.
*/
private class ThumbnailAction extends AbstractAction{

/**
*The icon if the full image we want to display.
*/
private Icon displayPhoto;

/**
* @param Icon - The full size photo to show in the button.
* @param Icon - The thumbnail to show in the button.
* @param String - The descriptioon of the icon.
*/
public ThumbnailAction(Icon photo, Icon thumb, String desc){
displayPhoto = photo;

// The short description becomes the tooltip of a button.
putValue(SHORT_DESCRIPTION, desc);

// The LARGE_ICON_KEY is the key for setting the
// icon when an Action is applied to a button.
putValue(LARGE_ICON_KEY, thumb);
}

/**
* Shows the full image in the main area and sets the application title.
*/
public void actionPerformed(ActionEvent e) {
photographLabel.setIcon(displayPhoto);
setTitle("Icon Demo: " + getValue(SHORT_DESCRIPTION).toString());
}
}
}


