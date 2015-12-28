package th.ac.kmutt.dsd.train.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class DocumentUtils {

	static Logger log = Logger.getLogger(DocumentUtils.class);
	static ResourceBundle configuration = ResourceBundle.getBundle("applicationConfig");
	
	// Configuration ----------------------------------------------------------- 

	public static String getBasePath() {

		ResourceBundle configuration = ResourceBundle.getBundle("applicationConfig");

		if (configuration.containsKey("FILE.PATH.DIR")) {

			return (String) configuration.getObject("FILE.PATH.DIR");

		}

		return null;

	}

	
	public static String getCreateFullPath(String studentID) throws Exception {

		return getBasePath() + File.separator +getTimeStamp()+ studentID;

	}

	public static String getTimeStamp() throws Exception {

		Date now = new Date();

		String directoryName = DateUtil.getDateString(now, "yyyyMMddHHmmssSSS", Locale.US);

		return directoryName;

	}


	public static void deleteFireHttpServer(String directory) throws IOException{
		
		
		File direct = new File(directory);

		if (direct.exists()) {

			FileUtils.deleteDirectory(direct);

		}

	}
	
}
