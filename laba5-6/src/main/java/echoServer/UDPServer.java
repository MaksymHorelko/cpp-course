package echoServer;

import java.io.*;
import java.net.*;

public abstract class UDPServer implements Runnable {

	private final int bufferSize;
	private final int port;
	private volatile boolean isShutDown = false;

	@Override
	public void run() {
		byte[] buffer = new byte[bufferSize];
		try (DatagramSocket socket = new DatagramSocket(port)) {
			socket.setSoTimeout(10000);
			while (true) {
				if (isShutDown)
					return;
				DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
				try {
					socket.receive(incoming);
					this.respond(socket, incoming);
				} catch (SocketTimeoutException e) {
					if (isShutDown)
						return;
				} catch (IOException ex) {
					System.err.println(ex.getMessage() + "\n" + ex);
				}
			}
		} catch (SocketException ex) {
			System.err.println("Could not bind to port: " + port + "\n" + ex);
		}
	}

	public UDPServer(int bufferSize, int port) {
		this.bufferSize = bufferSize;
		this.port = port;
	}

	public UDPServer(int port) {
		this(8192, port);
	}

	public void shutDown() {
		this.isShutDown = true;
	}

	protected abstract void respond(DatagramSocket socket, DatagramPacket incoming) throws IOException;
}
