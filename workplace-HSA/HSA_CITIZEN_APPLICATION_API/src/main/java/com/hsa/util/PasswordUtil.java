package com.hsa.util;
import java.util.Base64;


public class PasswordUtil {

	public static String encrypt(String input) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(input.getBytes());
	}

	public static String decrypt(String input) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(input);
		return new String(decodedBytes);
	}

}
