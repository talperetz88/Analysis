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
import Class.Focus;
import Class.ImproveFocus;

public class TestImageSharping {
	@Test
	public void test() {
		Focus f= new Focus();
		ImproveFocus improve = new ImproveFocus();
		/*
		try {
			   BufferedImage img = ImageIO.read(new File("a.jpg"));
			} catch (IOException e) {
				System.err.println(e);
			}
			*/
		improve.blur("C:\\project\\foucs\\","C:\\project\\foucs\\blurImage\\","R.png",1,2);//CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName,ker,sigma
		improve.mask("C:\\project\\foucs\\","C:\\project\\foucs\\blurImage\\","R.png");//CUtils.GetImagesDestPath() + "goodImages\\", CUtils.GetImagesDestPath()+"ImproveFocus\\blurImage\\", fileName
		improve.improveFocus("C:\\project\\foucs\\","C:\\project\\foucs\\maskImages\\","R.png");//CUtils.GetImagesDestPath(), CUtils.GetImagesDestPath()+"ImproveFocus\\maskImages\\", fileName
		try {
			   BufferedImage imgI = ImageIO.read(new File("C:\\project\\foucs\\blurImage\\"+"blur_R.png"));
			} catch (IOException e) {
				System.err.println(e);
			}
		
		
		double sharp = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\foucs\\sharpImages\\"+"sharp_R.png");
		double r = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\foucs\\R.png");
		System.out.println(sharp+"    "+r);
		Assert.assertTrue(sharp>r);
			
	}

}
