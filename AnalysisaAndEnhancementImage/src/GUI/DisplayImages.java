	package GUI;
	
	
	
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
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JToolBar;
	import javax.swing.ScrollPaneConstants;
	import javax.swing.SwingUtilities;
	import javax.swing.SwingWorker;
	import javax.swing.border.Border;
	
	import java.awt.Scrollbar;
	import java.awt.Component;
	
	//work 
	
	public class DisplayImages extends JFrame {
	
	private JLabel photographLabel = new JLabel();
	private JPanel panel = new JPanel ();
	private JScrollPane scroll = new JScrollPane();
	private JToolBar buttonBar = new JToolBar();
	
	private String imagedir;// = "C:\\project\\images\\1\\ImproveFocus\\sharpLaplacian\\";//C:\\project\\images\\1\\goodImages\\sharpLaplacian\\";//"C:\\Users\\omri\\git\\Analysis\\AnalysisaAndEnhancementImage\\";//
	
	

	
	public DisplayImages(String path,int i) {
		this.imagedir = path;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100,100,722,533);
	setTitle("Display Images : Please Select an Image");
	
	
	// A label for displaying the pictures
	photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
	photographLabel.setHorizontalTextPosition(JLabel.CENTER);
	photographLabel.setHorizontalAlignment(JLabel.CENTER);
	photographLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	
	//panel.add(photographLabel);
	
	//panel.add(scroll);
	
	
	
	
	
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
	
	public DisplayImages(String path) {
		this.imagedir = path;
	SwingUtilities.invokeLater(new Runnable() {
	public void run() {
	DisplayImages app = new DisplayImages(imagedir,2);
	app.setVisible(true);
	}
	});
	}
	
	private SwingWorker<Void, ThumbnailAction> loadimages = new SwingWorker<Void, ThumbnailAction>() {
	
	
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
	
	
	private Icon displayPhoto;
	
	
	public ThumbnailAction(Icon photo, Icon thumb, String desc){
	displayPhoto = photo;
	
	// The short description becomes the tooltip of a button.
	putValue(SHORT_DESCRIPTION, desc);
	
	// The LARGE_ICON_KEY is the key for setting the
	// icon when an Action is applied to a button.
	putValue(LARGE_ICON_KEY, thumb);
	}
	
	
	public void actionPerformed(ActionEvent e) {
	photographLabel.setIcon(displayPhoto);
	setTitle("Icon Demo: " + getValue(SHORT_DESCRIPTION).toString());
	}
	}
	}
	
	
