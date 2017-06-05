package Test;




import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import Class.CUtils;

public class NumOfImages {



	@Test
	public void test() {
		double videoTime = 114;//time in second
		String path = "C:\\project\\images\\check\\";
		int frameRate = 30;
		int cutFrameRate = 10;
		File folder = new File(path);
		BufferedImage image = null;
		//Histogram his = new Histogram(image);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		Assert.assertTrue(((listOfFiles.length -2) < (((videoTime * frameRate)/cutFrameRate)+10) || (listOfFiles.length -2) > (((videoTime * frameRate)/cutFrameRate)-10)));
		
	}

}
