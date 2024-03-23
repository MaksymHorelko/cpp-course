package laba1.task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayManipulatorGUI extends JFrame {

	private JTextArea outputTextArea;

	public ArrayManipulatorGUI() {
		setTitle("Array Manipulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputTextArea);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 2));

		JTextField classNameField = new JTextField();
		JTextField sizeField = new JTextField();
		JTextField rowsField = new JTextField();
		JTextField colsField = new JTextField();

		JButton createArrayButton = new JButton("Create Array");
		createArrayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String className = classNameField.getText();
					int size = Integer.parseInt(sizeField.getText());
					Object[] array = ArrayManipulator.createArray(className, size);
					outputTextArea.append("Created array: " + ArrayManipulator.arrayToString(array) + "\n");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ArrayManipulatorGUI.this, "Please enter a valid integer for size.");
				}
			}
		});

		JButton createMatrixButton = new JButton("Create Matrix");
		createMatrixButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String className = classNameField.getText();
					int rows = Integer.parseInt(rowsField.getText());
					int cols = Integer.parseInt(colsField.getText());
					Object[][] matrix = ArrayManipulator.createMatrix(className, rows, cols);
					outputTextArea.append("Created matrix:\n" + ArrayManipulator.matrixToString(matrix) + "\n");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ArrayManipulatorGUI.this,
							"Please enter valid integers for rows and columns.");
				}
			}
		});

		buttonPanel.add(new JLabel("Class Name:"));
		buttonPanel.add(classNameField);
		buttonPanel.add(new JLabel("Size:"));
		buttonPanel.add(sizeField);
		buttonPanel.add(new JLabel("Rows:"));
		buttonPanel.add(rowsField);
		buttonPanel.add(new JLabel("Cols:"));
		buttonPanel.add(colsField);
		buttonPanel.add(createArrayButton);
		buttonPanel.add(createMatrixButton);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ArrayManipulatorGUI();
			}
		});
	}
}
