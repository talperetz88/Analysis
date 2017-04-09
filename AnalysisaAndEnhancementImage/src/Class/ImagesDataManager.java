
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ImagesDataManager 
{
	
	private static boolean m_bInitialized = false;
	public static List<ImageData> imagesDataList;
	
	public static boolean Init()
	{
		if(!m_bInitialized)
		{
			imagesDataList = new ArrayList<ImageData>();
		}
		
		return m_bInitialized;
	}

	
	public static boolean WriteJsonToFile(ImageData imageData, String destinationPath)
	{
		JSONObject imageDataObj = new JSONObject();  
		
        try 
        { 
        	imageDataObj.put("Name", imageData.name);  
        	imageDataObj.put("Width", imageData.width);
        	imageDataObj.put("Height", imageData.height);
        	imageDataObj.put("ImageMatrix", imageData.ByteArrayToString(imageData.imageMatrix));
        	imageDataObj.put("TriangleBordersMatrix", imageData.ByteArrayToString(imageData.triangleBordersMatrix));
        	imageDataObj.put("TriangleExist", imageData.isTriangleExist);
        	imageDataObj.put("TemFound", imageData.temFound);
        	imageDataObj.put("GranulomaFound", imageData.granulomaFound);
        	imageDataObj.put("TriangleEdges", imageData.triangleEdges.toString());
        	imageDataObj.put("colorImageMatrix", imageData.IntArrayToString(imageData.colorImageMatrix));
        	
    		
            // Writing to a file  
            File file=new File(destinationPath);  
            file.createNewFile();  
            FileWriter fileWriter = new FileWriter(file);  
           
            fileWriter.write(imageDataObj.toJSONString());  
            fileWriter.flush();  
            fileWriter.close();  
  
            return true;
            
        } 
        catch (Exception e) 
        {  
        	return false;  
        }  


	}

	public static ImageData ReadJsonfromFile(String filePath) throws Exception
	{
		JSONParser parser = new JSONParser();  
		ImageData imageInfo  = new ImageData();
		  
		try 
		{  
		   Object obj = parser.parse(new FileReader(filePath));  
		   JSONObject jsonObject = (JSONObject) obj;  
		  
		   imageInfo.name = (String) jsonObject.get("Name");  
		   imageInfo.width = (int) jsonObject.get("Width");  
		   imageInfo.height = (int) jsonObject.get("Height");  
		   imageInfo.imageMatrix = (byte[][]) jsonObject.get("ImageMatrix");
		   imageInfo.triangleBordersMatrix = (byte[][]) jsonObject.get("TriangleBordersMatrix");  
		   imageInfo.isTriangleExist = (boolean)jsonObject.get("TriangleExist");
		   imageInfo.temFound = (boolean)jsonObject.get("TemFound"); 
		   imageInfo.granulomaFound = (boolean)jsonObject.get("GranulomaFound"); 
		   imageInfo.triangleEdges = (TriangleEdges)jsonObject.get("TriangleEdges");
		   imageInfo.colorImageMatrix = (int[][])jsonObject.get("ColorImageMatrix");
		   
		   return imageInfo; 

		} 
		  
		catch (FileNotFoundException e) 
		{  
			return null; 
		} 
		catch (IOException e) 
		{  
			return null; 
		}  
		  
	}
	
}

