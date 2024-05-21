package laba4.metro;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class MetroSystemGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField ipField;
	private JTextField portField;
	private JTextArea outputArea;
	private Client client;
	private JTextField serialNumberField;
	private JTextField amountField;

	public MetroSystemGUI() {
		setTitle("Metro Card System");
		setSize(400, 600);
		setDefaultCloseOperation(0);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create the menu
		JMenu fileMenu = new JMenu("File");
		JMenu operationsMenu = new JMenu("Operations");

		// Create menu items
		JMenuItem connectItem = new JMenuItem("Connect");
		JMenuItem addMetroCardItem = new JMenuItem("Add Metro Card");
		JMenuItem addMoneyItem = new JMenuItem("Add Money");
		JMenuItem payMoneyItem = new JMenuItem("Pay Money");
		JMenuItem removeCardItem = new JMenuItem("Remove Card");
		JMenuItem showBalanceItem = new JMenuItem("Show Balance");
		JMenuItem getUserItem = new JMenuItem("Get User");
		JMenuItem stopItem = new JMenuItem("Stop");
		JMenuItem exitItem = new JMenuItem("Exit");

		// Add action listeners to menu items
		connectItem.addActionListener(new ConnectButtonListener());
		addMetroCardItem.addActionListener(new AddMetroCardButtonListener());
		addMoneyItem.addActionListener(new AddMoneyButtonListener());
		payMoneyItem.addActionListener(new PayMoneyButtonListener());
		removeCardItem.addActionListener(new RemoveCardButtonListener());
		showBalanceItem.addActionListener(new ShowBalanceButtonListener());
		getUserItem.addActionListener(new GetUserButtonListener());
		stopItem.addActionListener(new StopButtonListener());
		exitItem.addActionListener(e -> {
			try {
				if (client != null)
					client.finish();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		});

		// Add items to the menus
		fileMenu.add(connectItem);
		fileMenu.add(exitItem);

		operationsMenu.add(addMetroCardItem);
		operationsMenu.add(addMoneyItem);
		operationsMenu.add(payMoneyItem);
		operationsMenu.add(removeCardItem);
		operationsMenu.add(showBalanceItem);
		operationsMenu.add(getUserItem);
		operationsMenu.add(stopItem);

		// Add menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(operationsMenu);

		// Add the menu bar to the frame
		setJMenuBar(menuBar);

		// Create connection panel
		JPanel connectionPanel = new JPanel();
		connectionPanel.setLayout(new GridLayout(2, 2, 5, 5));
		connectionPanel.setBorder(BorderFactory.createTitledBorder("Connection"));

		connectionPanel.add(new JLabel("Server IP:"));
		ipField = new JTextField("localhost");
		connectionPanel.add(ipField);

		connectionPanel.add(new JLabel("Server Port:"));
		portField = new JTextField("7891");
		connectionPanel.add(portField);

		add(connectionPanel, BorderLayout.NORTH);

		// Create output area
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
		add(scrollPane, BorderLayout.CENTER);

	}

	private class ConnectButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String ip = ipField.getText();
				int port = Integer.parseInt(portField.getText());
				client = new Client(ip, port);
				outputArea.append("Connected to server at " + ip + ":" + port + "\n");
			} catch (Exception ex) {
				outputArea.append("Failed to connect: " + ex.getMessage() + "\n");
			}
		}
	}

	private class AddMetroCardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField firstNameField = new JTextField();
			JTextField lastNameField = new JTextField();
			JTextField middleNameField = new JTextField();
			JTextField birthDateField = new JTextField("YYYY-MM-DD");
			JComboBox<Sex> sexComboBox = new JComboBox<>(Sex.values());
			JTextField colledgeField = new JTextField();
			JTextField balanceField = new JTextField();

			JPanel panel = new JPanel(new GridLayout(8, 2));
			panel.add(new JLabel("First Name:"));
			panel.add(firstNameField);
			panel.add(new JLabel("Last Name:"));
			panel.add(lastNameField);
			panel.add(new JLabel("Middle Name:"));
			panel.add(middleNameField);
			panel.add(new JLabel("Sex:"));
			panel.add(sexComboBox);
			panel.add(new JLabel("Birth Date:"));
			panel.add(birthDateField);
			panel.add(new JLabel("Colledge:"));
			panel.add(colledgeField);
			panel.add(new JLabel("Initial Balance:"));
			panel.add(balanceField);

			int result = JOptionPane.showConfirmDialog(null, panel, "Enter Metro Card Details",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				try {
					String firstName = firstNameField.getText();
					String lastName = lastNameField.getText();
					String middleName = middleNameField.getText();
					Sex sex = (Sex) sexComboBox.getSelectedItem();
					Date birthDate = java.sql.Date.valueOf(birthDateField.getText());
					String colledge = colledgeField.getText();
					BigDecimal balance = new BigDecimal(balanceField.getText());

					AddMetroCardOperation op = new AddMetroCardOperation();
					User user = new User(firstName, lastName, middleName, sex, birthDate);
					op.getCrd().setUser(user);
					op.getCrd().setColledge(colledge);
					op.getCrd().setBalance(balance);
					client.applyOperation(op);
					outputArea.append(
							"Metro card added successfully, serial number: " + op.getCrd().getSerialNumber() + " \n");
				} catch (Exception ex) {
					outputArea.append("Failed to add metro card: " + ex.getMessage() + "\n");
				}
			}
		}
	}

	private class AddMoneyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame addMoneyFrame = new JFrame("Add Money");
			addMoneyFrame.setSize(300, 150);
			addMoneyFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3, 2, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JLabel serialNumberLabel = new JLabel("Serial Number:");
			serialNumberField = new JTextField();

			JLabel amountLabel = new JLabel("Amount:");
			amountField = new JTextField();

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						String serialNumber = serialNumberField.getText();
						String amountText = amountField.getText();

						if (serialNumber.isEmpty() || amountText.isEmpty()) {
							JOptionPane.showMessageDialog(addMoneyFrame,
									"Serial number and amount fields cannot be empty.", "Input Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						BigDecimal amount;
						try {
							amount = new BigDecimal(amountText);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(addMoneyFrame, "Invalid amount. Please enter a valid number.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (amount.compareTo(BigDecimal.ZERO) <= 0) {
							JOptionPane.showMessageDialog(addMoneyFrame, "Amount must be greater than zero.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						AddMoneyOperation op = new AddMoneyOperation(Integer.valueOf(serialNumber), amount);
						client.applyOperation(op);
						outputArea.append("Money added successfully.\n");
						addMoneyFrame.dispose();
					} catch (Exception ex) {
						outputArea.append("Failed to add money: " + ex.getMessage() + "\n");
					}
				}
			});

			panel.add(serialNumberLabel);
			panel.add(serialNumberField);
			panel.add(amountLabel);
			panel.add(amountField);
			panel.add(new JLabel());
			panel.add(submitButton);

			addMoneyFrame.add(panel);
			addMoneyFrame.setVisible(true);
		}
	}

	private class PayMoneyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame payMoneyFrame = new JFrame("Pay Money");
			payMoneyFrame.setSize(300, 150);
			payMoneyFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3, 2, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JLabel serialNumberLabel = new JLabel("Serial Number:");
			serialNumberField = new JTextField();

			JLabel amountLabel = new JLabel("Amount:");
			amountField = new JTextField();

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						String serialNumber = serialNumberField.getText();
						String amountText = amountField.getText();

						if (serialNumber.isEmpty() || amountText.isEmpty()) {
							JOptionPane.showMessageDialog(payMoneyFrame,
									"Serial number and amount fields cannot be empty.", "Input Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						BigDecimal amount;
						try {
							amount = new BigDecimal(amountText);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(payMoneyFrame, "Invalid amount. Please enter a valid number.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (amount.compareTo(BigDecimal.ZERO) <= 0) {
							JOptionPane.showMessageDialog(payMoneyFrame, "Amount must be greater than zero.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						PayMoneyOperation op = new PayMoneyOperation(Integer.valueOf(serialNumber), amount);
						client.applyOperation(op);
						outputArea.append("Money payed successfully.\n");
						payMoneyFrame.dispose();
					} catch (Exception ex) {
						outputArea.append("Failed to pay money: " + ex.getMessage() + "\n");
					}
				}
			});

			panel.add(serialNumberLabel);
			panel.add(serialNumberField);
			panel.add(amountLabel);
			panel.add(amountField);
			panel.add(new JLabel());
			panel.add(submitButton);

			payMoneyFrame.add(panel);
			payMoneyFrame.setVisible(true);
		}
	}

	private class RemoveCardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame removeCardFrame = new JFrame("Remove Card");
			removeCardFrame.setSize(300, 100);
			removeCardFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JLabel serialNumberLabel = new JLabel("Serial Number:");
			serialNumberField = new JTextField();

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						String serialNumber = serialNumberField.getText();

						if (serialNumber.isEmpty()) {
							JOptionPane.showMessageDialog(removeCardFrame, "Serial number field cannot be empty.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						Integer serialNum;
						try {
							serialNum = Integer.valueOf(serialNumber);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(removeCardFrame,
									"Invalid serial number. Please enter a valid number.", "Input Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						RemoveCardOperation op = new RemoveCardOperation(serialNum);
						client.applyOperation(op);
						outputArea.append("Card removed successfully.\n");
						removeCardFrame.dispose();
					} catch (Exception ex) {
						outputArea.append("Failed to remove card: " + ex.getMessage() + "\n");
					}
				}
			});

			panel.add(serialNumberLabel);
			panel.add(serialNumberField);
			panel.add(new JLabel());
			panel.add(submitButton);

			removeCardFrame.add(panel);
			removeCardFrame.setVisible(true);
		}
	}

	private class ShowBalanceButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame showBalanceFrame = new JFrame("Show Balance");
			showBalanceFrame.setSize(300, 100);
			showBalanceFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JLabel serialNumberLabel = new JLabel("Serial Number:");
			serialNumberField = new JTextField();

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						String serialNumber = serialNumberField.getText();

						if (serialNumber.isEmpty()) {
							JOptionPane.showMessageDialog(showBalanceFrame, "Serial number field cannot be empty.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						Integer serialNum;
						try {
							serialNum = Integer.valueOf(serialNumber);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(showBalanceFrame,
									"Invalid serial number. Please enter a valid number.", "Input Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						ShowBalanceOperation op = new ShowBalanceOperation(serialNum);
						Object response = client.applyOperation(op);
						if (response instanceof BigDecimal) {
							BigDecimal balance = (BigDecimal) response;
							outputArea.append("Balance for card " + serialNum + ": " + balance + "\n");
						} else {
							outputArea.append((String) response + "\n");
						}
						showBalanceFrame.dispose();
					} catch (Exception ex) {
						outputArea.append("Failed to show balance: " + ex.getMessage() + "\n");
					}
				}
			});

			panel.add(serialNumberLabel);
			panel.add(serialNumberField);
			panel.add(new JLabel());
			panel.add(submitButton);

			showBalanceFrame.add(panel);
			showBalanceFrame.setVisible(true);
		}
	}

	private class GetUserButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame getUserCardFrame = new JFrame("Get User");
			getUserCardFrame.setSize(300, 100);
			getUserCardFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2, 10, 10));
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JLabel serialNumberLabel = new JLabel("Serial Number:");
			serialNumberField = new JTextField();

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						String serialNumber = serialNumberField.getText();

						if (serialNumber.isEmpty()) {
							JOptionPane.showMessageDialog(getUserCardFrame, "Serial number field cannot be empty.",
									"Input Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						Integer serialNum;
						try {
							serialNum = Integer.valueOf(serialNumber);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(getUserCardFrame,
									"Invalid serial number. Please enter a valid number.", "Input Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						GetUserOperation op = new GetUserOperation(serialNum);
						outputArea.append(client.applyOperation(op) + "\n");
						getUserCardFrame.dispose();
					} catch (Exception ex) {
						outputArea.append("Failed to get user: " + ex.getMessage() + "\n");
					}
				}
			});

			panel.add(serialNumberLabel);
			panel.add(serialNumberField);
			panel.add(new JLabel());
			panel.add(submitButton);

			getUserCardFrame.add(panel);
			getUserCardFrame.setVisible(true);
		}
	}

	private class StopButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				client.finish();
				outputArea.append("Connection closed\n");
			} catch (Exception ex) {
				outputArea.append("Failed to stop: " + ex.getMessage() + "\n");
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MetroSystemGUI().setVisible(true);
			}
		});
	}
}
