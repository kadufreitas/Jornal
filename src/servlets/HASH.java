package servlets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HASH {
	public HASH(){
		
	}
	
	public String get_SHA_512_SecurePassword(String str){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());
		    byte byteData[] = md.digest();

		    //convert the byte to hex format method 1
		    StringBuffer hashCodeBuffer = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++) {
		        hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    }
		    return hashCodeBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
