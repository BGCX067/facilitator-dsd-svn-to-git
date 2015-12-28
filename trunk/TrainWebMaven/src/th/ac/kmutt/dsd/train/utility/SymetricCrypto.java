package th.ac.kmutt.dsd.train.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.Hex;

public class SymetricCrypto {
	

	private static Logger logger = Logger.getLogger(SymetricCrypto.class);
	
	private static String padString(String in) {
       int slen = (in.length() % 16);
       int i = (16 - slen);
       if ((i > 0) && (i < 16)) {
           StringBuffer buf = new StringBuffer(in.length() + i);
           buf.insert(0, in);
           for (i = (16 - slen); i > 0; i--) {
               buf.append(" ");
           }
           return buf.toString();
       } else {
           return in;
       }
	}
	
	public static String encrypt(String key,String plainText)  throws Exception {

 	   if(CommonUtil.isBlankValue(plainText)){
 	    	return "";
 	    }
 	   
 	   SecretKeySpec skeySpec = null;
	   Cipher cipher = null;
	   byte[] encrypted = null;
	   byte[] aesKey = null;
	   String encryptText = null;
	   String pass = null;
       
       try {
    	   
    	   pass = padString(key);
    	   aesKey = pass.getBytes();
	       skeySpec = new SecretKeySpec(aesKey, "AES");
	       cipher = Cipher.getInstance("AES");
	       cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	       encrypted = cipher.doFinal(plainText.getBytes());
	       encryptText = new String(Hex.encodeHex(encrypted));
	       logger.debug("plainText = "+ plainText);
	       logger.debug("encryptText = "+ encryptText);

		} catch (Exception e) {
			logger.error("ERROR : encrypt() :: ", e);
		}
		return encryptText;
   }
   
   public static String decrypt(String key,String chipherText)  throws Exception {
	   SecretKeySpec skeySpec = null;
	   Cipher cipher = null;
	   byte[] decrypted = null;
	   byte[] aesKey = null;
	   String decryptText = null;
	   String pass = null;
       
       try {
    	   pass = padString(key);
    	   aesKey = pass.getBytes();
	       skeySpec = new SecretKeySpec(aesKey, "AES");
	       cipher = Cipher.getInstance("AES");
	       cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	       decrypted = cipher.doFinal(Hex.decodeHex(chipherText.toCharArray()));
	       decryptText = new String(decrypted);
	       logger.debug("chipherText = "+ chipherText);
	       logger.debug("decryptText = "+ decryptText);

		} catch (Exception e) {
			logger.error("ERROR : decrypt() :: ", e);
		}
		return decryptText;
   }

   /*
   public static void main(String[] args) {
	   try {
		  //String aa = encrypt("AISeService","TH");
//		   String text= "mobileNo=0899010006&channel=Web12Call&onetimeFlg=Y&lang=th"; 
		   
		   String text= "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash3/q84/s720x720/581785_10152330355258312_1270588973_n.jpg";
		    String aa = encrypt("url",text);
		    String bb = decrypt("url",aa);
		    System.out.println("aa = "+aa);
		    System.out.println("bb = "+bb);
		    
//			   String text= "mobileNo=0890162226&channel=Widget&onetimeFlg=N&lang=th"; 
//			    String aa = encrypt("eServiceWidget",text);
//			    String bb = decrypt("eServiceWidget",aa);
//			    System.out.println("aa = "+aa);
//			    System.out.println("bb = "+bb);

	   } catch (Exception e) {
			// TODO: handle exception
	   }
   }  
*/

}
