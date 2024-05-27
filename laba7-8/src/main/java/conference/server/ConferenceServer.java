package conference.server;

import javax.swing.*;

import conference.rmi.ConferenceRegistration;
import conference.rmi.Participant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ConferenceServer {
	private JFrame frame;
	private JTextArea logTextArea;
	private JLabel totalParticipantsLabel;
	private List<Participant> participants;
	private ConferenceRegistrationImpl registrationImpl;
	private Registry registry;

	public ConferenceServer() {
		participants = new ArrayList<>();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Conference Server");
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton stopServerButton = new JButton("Stop Server");
		stopServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		buttonPanel.add(stopServerButton);

		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(logTextArea);
		frame.getContentPane().add(logScrollPane, BorderLayout.CENTER);

		totalParticipantsLabel = new JLabel("Total Participants: 0");
		frame.getContentPane().add(totalParticipantsLabel, BorderLayout.SOUTH);
	}

	private void stopServer() {
		try {
			if (registry != null) {
				registry.unbind("ConferenceRegistration");
				UnicastRemoteObject.unexportObject(registrationImpl, true);
				log("Server stopped.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void log(String message) {
		logTextArea.append(message + "\n");
	}

	public void startServer(int port) {
		try {
			registrationImpl = new ConferenceRegistrationImpl();
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("ConferenceRegistration", registrationImpl);
			log("Server started on port " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ConferenceRegistrationImpl extends UnicastRemoteObject implements ConferenceRegistration {
		private static final long serialVersionUID = 1L;

		protected ConferenceRegistrationImpl() throws RemoteException {
			super();
		}

		@Override
		public synchronized int registerParticipant(Participant participant) throws RemoteException {
			participants.add(participant);
			log("Participant registered: " + participant.getName() + " " + participant.getLastName());
			totalParticipantsLabel.setText("Total Participants: " + participants.size());
			return participants.size();
		}

		@Override
		public void clientConnected() throws RemoteException {
			log("Client connected.");
		}

		@Override
		public void clientDisconnected() throws RemoteException {
			log("Client disconnected.");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConferenceServer window = new ConferenceServer();
					window.frame.setVisible(true);
					window.startServer(1099); // Start server on default port 1099
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
