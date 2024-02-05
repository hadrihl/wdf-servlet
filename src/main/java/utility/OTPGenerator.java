package utility;

import java.security.SecureRandom;

public class OTPGenerator {
	private static final int length = 6;
	
	public static String generate() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			sb.append(String.format("%01d", random.nextInt(10)));
		}
		
		return sb.toString();
	}
}
