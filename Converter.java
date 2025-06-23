import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter extends JFrame implements ActionListener {

    private final JTextField inputField;
    private final JLabel resultLabel;
    private final JComboBox<String> fromBox, toBox;
    private final String[] systems = {"Binary", "Octal", "Decimal", "Hexadecimal"};

    public Converter() {
        setTitle("Number System Converter");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // === Main Container ===
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Number System Converter");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(110, 20, 300, 30);
        panel.add(title);

        // === Input Field ===
        JLabel inputLabel = new JLabel("Enter Number:");
        inputLabel.setForeground(Color.LIGHT_GRAY);
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        inputLabel.setBounds(50, 80, 120, 25);
        panel.add(inputLabel);

        inputField = new JTextField();
        inputField.setFont(new Font("Consolas", Font.BOLD, 20));
        inputField.setBounds(180, 80, 250, 30);
        inputField.setBackground(new Color(50, 50, 50));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        panel.add(inputField);

        // === From Dropdown ===
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.LIGHT_GRAY);
        fromLabel.setBounds(50, 140, 100, 25);
        fromLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(fromLabel);

        fromBox = new JComboBox<>(systems);
        fromBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        fromBox.setBounds(110, 140, 120, 30);
        fromBox.setBackground(new Color(60, 60, 60));
        fromBox.setForeground(Color.WHITE);
        panel.add(fromBox);

        // === To Dropdown ===
        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.LIGHT_GRAY);
        toLabel.setBounds(260, 140, 40, 25);
        toLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(toLabel);

        toBox = new JComboBox<>(systems);
        toBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        toBox.setBounds(300, 140, 120, 30);
        toBox.setBackground(new Color(60, 60, 60));
        toBox.setForeground(Color.WHITE);
        panel.add(toBox);

        // === Convert Button ===
        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(180, 200, 120, 40);
        convertBtn.setBackground(new Color(255, 165, 0));
        convertBtn.setForeground(Color.BLACK);
        convertBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        convertBtn.addActionListener(this);
        panel.add(convertBtn);

        // === Result Label ===
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Consolas", Font.BOLD, 24));
        resultLabel.setForeground(Color.GREEN);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBounds(50, 270, 380, 40);
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();
        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();

        try {
            int decimalValue = switch (from) {
                case "Binary" -> Integer.parseInt(input, 2);
                case "Octal" -> Integer.parseInt(input, 8);
                case "Decimal" -> Integer.parseInt(input, 10);
                case "Hexadecimal" -> Integer.parseInt(input, 16);
                default -> throw new NumberFormatException();
            };

            String result = switch (to) {
                case "Binary" -> Integer.toBinaryString(decimalValue);
                case "Octal" -> Integer.toOctalString(decimalValue);
                case "Decimal" -> Integer.toString(decimalValue);
                case "Hexadecimal" -> Integer.toHexString(decimalValue).toUpperCase();
                default -> "Error";
            };

            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Invalid input for selected base");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Converter::new);
    }
}
