package ktu.agveri.crypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;

public  class Encrypt {

	Encrypt() {
		String text = ReadText(); 
		BigInteger n = new BigInteger(GetN()); 
		Encrypt(text, n);
	}

	public String ReadText() {
		String inFile = "Input.txt"; 
		String line = ""; 
		String text = ""; 
		
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);
			
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
				//System.out.println("Input.txt okunu!");
				bReader.close();
				return text;
			} catch (IOException e) {
				//System.out.println("Dosya okundu!");
			}

		} catch (FileNotFoundException e) {
			//System.out.println("Dosya bulunamadı!");
		}
                
		return text;
	}

	public String GetN() {
		String inFile = "Public.txt"; 
		String line = ""; 
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);

			try {
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				//System.out.println("Error reading file!");
			}
		} catch (FileNotFoundException e) {
			//System.out.println("File not found or does not exist!");
		}

		// Should never get here, keeps the compiler happy
		return line;
	}

	public void Encrypt(String text, BigInteger n) {
                Md5Encryption md5 = new Md5Encryption();
                text = md5.hash(text);
		// Try to create and write to the file
		try {
			File outFile = new File("Encrypted.txt"); 
                        PrintWriter writer = new PrintWriter(outFile); 	
			BigInteger message = new BigInteger(text.getBytes(Charset.forName("ascii")));
			// System.out.println(message);
			final BigInteger TWO = new BigInteger("2");
			try {
				if(!message.mod(n).equals(0)){
					BigInteger ciphertext = message.modPow(TWO, n);//ciphertext = message^2(mod n)
					writer.print(ciphertext);
				}	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Mesaj ile Public key aralarında asal degil");
			}

			writer.close();
			//System.out.println("Şifreleme başarılı!");
		} catch (FileNotFoundException e) {
			//System.out.print("Dosya oluşturulamadı!");
		}
	}

	/*public static void main(String args[]) {
		Encrypt encryption = new Encrypt();
	}*/

}
