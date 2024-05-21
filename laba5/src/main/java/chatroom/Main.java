package chatroom;

import javax.swing.*;
import java.net.InetAddress;

public class Main extends JFrame {
    private static final long serialVersionUID = -8955330931050770679L;
    
	private JTextField groupField;
    private JTextField portField;
    private JTextField nameField;
    private JTextField messageField;
    private JButton sendButton;
    private JTextArea outputArea;
    private JButton connectButton;
    private JButton disconnectButton;
    private JButton clearButton;
    private JButton exitButton;
    private MessanderImpl messanger;

    public Main() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Чат");

        JLabel groupLabel = new JLabel("Група:");
        JLabel portLabel = new JLabel("Порт:");
        JLabel nameLabel = new JLabel("Ім'я:");

        groupField = new JTextField();
        portField = new JTextField();
        nameField = new JTextField();
        messageField = new JTextField();
        sendButton = new JButton("Надіслати");
        outputArea = new JTextArea();
        connectButton = new JButton("З'єднати");
        disconnectButton = new JButton("Роз'єднати");
        clearButton = new JButton("Очистити");
        exitButton = new JButton("Завершити");

        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(messageField)
                        .addComponent(sendButton))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(groupLabel)
                                .addComponent(groupField)
                                .addComponent(portLabel)
                                .addComponent(portField)
                                .addComponent(nameLabel)
                                .addComponent(nameField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(connectButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(messageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sendButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(groupLabel)
                                .addComponent(groupField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portLabel)
                                .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel)
                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(outputScrollPane))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(connectButton)
                        .addComponent(disconnectButton)
                        .addComponent(clearButton)
                        .addComponent(exitButton))
        );

        pack();

        sendButton.addActionListener(e -> sendMessage());
        connectButton.addActionListener(e -> connect());
        disconnectButton.addActionListener(e -> disconnect());
        clearButton.addActionListener(e -> clearOutput());
        exitButton.addActionListener(e -> exit());
    }

    private void sendMessage() {
        if (messanger != null) {
            messanger.send();
        } else {
            JOptionPane.showMessageDialog(this, "Не підключен до конференції.");
        }
    }

    private void connect() {
        try {
            String group = groupField.getText();
            int port = Integer.parseInt(portField.getText());
            String name = nameField.getText();
            InetAddress addr = InetAddress.getByName(group);

            UITasksImpl uiTasks = new UITasksImpl(messageField, outputArea);
            messanger = new MessanderImpl(addr, port, name, uiTasks);
            messanger.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Помилка підключення до конференції: " + ex.getMessage());
        }
    }

    private void disconnect() {
        if (messanger != null) {
            messanger.stop();
            messanger = null;
        }
    }

    private void clearOutput() {
        outputArea.setText("");
    }

    private void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main form = new Main();
            form.setVisible(true);
        });
    }
}