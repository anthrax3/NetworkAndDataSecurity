package ktu.proje.agveri;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryption {
	public String hash(String textToHash) {
		String digestText = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(textToHash.getBytes());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));

			digestText = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return digestText;
	}
	public static void main(String[] args) {
		Md5Encryption md5 = new Md5Encryption();
		//md5.hash("ornek");
		System.out.println(md5.hash("ornek"));
	}
}
