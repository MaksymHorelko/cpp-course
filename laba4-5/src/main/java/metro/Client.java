package metro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	private Socket socket = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream os = null;

	public Client(String server, int port) throws IOException {
		socket = new Socket();
		socket.connect(new InetSocketAddress(server, port), 1000);
		os = new ObjectOutputStream(socket.getOutputStream());
		is = new ObjectInputStream(socket.getInputStream());
	}

	public void finish() throws IOException, ClassNotFoundException {
		os.writeObject(new StopOperation());
		os.flush();
		System.out.println(is.readObject());
	}

	public Object applyOperation(CardOperation op) throws Exception {
		os.writeObject(op);
		os.flush();
		return is.readObject();
	}
}