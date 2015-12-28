package th.ac.kmutt.dsd.train.utility;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicationConfigurationUtils {
	
	private static Logger logger = LogManager.getLogger(ApplicationConfigurationUtils.class);

	private static Properties appConfiguration;
	private static Properties appMessage;
	
	public static String getAppMessage(String rcvParameter) {
		if(appMessage != null) {
			return appMessage.getProperty(rcvParameter);
		} 
		else {
			logger.error("Application Message is null");
			return "Application Message is null";
		}
	}
	
	public static String getAppConfiguration(String rcvParameter) {
		if(appConfiguration != null) {
			return appConfiguration.getProperty(rcvParameter);
		} 
		else {
			logger.error("Application Resource is null then return null");
			return null;
		}
	}
	
	public static Properties getAppMessage() {
		return appMessage;
	}

	public static void setAppMessage(Properties rcvAppMessage) {
		ApplicationConfigurationUtils.appMessage = rcvAppMessage;
	}
	
	public static Properties getAppConfiguration() {
		return appConfiguration;
	}

	public static void setAppConfiguration(Properties appConfig) {
		appConfiguration = appConfig;
	}
}