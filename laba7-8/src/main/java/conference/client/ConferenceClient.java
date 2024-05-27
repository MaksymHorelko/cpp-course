package conference.client;

import javax.swing.*;

import conference.rmi.ConferenceRegistration;
import conference.rmi.Participant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConferenceClient {
	private JFrame frame;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField reportField;
	private JTextField affiliationField;
	private JTextField rmiAddressField;
	private JTextField portField;

	public ConferenceClient() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Conference Registration");
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblRmiAddress = new JLabel("RMI Address:");
		rmiAddressField = new JTextField("localhost");
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.getContentPane().add(lblRmiAddress, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(rmiAddressField, gbc);

		JLabel lblPort = new JLabel("Port:");
		portField = new JTextField("1099");
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.getContentPane().add(lblPort, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(portField, gbc);

		JLabel lblName = new JLabel("Name:");
		nameField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.getContentPane().add(lblName, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(nameField, gbc);

		JLabel lblLastName = new JLabel("Last Name:");
		lastNameField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.getContentPane().add(lblLastName, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(lastNameField, gbc);

		JLabel lblEmail = new JLabel("Email:");
		emailField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 4;
		frame.getContentPane().add(lblEmail, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(emailField, gbc);

		JLabel lblReport = new JLabel("Report:");
		reportField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 5;
		frame.getContentPane().add(lblReport, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(reportField, gbc);

		JLabel lblAffiliation = new JLabel("Affiliation:");
		affiliationField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 6;
		frame.getContentPane().add(lblAffiliation, gbc);
		gbc.gridx = 1;
		frame.getContentPane().add(affiliationField, gbc);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		buttonPanel.add(btnClear);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerParticipant();
			}
		});
		buttonPanel.add(btnRegister);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(btnFinish);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		frame.getContentPane().add(buttonPanel, gbc);
	}

	private void clearFields() {
		nameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		reportField.setText("");
		affiliationField.setText("");
	}

	private void registerParticipant() {
		String name = nameField.getText();
		String lastName = lastNameField.getText();
		String email = emailField.getText();
		String report = reportField.getText();
		String affiliation = affiliationField.getText();

		clearFields();

		if (!Participant.validateParticipant(name, lastName, email, report, affiliation)) {
			showErrorMessage("Check all your fields!", "Incorrect input");
		}

		else {
			Participant participant = new Participant(name, lastName, email, report, affiliation);
			registerClient(participant);
		}
	}

	private void showErrorMessage(String errorMessage, String windowLabel) {
		JLabel errorLabel = new JLabel(errorMessage);
		errorLabel.setForeground(Color.RED);

		JPanel panel = new JPanel();
		panel.add(errorLabel);

		JOptionPane.showMessageDialog(null, panel, windowLabel, JOptionPane.ERROR_MESSAGE);
	}

	private void registerClient(Participant participant) {
		ConferenceRegistration registration;
		try {
			String address = rmiAddressField.getText();
			int port = Integer.parseInt(portField.getText());
			Registry registry = LocateRegistry.getRegistry(address, port);
			registration = (ConferenceRegistration) registry.lookup("ConferenceRegistration");

			int count = registration.registerParticipant(participant);
			JOptionPane.showMessageDialog(frame, "Registered successfully! Total participants: " + count);
		} catch (Exception e) {
			showErrorMessage("Check host / port", "Connection problem");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConferenceClient window = new ConferenceClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
