package com.auditorio.auditorio.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashUtil {

	public static String hash256(String palavra) {
		String salt = "devsen@i138";
		palavra = palavra + salt;
		String sha256 = Hashing.sha256().hashString(palavra,StandardCharsets.UTF_8 ).toString();
		
		return sha256;
	}
}
