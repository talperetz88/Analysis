package Class;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

public class Matlab {

	public static void ExecuteMatlabCode(String fileName)
	{
		try
    	{
    		//Execute MATLAB code  
    		MatlabManager.Init();
    	
    		//MatlabManager.ExecuteMatlabCode(CUtils.GetMatlabCodePath(),"C:\\Users\\talpe\\git\\AnalysisaAndEnhancementImage\\AnalysisaAndEnhancementImage\\"+ fileName, "C:\\Users\\talpe\\git\\AnalysisaAndEnhancementImage\\AnalysisaAndEnhancementImage\\"+ "MatlabResult"+fileName);
    		MatlabManager.ExecuteMatlabCode(CUtils.GetMatlabCodePath(),"C:\\Users\\talpe\\Desktop\\img\\img\\2\\" + fileName,"C:\\Users\\talpe\\Desktop\\img\\img\\2\\res\\" +"MatlabRes"+ fileName);
    		MatlabManager.Finish();
        
    	}
    	catch (MatlabConnectionException | MatlabInvocationException ex) 
    	{
		
    		System.out.println("Error occured in MatlabManager. Exception message - " + ex.getMessage());
    	}
	}
}
