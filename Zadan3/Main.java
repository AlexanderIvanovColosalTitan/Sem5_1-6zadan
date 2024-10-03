import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;
    private JComboBox<String> algorithmComboBox;
    private EncryptionBridge encryptionBridge;

    public Main() {
        frame = new JFrame("Система Шифрования");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        inputField = new JTextField();
        inputField.setBounds(50, 30, 300, 30);
        frame.add(inputField);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 100, 300, 100);
        frame.add(outputArea);

        String[] algorithms = {"AES", "RSA", "SHA"};
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmComboBox.setBounds(50, 70, 100, 30);
        frame.add(algorithmComboBox);

        JButton encryptButton = new JButton("Зашифровать");
        encryptButton.setBounds(160, 70, 120, 30);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = inputField.getText();
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                setEncryption(selectedAlgorithm);
                String result = encryptionBridge.performEncryption(data);
                outputArea.setText(result);
            }
        });
        frame.add(encryptButton);

        JButton decryptButton = new JButton("Расшифровать");
        decryptButton.setBounds(290, 70, 120, 30);
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = outputArea.getText();
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                setEncryption(selectedAlgorithm);
                String result = encryptionBridge.performDecryption(data);
                outputArea.setText(result);
            }
        });
        frame.add(decryptButton);

        frame.setVisible(true);
    }

    private void setEncryption(String algorithm) {
        switch (algorithm) {
            case "AES":
                encryptionBridge = new EncryptionBridge(new AESEncryption());
                break;
            case "RSA":
                encryptionBridge = new EncryptionBridge(new RSAEncryption());
                break;
            case "SHA":
                encryptionBridge = new EncryptionBridge(new SHAEncryption());
                break;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

