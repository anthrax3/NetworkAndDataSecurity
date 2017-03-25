package ktu.algorithm.agveri;

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
		String inFile = "Encrypted.txt"; // The name of the file to be read in
		String line = ""; // Store each line of the file here
		String text = ""; // Store the entire file's text here
		// Try to instantiate both readers
		try {
			FileReader reader = new FileReader(inFile); // Create a filereader
			BufferedReader bReader = new BufferedReader(reader);
			// Try to actually read from the file
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
				System.out.println("Successfully read encryptedtext from file!");
				bReader.close();
				return text;
			} catch (IOException e) {
				System.out.println("Error reading file!");
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found or does not exist!");
		}
		return text;
	}

	private void ComputeRoots(BigInteger c, BigInteger p, BigInteger q) {

		BigInteger[] roots; // Holds solution to yp*p + yq*q = 1
		roots = new BigInteger[2]; // Holds solution to yp*p + yq*q = 1
		roots = xGCD(p, q); // Solves yp*p + yq*q = 1
		BigInteger n = p.multiply(q); // Recalculate n instead of reading it
		int counter = 1; // Counter to track line written

		// Try opening/creating the files
		try {
			File outFile = new File("Decrypted.txt");
			PrintWriter writer = new PrintWriter(outFile);

			final BigInteger ONE = new BigInteger("1");
			final BigInteger FOUR = new BigInteger("4");

			BigInteger N = p.multiply(q);
			BigInteger m_p1 = c.modPow(p.add(BigInteger.ONE).divide(FOUR), p);
			BigInteger m_p2 = p.subtract(m_p1);
			BigInteger m_q1 = c.modPow(q.add(BigInteger.ONE).divide(FOUR), q);
			BigInteger m_q2 = q.subtract(m_q1);

			BigInteger[] ext = xGCD(p, q);
			BigInteger y_p = ext[0];
			BigInteger y_q = ext[1];

			// y_p*p*m_q + y_q*q*m_p (mod n)
			BigInteger d1 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p1)).mod(N);
			BigInteger d2 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p1)).mod(N);
			BigInteger d3 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p2)).mod(N);
			BigInteger d4 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p2)).mod(N);

			String dec1 = new String(d1.toByteArray(),Charset.forName("ascii"));
			String dec2 = new String(d1.toByteArray(),Charset.forName("ascii"));
			String dec3 = new String(d1.toByteArray(),Charset.forName("ascii"));
			String dec4 = new String(d1.toByteArray(),Charset.forName("ascii"));
			
			// Print all 4 values to the output file
			writer.println("Kokler : ");
			writer.println(dec1);// m_p1
			writer.println(dec2);// m_p2
			writer.println(dec3);// m_q1
			writer.println(dec4);// m_q2
			writer.println("");

			/*
			 * String msg = "Hello there!"; BigInteger bi = new
			 * BigInteger(msg.getBytes()); System.out.println(new
			 * String(bi.toByteArray())); // prints "Hello there!"
			 */
			// Increment the counter

			// Close the writer
			writer.close();
			System.out.println("Successfully decrypted the ciphertext!");

		} catch (FileNotFoundException e) {
			System.out.println("File not found or error creating file!");
		}
	}

	private static BigInteger[] xGCD(BigInteger p, BigInteger q) {
		BigInteger x = BigInteger.ZERO; // x = 0
		BigInteger px = BigInteger.ONE; // px = 1
		BigInteger y = BigInteger.ONE; // y = 1
		BigInteger py = BigInteger.ZERO; // py = 0

		// As long as q doesn't equal zero
		while (!q.equals(BigInteger.ZERO)) {
			// Compute quotient and remainder
			BigInteger[] qr = p.divideAndRemainder(q); // dive and compute
														// remainder
			BigInteger quotient = qr[0]; // quotient stored in qr[0]

			BigInteger temp = p; // Need to hold previous value for storage
			p = q; // swap
			q = qr[1]; // q takes on remainder

			temp = x; // swap
			x = px.subtract(quotient.multiply(x)); // compute next x
			px = temp; // swap

			temp = y; // swap
			y = py.subtract(quotient.multiply(y)); // compute next y
			py = temp; // swap
		}

		BigInteger[] roots; // Store values as roots
		roots = new BigInteger[2]; // Store values as roots
		roots[0] = px; // Indice 0 is yp
		roots[1] = py; // Indice 1 is yq

		return roots;
	}

	private String GetP() {
		String inFile = "Private.txt"; // The file must be named
		String line = ""; // Empty string to read into

		// Try to instantiate the readers
		try {
			FileReader reader = new FileReader(inFile);
			BufferedReader bReader = new BufferedReader(reader);

			// Try to read from the file
			try {
				// We only need to read one line
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				System.out.println("Error reading file!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found or does not exist!");
		}

		// Should never get here, keeps the compiler happy
		return line;
	}

	private String GetQ() {
		String inFile = "Private.txt"; // The file must be named
		String line = ""; // Empty string to read into

		// Try to instantiate the readers
		try {
			FileReader reader = new FileReader(inFile);
			BufferedReader bReader = new BufferedReader(reader);

			// Try to read from the file
			try {
				// Read in the second line
				bReader.readLine();
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				System.out.println("Error reading file!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found or does not exist!");
		}

		// Should never get here, keeps the compiler happy
		return line;
	}

	public static void main(String args[]) {
		Decrypt decryption = new Decrypt();
	}

}
