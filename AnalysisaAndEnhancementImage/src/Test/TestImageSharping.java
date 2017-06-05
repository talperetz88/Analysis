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
		boolean bool = true ;
		ImproveFocus improve = new ImproveFocus();
		improve.blur("C:\\project\\foucs\\","C:\\project\\foucs\\blurImage\\","R.png",1,2);
		improve.mask("C:\\project\\foucs\\","C:\\project\\foucs\\blurImage\\","R.png");
		improve.improveFocus("C:\\project\\foucs\\","C:\\project\\foucs\\maskImages\\","R.png");
		double sharp = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\foucs\\sharpImages\\"+"sharp_R.png",(int) 20);
		double  orignal = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\foucs\\R.png",(int) 20);
		
		if(sharp<orignal)
			bool = false;
		
		improve.laplacianMask("C:\\project\\foucs\\", "C:\\project\\foucs\\laplacianMask\\", "R.png", 1);
		improve.improveFocusLaplacian("C:\\project\\foucs\\", "C:\\project\\foucs\\laplacianMask\\", "R.png");
		sharp = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\foucs\\sharpLaplacian\\"+"sharp_R.png",(int) 20);
		
		if(sharp<orignal)
			bool = false;	
		
		Assert.assertTrue(bool);
	}

}
