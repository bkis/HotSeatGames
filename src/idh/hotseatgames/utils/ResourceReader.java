package idh.hotseatgames.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Quick helper class to read character based resource (file)
 * located in the same package as a given class 
 * 
 * @author bkis
 * 
 */
public class ResourceReader {
	
	private static final String DEFAULT_RESOURCE_PATH = 
			"/idh/hotseatgames/resources/";
	
	/**
	 * Reads character based resource (file)
	 * located in the same package as the given class 
	 * @param resourceName
	 * @param relativeToClass
	 * @return String contents of the resource file
	 */
	public static String readResource(
			String resourceName,
			Class<?> relativeToClass) {
		return read(relativeToClass.getResourceAsStream(resourceName));
	}
	
	/**
	 * Reads character based resource (file)
	 * located in the /idh/hotseatgames/resources/ package
	 * @param resourceName
	 * @return String contents of the resource file
	 */
	public static String readResource(
			String resourceName) {
		return read(ResourceReader.class.getResourceAsStream(
				DEFAULT_RESOURCE_PATH + resourceName));
	}
	
	
	private static String read(InputStream is) {
		BufferedInputStream bis = new BufferedInputStream(is);
		String content = null;
		
		try {
			content = new String(bis.readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return content;
	}

}
