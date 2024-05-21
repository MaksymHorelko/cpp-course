package laba4.executor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 12345);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			Executable task = new Factorial(10);

			out.writeObject(task);

			Result result = (Result) in.readObject();
			System.out.println("Result received from server: " + result.output());
			System.out.println("Result time from server: " + result.scoreTime());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}