package echoServer;

import java.io.*;
import java.net.*;

public class UDPEchoServer extends UDPServer {

	public final static int DEFAULT_PORT = 12345;

	public UDPEchoServer() {
		super(DEFAULT_PORT);
	}

	@Override
	protected void respond(DatagramSocket socket, DatagramPacket request) throws IOException {
		DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
				request.getPort());
		socket.send(reply);
	}

	public static void main(String[] args) {
		UDPServer server = new UDPEchoServer();
		Thread t = new Thread(server);
		t.start();

		System.out.println("Echo-server started...");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.shutDown();
		System.out.println("Echo-server stopped...");
	}
}
