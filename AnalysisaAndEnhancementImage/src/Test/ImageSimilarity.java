package Test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Class.CUtils;
import GUI.classifyImages;

public class ImageSimilarity {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		CUtils.SetImagesDestinationPath("C:\\project\\testImageSimilarity\\");
		classifyImages c = new classifyImages(null);
		
		
		}
		


}
