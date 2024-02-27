package com.management.app;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class Test {

	public static void main(String[] args) {
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@")).toCharArray();
		String random = RandomStringUtils.random( 6, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
		System.out.println(random);

	}
}
