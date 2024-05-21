package echoServer;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {

	public final static int DEFAULT_PORT = 12345;

	public static void main(String[] args) {
		String hostname = "localhost";
		if (args.length > 0) {
			hostname = args[0];
		}
		try {
			InetAddress ia = InetAddress.getByName(hostname);
			DatagramSocket socket = new DatagramSocket();
			Thread sender = new SenderThread(socket, ia, DEFAULT_PORT);
			sender.start();
			Thread receiver = new ReceiverThread(socket);
			receiver.start();
		} catch (UnknownHostException | SocketException ex) {
			System.err.println(ex);
		}
	}
}
