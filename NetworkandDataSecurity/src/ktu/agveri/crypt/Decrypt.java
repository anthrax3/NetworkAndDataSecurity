package ktu.agveri.crypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Decrypt {

	Decrypt() {
		BigInteger p = new BigInteger(GetP());
		BigInteger q = new BigInteger(GetQ());
		String chipherText = ReadEncryptedText();
		BigInteger c = new BigInteger(chipherText);
		// if(c.toString().equals(chipherText))
		// System.out.println("Encryption text big integeter olarak alındı");
		ComputeRoots(c, p, q);
	}

	private String ReadEncryptedText() {
		String inFile = "/home/mcanv/Downloads/AgVeriFiles/Encrypted.txt";
		String line = ""; 
		String text = ""; 
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
				System.out.println("Şifreli dosya okundu");
				bReader.close();
				return text;
			} catch (IOException e) {
				System.out.println("dosya okunamadi!");
			}

		} catch (FileNotFoundException e) {
			System.out.println("dosya bulunadi!");
		}
		return text;
	}

	private void ComputeRoots(BigInteger c, BigInteger p, BigInteger q) {

		BigInteger n = p.multiply(q); // Public Key
		
		try {
			File outFile = new File("/home/mcanv/Downloads/AgVeriFiles/Decrypted.txt");
			PrintWriter writer = new PrintWriter(outFile);

			final BigInteger ONE = new BigInteger("1");
			final BigInteger FOUR = new BigInteger("4");

			BigInteger N = p.multiply(q);
			//x denk a^(p+1)/4%p
			BigInteger a1 = c.modPow(p.add(BigInteger.ONE).divide(FOUR), p);
			BigInteger a2 = p.subtract(a1);//p2
			BigInteger b1 = c.modPow(q.add(BigInteger.ONE).divide(FOUR), q);
			BigInteger b2 = q.subtract(b1);//q2

			
			BigInteger d1 = ChineseRemaninder(a1, b1, p, q);
			BigInteger d2 = ChineseRemaninder(a1, b2, p, q);
			BigInteger d3 = ChineseRemaninder(a2, b1, p, q);
			BigInteger d4 = ChineseRemaninder(a2, b2, p, q);

			String P1 = new String(d1.toByteArray(),Charset.forName("ascii"));
			String P2 = new String(d2.toByteArray(),Charset.forName("ascii"));
			String P3 = new String(d3.toByteArray(),Charset.forName("ascii"));
			String P4 = new String(d4.toByteArray(),Charset.forName("ascii"));
			
			// Açık metinler elde edildi
			if(P1.substring(P1.length()-2,P1.length())
					.equals(P1.substring(P1.length()-4,P1.length()-2))){
				writer.println(P1.substring(0,P1.length()-2));
			}else if (P2.substring(P2.length()-2,P2.length())
					.equals(P2.substring(P2.length()-4,P2.length()-2))) {
				writer.println(P2.substring(0,P2.length()-2));
			}else if (P3.substring(P3.length()-2,P3.length())
					.equals(P3.substring(P3.length()-4,P3.length()-2))) {
				writer.println(P3.substring(0,P3.length()-2));
			}else if (P4.substring(P4.length()-2,P4.length())
					.equals(P4.substring(P4.length()-4,P4.length()-2))) {
				writer.println(P4.substring(0,P4.length()-2));
				
			}
			
			// Close the writer
			writer.close();
			System.out.println("Deşifreleme tamamlandı!");

		} catch (FileNotFoundException e) {
			System.out.println("dosya oluşturalamadı!");
		}
	}

       private static BigInteger ChineseRemaninder(BigInteger a,BigInteger b,BigInteger p,BigInteger q){
		
		BigInteger M = p.multiply(q);
		BigInteger M1 = M.divide(p);
		BigInteger M2 = M.divide(q);
		BigInteger M1_inv = M1.modInverse(p);
		BigInteger M2_inv = M2.modInverse(q);
		
		BigInteger solition = (a.multiply(M1).multiply(M1_inv).add(b.multiply(M2).multiply(M2_inv))).mod(M);
		
		return solition;		
	}
	private String GetP() {
		String inFile = "Private.txt";
		String line = ""; 
		try {
			FileReader reader = new FileReader(inFile);
			BufferedReader bReader = new BufferedReader(reader);
			try {
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				System.out.println("Dosya okunamadı");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Dosya bulunamadı!");
		}
		return line;
	}

	private String GetQ() {
		String inFile = "Private.txt";
		String line = ""; 
		try {
			FileReader reader = new FileReader(inFile);
			BufferedReader bReader = new BufferedReader(reader);
			try {
				bReader.readLine();//ikinci satırı okumak için ilk satırı okudum
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				System.out.println("Dosya okunamadı!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Dosya bulunamadı!");
		}
		return line;
	}

	/*public static void main(String args[]) {
		Decrypt decryption = new Decrypt();
	}*/

}
