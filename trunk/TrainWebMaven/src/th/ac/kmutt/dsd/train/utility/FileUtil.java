package th.ac.kmutt.dsd.train.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.springframework.core.io.ClassPathResource;



public abstract class FileUtil {
	
	final static double IMAGE_SCALER = 3.1389;
	  
	private FileUtil() {}
	
	public static String getSuffix(File file) {
		return getSuffix(file.getName());
	}
	
	public static String getSuffix(String fileName) {
		int ix = fileName.lastIndexOf(".");
		return fileName.substring(ix + 1);
	}
	
	public static String getFilename(File file) {
		String name = file.getName();
		int ix = name.lastIndexOf(".");
		return name.substring(0, ix);
	}
	
	public static String getNameFromFileName(String fileName){
		int ix = fileName.lastIndexOf(".");
		return fileName.substring(0, ix);
	}
	
	public static void writeFile(File file, String output) {
		try {
			
	    	PrintWriter pw = new PrintWriter(file);
	    	pw.write(output);
	    	pw.close();
	    	
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static File loadFileClasspath(String fileName) {
		try {
			
			return new ClassPathResource(fileName).getFile();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String slurp(File file, boolean includeCRLF) {
		List<String> lines = load(file);
		StringBuilder sb = new StringBuilder();
		for (String line: lines) {
			sb.append(line);
			if (includeCRLF) {
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	
	public static final List<String> load(File file) {
		try {

			List<String> lines = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				String line = (br.readLine());
				if (line == null) {
					break;
				}
				lines.add(line);
			}
			br.close();
			return lines;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static  List<String> getFileNameList(String path) throws Exception {
		List<String> fileList = new LinkedList<String>();
		try {
			File dir = new File(path);
			String[] children = dir.list();
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return !name.startsWith(".");
				}
			};

			children = dir.list(filter);
			File[] files = dir.listFiles();
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
					return file.isDirectory();
				}
			};
			files = dir.listFiles(fileFilter);
			if (children.length > 0) {
				for (int i = 0; i < children.length; i++) {
					String readFileStr = children[i].toString();
					if (readFileStr != null && readFileStr.trim().length() > 0) {
							fileList.add(readFileStr);
					}

				}
			}
		} catch (Exception e) {
			

		}

		return fileList;

	}
	
	public static void createPathDirectories (String directoryPath){
		
		File file = new File(directoryPath);
		boolean exist = file.exists();
		
		try {
			if(!exist){
				file.mkdirs();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    // The directory is now empty so delete it
	    return dir.delete();
	}
	
}
