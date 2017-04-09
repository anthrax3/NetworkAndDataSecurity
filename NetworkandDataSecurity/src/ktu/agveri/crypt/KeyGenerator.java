package ktu.agveri.crypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
	private BigInteger q; 
	private BigInteger p; 
	public BigInteger n; 

	KeyGenerator() {
		p = GeneratePrime(); // Generate p
		System.out.println("Private P key uzunlugu :  " + p.toString().length());

		q = GeneratePrime(); // Generate q
		System.out.println("Private Q key uzunlugu : " + q.toString().length());

		n = p.multiply(q); // Generate n
		System.out.println("Public N key uzunlugu: " + n.toString().length());

		CreatePublicKeyFile(n); 
		CreatePrivateKeyFile(p, q); 
	}

	private boolean Is3mod4(BigInteger prime) {
		final BigInteger ZERO = new BigInteger("0"); 
		final BigInteger THREE = new BigInteger("3"); 
		final BigInteger FOUR = new BigInteger("4"); 

		// 3mod4 degerini saglıyor mu 
		if ((((prime.subtract(THREE)).mod(FOUR)).equals(ZERO))) {
			return true;
		}
		return false;
	}

	private BigInteger GeneratePrime() {

		final int LENGTH = 1024; 
		Random rnd = new Random(); 
		BigInteger prime = new BigInteger("13"); 
		while (!(Is3mod4(prime))) {
			StringBuilder sb = new StringBuilder(LENGTH); 
			for (int i = 0; i < LENGTH; i++) {
				if (i == 0) {
					sb.append((char) ('0' + (rnd.nextInt(9) + 1)));
				}
				else {
					sb.append((char) ('0' + (rnd.nextInt(10))));
				}
			}

			String number = sb.toString(); 
			prime = new BigInteger(number); 
			prime = prime.nextProbablePrime();
		}
		return prime;
	}

	public void CreatePublicKeyFile(BigInteger n) {
		try {
			File outFile = new File("Public.txt"); 
			PrintWriter writer = new PrintWriter(outFile); 
			writer.println(n);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dosya olusturulamadı");
		}
	}

	public void CreatePrivateKeyFile(BigInteger p, BigInteger q) {
		try {
			File outFile = new File("Private.txt");
			PrintWriter writer = new PrintWriter(outFile); 	
			writer.println(p);
			writer.println(q);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dosya olusturulamadı!");
		}
	}

	public static void main(String args[]) {
		KeyGenerator generateKeys = new KeyGenerator();
	}

}
