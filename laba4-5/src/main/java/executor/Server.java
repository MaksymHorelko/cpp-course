package executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			System.out.println("Server started. Waiting for clients...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected.");

				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket clientSocket;

		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
				Executable task = (Executable) in.readObject();

				Result result = task.execute();

				out.writeObject(result);

				clientSocket.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
