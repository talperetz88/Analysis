package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Class.Focus;

public class TestFocusFunctionMeasures {
	@Test
	public void test() {
		
		Focus f= new Focus();
		double orignal, blur;
		boolean bool =true;

		
		System.out.println("FocusMeasuresBasedOnImageStatisticsVariance grauscale");
		orignal = f.FocusMeasuresBasedOnImageStatisticsVariance("C:\\project\\focusfunctiontest\\orignal.png",1);
		blur = f.FocusMeasuresBasedOnImageStatisticsVariance("C:\\project\\focusfunctiontest\\blur.png",1);
		System.out.println("the orignal is : "+orignal+"the blur is :"+blur);
		if(orignal<blur)
			bool=false;

		System.out.println("FocusMeasuresBasedOnImageStatisticsVariance luminace");
		orignal = f.FocusMeasuresBasedOnImageStatisticsVariance("C:\\project\\focusfunctiontest\\orignal.png",2);
		blur = f.FocusMeasuresBasedOnImageStatisticsVariance("C:\\project\\focusfunctiontest\\blur.png",2);
		System.out.println("the orignal is : "+orignal+"the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
		
		System.out.println("FocusMeasuresBasedOnImageStatisticsNormalizedVariance grayscale");
		orignal = f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\project\\focusfunctiontest\\orignal.png",1);
		blur = f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\project\\focusfunctiontest\\blur.png",1);
		System.out.println("the orignal is : "+orignal+"the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
		
		
		System.out.println("FocusMeasuresBasedOnImageStatisticsNormalizedVariance luminace");
		orignal = f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\project\\focusfunctiontest\\orignal.png",2);
		blur = f.FocusMeasuresBasedOnImageStatisticsNormalizedVariance("C:\\project\\focusfunctiontest\\blur.png",2);
		System.out.println("the orignal is : "+orignal+"the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
		

		System.out.println("FunctionBasedOnDepthOfPeaksAndValleysA");
		orignal = f.FunctionBasedOnDepthOfPeaksAndValleysA("C:\\project\\focusfunctiontest\\orignal.png",120);
		blur = f.FunctionBasedOnDepthOfPeaksAndValleysA("C:\\project\\focusfunctiontest\\blur.png",120);
		System.out.println("the orignal is : "+orignal+"the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
	
		
		System.out.println("FunctionBasedOnDepthOfPeaksAndValleysB");
		orignal = f.FunctionBasedOnDepthOfPeaksAndValleysB("C:\\project\\focusfunctiontest\\orignal.png",120);
		blur = f.FunctionBasedOnDepthOfPeaksAndValleysB("C:\\project\\focusfunctiontest\\blur.png",120);
		System.out.println("the orignal is : "+orignal+" the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
			
		
		System.out.println("FunctionBasedOnDepthOfPeaksAndValleysC");
		orignal = f.FunctionBasedOnDepthOfPeaksAndValleysC("C:\\project\\focusfunctiontest\\orignal.png",120);
		blur = f.FunctionBasedOnDepthOfPeaksAndValleysC("C:\\project\\focusfunctiontest\\blur.png",120);
		System.out.println("the orignal is : "+orignal+" the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
		
		
		System.out.println("FocusMeasuresBasedOnImageDifferentiationA");
		orignal = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\focusfunctiontest\\orignal.png",(float) 20);
		blur = f.FocusMeasuresBasedOnImageDifferentiationA("C:\\project\\focusfunctiontest\\blur.png",(float) 20);
		System.out.println("the orignal is : "+orignal+" the blur is :"+blur);
		
		if(orignal<blur)
			bool=false;
		
		
		System.out.println("FocusMeasuresBasedOnImageDifferentiationB");
		orignal = f.FocusMeasuresBasedOnImageDifferentiationB("C:\\project\\focusfunctiontest\\orignal.png",(float) 9700);
		blur = f.FocusMeasuresBasedOnImageDifferentiationB("C:\\project\\focusfunctiontest\\blur.png",(float) 9700);
		System.out.println("the orignal is : "+orignal+" the blur is :"+blur);
		
		
		if(orignal<blur)
			bool=false;
		
		Assert.assertTrue(bool);
		
	}

}
