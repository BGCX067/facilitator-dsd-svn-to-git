package th.ac.kmutt.dsd.helper;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class StorageHelper {

	public static final String DIR = (String)ResourceBundle.getBundle("applicationConfig").getObject("FILE.PATH");
	public static final String TMPDIR = (String)ResourceBundle.getBundle("applicationConfig").getObject("FILE.PATH");

	public static String getBaseUrl(String dir) throws MalformedURLException{
		HttpServletRequest reques = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return reques.getSession().getServletContext().getContextPath() + dir;
	}
	
	public static String getBasePath(String dir) throws MalformedURLException{
		
		HttpServletRequest reques = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String path = reques.getSession().getServletContext().getRealPath(dir);
		
		java.net.URL url = reques.getSession().getServletContext().getResource(dir);
		File file = new File(path);

		if (path == null || "null".equals(path)) {
			path = url.getPath();
		}

		if (!file.exists()) {
			file.mkdir();
		}
		
		return path;
	}
	
}
