package executor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientGUI {

	private ObjectOutputStream out;
	private ObjectInputStream in;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ClientGUI::new);
	}

	public ClientGUI() {
		JFrame frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel numberLabel = new JLabel("Number:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.getContentPane().add(numberLabel, gbc);

		JTextField numberField = new JTextField(10);
		numberField.setToolTipText("Enter a number");
		gbc.gridx = 1;
		gbc.gridy = 0;
		frame.getContentPane().add(numberField, gbc);

		JButton submitButton = new JButton("Submit");
		gbc.gridx = 1;
		gbc.gridy = 1;
		frame.getContentPane().add(submitButton, gbc);

		JLabel resultLabel = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		frame.getContentPane().add(resultLabel, gbc);

		submitButton.addActionListener(event -> {
			try {
				int number = Integer.parseInt(numberField.getText());

				Socket socket = new Socket("localhost", 12345);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());

				Executable task = new Factorial(number);
				out.writeObject(task);

				Result result = (Result) in.readObject();
				resultLabel.setText("Result: " + result.output() + ", time: " + result.scoreTime());

				socket.close();
			} catch (NumberFormatException | IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		});

		frame.setVisible(true);
	}
}
