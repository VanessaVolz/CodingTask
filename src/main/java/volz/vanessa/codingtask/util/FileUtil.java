package volz.vanessa.codingtask.util;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final String TYPE = "text/plain";	

	private FileUtil() {
		super();
	}

	/**
	 * Validates if MultipartFile has text content type.
	 * 
	 * @param file MultipartFile
	 * @return boolean indicating whether MultipartFile content type is equals to "text/plain" or not
	 */
	public static boolean isTextFormat(MultipartFile file) {
		return TYPE.equals(file.getContentType());
    }
	
}
