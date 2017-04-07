package ktu.algorithm.agveri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Encrypt {

	Encrypt() {
		String text = ReadText(); // Read the text file into a string
		BigInteger n = new BigInteger(GetN()); // Get N from the Public.txt file
		Encrypt(text, n); // Encrypt input.txt
	}

	private String ReadText() {
		String inFile = "Input.txt"; // The name of the file to be read in
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
				System.out.println("Successfully read plaintext from file!");
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

	private String GetN() {
		String inFile = "Public.txt"; // The file must be named
		String line = ""; // Empty string to read into

		// Try to instantiate the readers
		try {
			FileReader reader = new FileReader(inFile); // Create a file reader
														// for the input
			BufferedReader bReader = new BufferedReader(reader);

			// Try to read from the file
			try {
				// We only need to read one line
				line = bReader.readLine();
				bReader.close();
				return line;

				// If there's an issue read from the file
				// we must report it to the user
			} catch (IOException e) {
				System.out.println("Error reading file!");
			}

			// If the file is not found we must
			// report it to the user
		} catch (FileNotFoundException e) {
			System.out.println("File not found or does not exist!");
		}

		// Should never get here, keeps the compiler happy
		return line;
	}

	public void Encrypt(String text, BigInteger n) {
		int pos = 1; // Position of the char we are at (non-zero based)
		String toEncrypt = ""; // Blank string to hold 50 chars

		// Try to create and write to the file
		try {
			File outFile = new File("Encrypted.txt"); // Create the outFile to
														// hold encryption
			PrintWriter writer = new PrintWriter(outFile); // New writer to
															// write to outFile

			BigInteger message = new BigInteger(text.getBytes(Charset.forName("ascii")));
			// System.out.println(message);
			final BigInteger TWO = new BigInteger("2");
			BigInteger ciphertext = message.modPow(TWO, n);

			writer.print(ciphertext);

			writer.close();
			System.out.println("Successfully encrypted plaintext!");

			// As a precaution, it's possible for the file to not be
			// created, in this case we report it to the user
		} catch (FileNotFoundException e) {
			System.out.print("File not found or does not exist!");
		}
	}

	public static void main(String args[]) {
		Encrypt encryption = new Encrypt();
	}

}
