package th.ac.kmutt.dsd.train.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class DownloadFileServlet  extends HttpServlet {
	
	private static Logger log = Logger.getLogger(DownloadFileServlet.class);
	final ResourceBundle resource = ResourceBundle.getBundle("applicationConfig");
	
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_JPEG = "image/jpeg";
	private static final String CONTENT_TYPE_GIF = "image/gif";
	private static final String CONTENT_TYPE_BMP = "image/bmp";
	private static final String CONTENT_TYPE_TIFF = "image/tiff";
	private static final String CONTENT_TYPE_PNG = "image/png";
	private static final String CONTENT_TYPE_SWF = "application/x-shockwave-flash";
	private static final String CONTENT_TYPE_PDF = "application/pdf";
	private static final String MASTERFILE_PATH  = "master.file.path";
	
	public DownloadFileServlet() {
		super();
	}
	
	public void init() throws ServletException {
		super.init();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  HttpServletRequest getRequest =  (HttpServletRequest)request;
	   
		try {
			String strFileName = request.getParameter("fileName");
			String strPath = "";
			
		    strPath = resource.getString(MASTERFILE_PATH);
			
		    log.info("strPath "+strPath);
		    
			String strFilePath = strPath +File.separator+strFileName;
			log.info("strFilePath = " + strFilePath);
			
			if(strFilePath != null) {
				int dotIndex = strFilePath.lastIndexOf('.');
				String contentType = null;
				String fileExt = strFilePath.substring(dotIndex+1).toUpperCase();
				
				if (fileExt.equals("GIF")) {
					contentType = CONTENT_TYPE_GIF;
				} else if (fileExt.equals("BMP")) {
					contentType = CONTENT_TYPE_BMP;
				} else if (fileExt.equals("JPG") || fileExt.equals("JPEG")) {
					contentType = CONTENT_TYPE_JPEG;
				} else  if (fileExt.equalsIgnoreCase("PNG")) {
					contentType = CONTENT_TYPE_PNG;
				} else  if (fileExt.equalsIgnoreCase("PDF")) {
					contentType = CONTENT_TYPE_PDF;
				} else  if (fileExt.equalsIgnoreCase("TIFF")) {
					contentType = CONTENT_TYPE_TIFF;
				} else  if (fileExt.equalsIgnoreCase("SWF")) {
					contentType = CONTENT_TYPE_SWF;
				}
				
				response.setContentType(contentType);
				ServletOutputStream out = response.getOutputStream();
				FileInputStream in = null;
				
				try {
					in = new FileInputStream(strFilePath);
					int buff = in.read();
					while (buff != -1) { // -1 as EOF
						out.write(buff);
						buff = in.read();
					}
					
					out.flush();
				} catch (Exception e) {
					
					log.error("Load Image Fail", e);
				} finally {
					if(out != null) out.close();
					out = null;
					if(in != null) in.close();
					in = null; 
				}
			}
		} catch(Exception exception) {
			log.debug("load image fail", exception);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
 }
