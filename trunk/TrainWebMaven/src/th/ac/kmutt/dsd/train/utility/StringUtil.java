/*
 * Created on 25 ม.ค. 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package th.ac.kmutt.dsd.train.utility;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StringUtil {
	
	public static final String MONEY_FORMAT = "#,##0.00";	
	public static final String INTEGER_FORMAT = "#,##0";
	public static final String DECIMAL_FORMAT = "#,##0.##";
	
	public static String getIntegerNumberMoneyFormatted(Double number) {
		if(number == null){
			return "0.00";
		}
		try {
			NumberFormat numberFormat = new DecimalFormat(INTEGER_FORMAT);
			return numberFormat.format(number);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "0.00";
	}
	
	public static String getIntegerNumberPercentFormatted(Double number) {
		if(number == null){
			return "0";
		}
		try {
			NumberFormat numberFormat = new DecimalFormat(INTEGER_FORMAT);
			return numberFormat.format(number);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static String getDoubleNumberMoneyFormatted(Double number) {
		if(number == null){
			return "0.00";
		}
		try {
			NumberFormat numberFormat = new DecimalFormat(MONEY_FORMAT);
			return numberFormat.format(number);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "0.00";
	}
	
	public static String getFloatNumberMoneyFormatted(Float number) {
		if(number == null){
			return "0.00";
		}
		try {
			NumberFormat numberFormat = new DecimalFormat(MONEY_FORMAT);
			return numberFormat.format(number);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "0.00";
	}
	
	public static String getFloatDecimalFormatted(Float number) {
		if(number == null){
			return "0.00";
		}
		try {
			NumberFormat numberFormat = new DecimalFormat(DECIMAL_FORMAT);
			return numberFormat.format(number);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "0.00";
	}
	
	public static String getString(Object theObject){
		String stringReturn = null;
		boolean isConvert = false;
		boolean isNA = false;
		int mid = 0;
		int leftBranch = 0;
		int rightBranch = 0;
		
		if(theObject != null){
			stringReturn = theObject.toString().trim();
			byte stringObj[]=null;
			try {
				stringObj = stringReturn.getBytes("ISO8859_1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mid = stringObj.length/2;
			for(int i=0; i<mid; i++){
				leftBranch = i+1;
				rightBranch = mid+leftBranch;
				if(stringObj[leftBranch-1] < 0){
					stringObj[leftBranch-1] *= -1;
					if(isConvert == false && isNA == false){
						isConvert = true;
					}
//					break;
				}
				if(stringObj[rightBranch-1] < 0 && isNA == false){
					stringObj[rightBranch-1] *= -1;					
					if(isConvert == false){
						isConvert = true;
					}
//					break;
				}

				if(stringObj[leftBranch-1] == 63){
					isNA = true;
				}
				if(stringObj[rightBranch-1] == 63){
					isNA = true;
				}
			}
			if((rightBranch+1) < stringObj.length){
				if(stringObj[rightBranch] < 0){
					stringObj[rightBranch] *= -1;
					if(isConvert == false && isNA == false){
						isConvert = true;
					}
				}
				if(stringObj[rightBranch] == 63){
					isNA = true;
				}
			}
			if(isConvert && !isNA){
				try {
					stringReturn = new String(stringObj,"TIS-620");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			stringReturn="";
		}
		
		return stringReturn;
	}
	
	public static String getStringWithoutCharector(String theString,char theCharector){
		String stringReturn = "";

		for(int i=0; i<theString.length(); i++){
			if(theString.charAt(i) != theCharector){
				stringReturn += ""+theString.charAt(i);
			}
		}

		return stringReturn;
	}
	
	public static String getStringDecimal(Double theDouble,int theGroupSize, int theFractionSize){
		String stringReturn = "";
		if(theDouble != null){
			DecimalFormat decimalFormat = new DecimalFormat();
			decimalFormat.setGroupingSize(theGroupSize);
			decimalFormat.setMaximumFractionDigits(theFractionSize);
			decimalFormat.setMinimumFractionDigits(theFractionSize);
			stringReturn = decimalFormat.format(theDouble.doubleValue());
		} else {
			stringReturn = "0.00";
		}
		return stringReturn;
	}
	
	public static String replaceAll(String source, String oldString, String newString) {
		StringBuffer buffer 	  = null;
		StringBuffer returnBuffer = null;
		if(source != null){
		
			buffer = new StringBuffer(source.length());
			int pos = -1;
			int markPos = 0;
			while ((pos = source.indexOf(oldString, markPos)) != -1) {
				String token = source.substring(markPos, pos);
				buffer.append(token);
				buffer.append(newString);
				markPos = pos + oldString.length();
			}
			buffer.append(source.substring(markPos, source.length()));
			returnBuffer = buffer;
		}else{
			returnBuffer = new StringBuffer();
		}
		return returnBuffer.toString();
	}
	public static ArrayList split(String input, char delimiter) {
		ArrayList data = new ArrayList();
		int size = input.length();
		if (size == 0)
			return null;
		int markPos = 0;
		for (int i = 0; i < size; i++) {
			char c = input.charAt(i);
			if (c == delimiter) {
				if (i - markPos < 1) {
					data.add("");
				} else {
					String token = input.substring(markPos, i);
					if (token.equals(String.valueOf(delimiter)))
						data.add("");
					else
						data.add(token);
				}
				markPos = i;
			} else {
				if (i - 1 >= 0 && input.charAt(i - 1) == delimiter)
					markPos = i;
			}
		}
		if (input.charAt(size - 1) == delimiter)
			data.add("");
		else
			data.add(input.substring(markPos, size));
		return data;
	}
	public static void main(String[] args) {
		System.out.println("Test >>"+getIntegerNumberMoneyFormatted(new Double("45.00")));
	}
}
