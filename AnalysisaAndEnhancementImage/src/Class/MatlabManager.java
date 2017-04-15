package Class;
import matlabcontrol.*;

public class MatlabManager {

	private static boolean m_bInitialized;
	private static MatlabProxy proxy;
	
	public static boolean Init()throws MatlabConnectionException, MatlabInvocationException
	{
		if(m_bInitialized)
			return m_bInitialized;
		try
		{
			// create proxy
			MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).build();
			MatlabProxyFactory factory = new MatlabProxyFactory(options);
			proxy = factory.getProxy();
			m_bInitialized = true;
		}
		catch(Exception ex)
		{
			m_bInitialized = false;
		}
		return m_bInitialized;
	}
	
	public static void Finish() throws MatlabConnectionException, MatlabInvocationException
	{
		try
		{
			m_bInitialized = false;
			proxy.exit();
		}
		catch(Exception ex)
		{
		
		}
	}
	
	public static Object[] ExecuteMatlabCode(String _filePath, String imageSourcePath, String imageDestPath)throws MatlabConnectionException, MatlabInvocationException
	{
		
		proxy.eval("addpath('" + _filePath + "')");
		
		return proxy.returningFeval("get_triangle_from_image",1, imageSourcePath, imageDestPath);
	}
	
	
}