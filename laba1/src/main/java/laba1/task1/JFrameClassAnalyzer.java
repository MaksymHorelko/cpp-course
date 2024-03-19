package laba1.task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameClassAnalyzer extends JFrame {

    private JTextField classNameField;
    private JTextArea resultTextArea;

    public JFrameClassAnalyzer() {
        setTitle("Class Analyzer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel classNameLabel = new JLabel("Class Name:");
        classNameField = new JTextField();
        JButton analyzeButton = new JButton("Analyze");
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText();
                String classDescription = ClassAnalyzer.getClassDescription(className);
                resultTextArea.setText(classDescription);
            }
        });

        panel.add(classNameLabel);
        panel.add(classNameField);
        panel.add(analyzeButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrameClassAnalyzer frame = new JFrameClassAnalyzer();
                frame.setVisible(true);
            }
        });
    }
}
