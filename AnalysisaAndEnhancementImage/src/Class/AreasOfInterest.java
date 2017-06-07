package Class;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

public class AreasOfInterest  {
	
	static int pixelsFactor;
	 List<Point> upperEdge ; //area 3 in the book ,
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> rightEdge2; //area 4 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> leftEdge2; //area 5 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom left vertex and the fourth is the bottom right vertex
	 */
	 List<Point> triangleArea; //area 6 in the book 
	/*
	 first argument is the upper left vertex of the rectangle second is the upper right vertex 
	 Third is the bottom  vertex 
	 */
	 List<Point> leftSide; //area 7 in the book 
	/*
	 the first argument is the upper vertex the second vertex is the bottom vertex and 
	 the third vertex is the middle  vertex 
	 */
	 List<Point> rightSide; //area 8 in the book 
	/*
	 the first argument is the upper vertex the second vertex is the bottom vertex and 
	 the third vertex is the middle  vertex 
	 */
	
	public AreasOfInterest() { 
		AreasOfInterest.pixelsFactor=10;
		upperEdge = new ArrayList<Point>(); 
		rightEdge2 = new ArrayList<Point>(); 
		leftEdge2 = new ArrayList<Point>(); 
	}
	
	public static BufferedImage Area1(BufferedImage image, int size,TriangleEdges edges) {
        int w = image.getWidth();
        int h = image.getHeight();
        int x=	edges.leftEdge.x;
        int y= edges.leftEdge.y;
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Double((x-90),(y-90), size,size));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
	
	public static BufferedImage Area2(BufferedImage image, int size,TriangleEdges edges ) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
        int x=	edges.rightEdge.x;
        int y= edges.rightEdge.y;
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Double((x-40),(y-80), size,size));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
	
	/*
	 * function that puts the area 3 (page 17 on the book) the user will give the rectangle height 
	 */
	public static BufferedImage Area3(BufferedImage image,TriangleEdges edges,int height){  
		
		/*
		Point p1 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y-height); 
		upperEdge.add(p1);
		Point p2 = new Point(edges.rightEdge.x+this.pixelsFactor,edges.leftEdge.y-height); //need to check the logic @@!!!!! if it needs to be plus or minus 
		upperEdge.add(p2);
		Point p3 = new Point(edges.leftEdge.x-this.pixelsFactor,edges.leftEdge.y+this.pixelsFactor);
		upperEdge.add(p3);
		Point p4 = new Point(edges.rightEdge.x+this.pixelsFactor,edges.leftEdge.y+this.pixelsFactor);
		upperEdge.add(p4);
		*/
		
        int [] x = new int [4];
        int [] y = new int [4];
        
        x[0]=edges.leftEdge.x-5*AreasOfInterest.pixelsFactor;
        x[1]=edges.rightEdge.x+5*AreasOfInterest.pixelsFactor;
        x[3]=edges.leftEdge.x-5*AreasOfInterest.pixelsFactor;
        x[2]=edges.rightEdge.x+5*AreasOfInterest.pixelsFactor;
        
        y[0]=edges.leftEdge.y+10;
        y[1]=edges.leftEdge.y+10;
        y[3]=(int) (edges.leftEdge.y-(Math.round(2.5*height)));
        y[2]=(int) (edges.leftEdge.y-Math.round(2.5*height));
      
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
		Graphics2D g2 = output.createGraphics();

		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		//System.out.println((edges.leftEdge.x-AreasOfInterest.pixelsFactor));
		g2.fill(new Polygon(x,y, 4));
		//g2.fill(new Rectangle((edges.leftEdge.x-AreasOfInterest.pixelsFactor),(edges.leftEdge.y-3*AreasOfInterest.pixelsFactor),(edges.rightEdge.x-edges.leftEdge.x+AreasOfInterest.pixelsFactor*3) ,height));
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);

		g2.dispose();		 
		
		 return output;
		
	}
	
	/*
	 * function that puts the area 4 (page 17 on the book) the user will give the rectangle height and width
	 */
	
	public static BufferedImage Area4(BufferedImage image,TriangleEdges edges){  
		
		int width=(edges.rightEdge.x-edges.leftEdge.x);
		/*
		Point p1 = new Point(edges.rightEdge.x-AreasOfInterest.pixelsFactor,edges.rightEdge.y-AreasOfInterest.pixelsFactor);
		rightEdge2.add(p1);
		Point p2 = new Point(edges.rightEdge.x+width,edges.rightEdge.y);
		rightEdge2.add(p2);
		Point p3 = new Point(edges.bottomEdge.x-AreasOfInterest.pixelsFactor,edges.bottomEdge.y+3*AreasOfInterest.pixelsFactor);  
		rightEdge2.add(p3);
		Point p4 = new Point(edges.bottomEdge.x+width,edges.bottomEdge.y+3*AreasOfInterest.pixelsFactor); 
		rightEdge2.add(p4);
		*/
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int [] x = new int [4];
        int [] y = new int [4];
        x[0]=edges.rightEdge.x;//-AreasOfInterest.pixelsFactor;
        x[1]=edges.rightEdge.x+width;
        x[2]=edges.bottomEdge.x+width;
        x[3]=edges.bottomEdge.x;//-AreasOfInterest.pixelsFactor;

        
        y[0]=edges.rightEdge.y-2*AreasOfInterest.pixelsFactor;
        y[1]=edges.rightEdge.y;
        y[2]=edges.bottomEdge.y+5*AreasOfInterest.pixelsFactor;
        y[3]=edges.bottomEdge.y+5*AreasOfInterest.pixelsFactor;

        	
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        g2.fill(new Polygon(x,y, 4));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
	}
	
	
	/*
	 * function that puts the area 5 (page 17 on the book) the user will give the rectangle height and width
	 */
	
	public static BufferedImage Area5(BufferedImage image,TriangleEdges edges){  
		
		
		int width=65;
		/*
		Point p1 = new Point(edges.leftEdge.x-AreasOfInterest.pixelsFactor,edges.leftEdge.y-AreasOfInterest.pixelsFactor);
		leftEdge2.add(p1);
		Point p2 = new Point(edges.leftEdge.x-width,edges.leftEdge.y);
		leftEdge2.add(p2);
		Point p3 = new Point(edges.bottomEdge.x-AreasOfInterest.pixelsFactor,edges.bottomEdge.y+3*AreasOfInterest.pixelsFactor);  
		leftEdge2.add(p3);
		Point p4 = new Point(edges.bottomEdge.x-width,edges.bottomEdge.y+3*AreasOfInterest.pixelsFactor); 
		leftEdge2.add(p4);
		*/
		width=(edges.rightEdge.x-edges.leftEdge.x);
		int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int [] x = new int [4];
        int [] y = new int [4];
        x[0]=edges.leftEdge.x+AreasOfInterest.pixelsFactor;
        x[1]=edges.leftEdge.x-width;
        x[2]=edges.bottomEdge.x-width;
        x[3]=edges.bottomEdge.x+AreasOfInterest.pixelsFactor;


        
        y[0]=edges.leftEdge.y-AreasOfInterest.pixelsFactor;
        y[1]=edges.leftEdge.y;
        y[2]=edges.bottomEdge.y+5*AreasOfInterest.pixelsFactor;
        y[3]=edges.bottomEdge.y+5*AreasOfInterest.pixelsFactor;


        	
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        g2.fill(new Polygon(x,y, 4));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
	}
	
	public static BufferedImage Area6(BufferedImage image,TriangleEdges edges ){  
		
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int [] x = new int [3];
        int [] y = new int [3];
        x[0]=edges.rightEdge.x+20;
        x[1]=edges.leftEdge.x-20;
        x[2]=edges.bottomEdge.x+10;
        
        y[0]=edges.rightEdge.y-20;
        y[1]=edges.leftEdge.y-20;
        y[2]=edges.bottomEdge.y+35;
        	
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        g2.fill(new Polygon(x,y, 3));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
		

	}
	
	public static BufferedImage Area7(BufferedImage image,TriangleEdges edges ){  
		
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int [] x = new int [4];
        int [] y = new int [4];
        int hieght = edges.bottomEdge.y-edges.rightEdge.y;
        x[0]=edges.rightEdge.x;//+40
        x[1]=hieght*3;
        x[2]=hieght*3;
        x[3]=edges.bottomEdge.x;//-40
        
        y[0]=edges.rightEdge.y-20;
        y[1]=(int)(hieght*0.5*0.5)+edges.rightEdge.y;
        y[2]=(int)(hieght*0.7)+edges.rightEdge.y;
        y[3]=edges.bottomEdge.y+55;
        //System.out.println(" y1: "+y[1]+" y2 : "+y[2]);
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        g2.fill(new Polygon(x,y, 4));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();
        //System.out.println(x[0]+ " "+y[0]);
        return output;
		

	}
	
	public static BufferedImage Area8(BufferedImage image,TriangleEdges edges ){  
		
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int [] x = new int [4];
        int [] y = new int [4];
        int hieght = edges.bottomEdge.y-edges.leftEdge.y;
        if(hieght>130)
        	hieght=130;
        x[0]=edges.leftEdge.x;
        x[1]=(int)(hieght/10);
        x[2]=(int)(hieght/10);
        x[3]=edges.bottomEdge.x;
        
        y[0]=edges.leftEdge.y-20;
        y[1]=(int)(hieght*1.5);
        y[2]=(int)(hieght*2);
        y[3]=edges.bottomEdge.y+65;
        	
        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        g2.fill(new Polygon(x,y, 4));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();
        
        
        return output;
		

	}



}
