package ktu.agveri.socket;

import java.io.*;
import java.net.Socket;

public final class Client {

	private Socket socket = null;
	private ObjectOutputStream outputStream = null;
	private boolean isConnected = false;
	//private String sourceFilePath = "Encrypted.txt";
	private FileEvent fileEvent = null;
	private final String destinationPath = "/home/mcanv/Downloads/AgVeriFiles/";

	public Client(String sourceFilePath) {
          //  Client client = new Client(sourceFilePath);
            connect();
            sendFile(sourceFilePath);
	}

	/**
	 * Localhost'a baglaniyorum
	 */
	public void connect() {
		while (!isConnected) {
			try {
				socket = new Socket("localHost", 9003);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				isConnected = true;
			} catch (IOException e) {
			}
		}
	}

	/**
	 * FileObject class'i yardimiyla dosyayı yolluyorum
         * @param sourceFilePath
	 */
	public void sendFile(String sourceFilePath) {
		fileEvent = new FileEvent();
		String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
		String path = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/") + 1);
		fileEvent.setDestinationDirectory(destinationPath);
		fileEvent.setFilename(fileName);
		fileEvent.setSourceDirectory(sourceFilePath);
		File file = new File(sourceFilePath);
		if (file.isFile()) {
			try {
				DataInputStream diStream = new DataInputStream(new FileInputStream(file));
				long len = (int) file.length();
				byte[] fileBytes = new byte[(int) len];
				int read = 0;
				int numRead = 0;
				while (read < fileBytes.length
						&& (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
					read = read + numRead;
				}
				fileEvent.setFileSize(len);
				fileEvent.setFileData(fileBytes);
				fileEvent.setStatus("Basarili");
			} catch (IOException e) {
				fileEvent.setStatus("Hata");
			}
		} else {
			//System.out.println("path specified is not pointing to a file");
			fileEvent.setStatus("Error");
		}
		// Now writing the FileEvent object to socket
		try {
			outputStream.writeObject(fileEvent);
			//System.out.println("Done...Going to exit");
			Thread.sleep(3000);
			System.exit(0);
		} catch (IOException | InterruptedException e) {
		}

	}

	/*public static void main(String[] args) {
		Client client = new Client();
		client.connect();
		client.sendFile();
	}*/
}
