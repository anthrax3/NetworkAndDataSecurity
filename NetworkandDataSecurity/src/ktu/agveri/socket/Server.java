package ktu.agveri.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectInputStream inputStream = null;
	private FileEvent fileEvent;
	private File dstFile = null;
	private FileOutputStream fileOutputStream = null;

	public Server() {
            
	}
	public void doConnect() {
		try {
			serverSocket = new ServerSocket(9003);
			socket = serverSocket.accept();
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
		}
	}

	
	public void downloadFile() {
		try {
			fileEvent = (FileEvent) inputStream.readObject();
			if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
				System.out.println("Hata olustu ...");
				System.exit(0);
			}
			String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
			if (!new File(fileEvent.getDestinationDirectory()).exists()) {
				new File(fileEvent.getDestinationDirectory()).mkdirs();
			}
			dstFile = new File(outputFile);
			fileOutputStream = new FileOutputStream(dstFile);
			fileOutputStream.write(fileEvent.getFileData());
			fileOutputStream.flush();
			fileOutputStream.close();
			System.out.println("Kaydedilen Dosya : " + outputFile + " basariyla kaydedildi.");
			Thread.sleep(3000);
			System.exit(0);

		} catch (IOException | ClassNotFoundException | InterruptedException e) {
		}
	}

	/*public static void main(String[] args) {
		Server server = new Server();
		server.doConnect();
		server.downloadFile();
	}*/
}
