package echoServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ReceiverThread extends Thread {

	private final DatagramSocket socket;
	private volatile boolean stopped = false;

	ReceiverThread(DatagramSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		byte[] buffer = new byte[65507];
		while (true) {
			if (stopped)
				return;
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength(), StandardCharsets.UTF_8);
				System.out.println(s);
				Thread.yield();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	public void halt() {
		this.stopped = true;
	}
}
