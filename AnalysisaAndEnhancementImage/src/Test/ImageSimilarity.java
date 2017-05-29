package Test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Class.CUtils;
import GUI.classifyImages;

public class ImageSimilarity {
	
	@Test
	public void test() {
		CUtils.SetImagesDestinationPath("C:\\project\\testImageSimilarity\\");
		classifyImages c = new classifyImages(null);
		/*c.MadFunc();
		c.MesFunc();
		c.intersectionFunc();
		c.bhattFunc();
		c.chiSquareFunc();*/
		boolean bool = true;
		boolean temp;
		File folder = new File(CUtils.GetImagesDestPath() +"Histogram\\Correlation\\RGB\\1");
		String name=".png";
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length -1; i++){
			String n = (i+1)+name;
			temp=n.equals(listOfFiles[i].getName());
			if (temp==false)
				bool = false;
		Assert.assertTrue(bool);

		}
	}
}
		



