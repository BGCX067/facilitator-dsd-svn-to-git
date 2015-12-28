package th.ac.kmutt.dsd.train.utility;

import java.util.Collection;
import java.util.List;

public class CommonUtil {
	
	public static boolean isNotBlankValue(Object object) {
		
		if (object == null)
			return false;
		if (object instanceof Long) {
			if (object == null)
				return false;
		} else if (object instanceof String) {
			String objString = (String) object;
			if (objString == null || objString.length() == 0 || objString.trim().equals("") || objString.equalsIgnoreCase("null"))
				return false;
		} else if(object instanceof List) {
			List obList= (List) object;
			if (object == null || obList.size() == 0)
				return false;
		}
		return true;
	}

	public static boolean isBlankValue(Object object) {
		if (object == null)
			return true;
		if (object instanceof Long) {
			if (object == null)
				return true;
		} else if (object instanceof String) {
			String objString = (String) object;
			if (objString == null || objString.length() == 0 || objString.trim().equals("") || objString.equalsIgnoreCase("null"))
				return true;
		} else if(object instanceof List) {
			List obList= (List) object;
			if (object == null || obList.size() == 0)
				return true;
		}
		return false;
	}

}
