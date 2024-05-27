package ipExchange;

import java.io.*;
import java.net.*;

public class UPDServer {
	private final ActiveUsers userList;
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	private final int bufferSize;

	public UPDServer(int serverPort, int bufferSize) {
		try {
			socket = new DatagramSocket(serverPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		userList = new ActiveUsers();
		this.bufferSize = bufferSize;
	}

	public void work() {
		try {
			System.out.println("Server started...");
			while (true) {
				processConnection();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e);
		} finally {
			System.out.println("Server stopped");
			socket.close();
		}
	}

	private void processConnection() throws IOException {
		getUserData();
		System.out.println("New request: " + packet.getAddress().getHostAddress() + " port: " + packet.getPort());
		sendUserData();
	}

	private void getUserData() throws IOException {
		byte[] buffer = new byte[bufferSize];
		packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		User user = new User(packet.getAddress(), packet.getPort());
		if (!userList.isContains(user)) {
			userList.addUser(user);
		}
	}

	private void sendUserData() throws IOException {
		byte[] buffer;
		for (int i = 0; i < userList.getUsersSize(); i++) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(userList.getUser(i));
			buffer = bout.toByteArray();
			packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
			socket.send(packet);
		}
		buffer = "end".getBytes();
		packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
		socket.send(packet);
	}

	public static void main(String[] args) {
		UPDServer server = new UPDServer(1501, 256);
		server.work();
	}
}