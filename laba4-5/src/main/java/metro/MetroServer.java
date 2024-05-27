package metro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
	private MetroSystem bnk = null;
	private ServerSocket servSock = null;
	private int serverPort = -1;

	public MetroServer(int port) {
		this.bnk = new MetroSystem();
		this.serverPort = port;
		start();
	}

	@Override
	public void run() {
		try {
			this.servSock = new ServerSocket(serverPort);
			System.out.println("Metro Server started");
			while (true) {
				System.out.println("New Client Waiting...");
				Socket sock = servSock.accept();
				System.out.println("New client: " + sock);
				ClientHandler ch = new ClientHandler(this.getBnk(), sock);
				ch.start();
			}
		} catch (IOException e) {

			System.out.println("Error: " + e);
		} finally {
			try {
				servSock.close();
				System.out.println("Metro Server stopped");
			} catch (IOException ex) {
				System.out.println("Error: " + ex);
			}
		}
	}

	private MetroSystem getBnk() {
		return bnk;
	}

	public static void main(String[] args) {
		new MetroServer(7891);
	}
}
