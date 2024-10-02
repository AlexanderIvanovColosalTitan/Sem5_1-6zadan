import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;
    private JComboBox<String> algorithmSelector;
    private DataEncryptor encryptor;
    private AESEncryption aesEncryption;
    private RSAEncryption rsaEncryption;

    public Main() throws Exception {
        aesEncryption = new AESEncryption();
        rsaEncryption = new RSAEncryption();

        frame = new JFrame("Encryption App");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        inputField = new JTextField();
        inputField.setBounds(50, 20, 300, 30);
        frame.add(inputField);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 100, 300, 100);
        frame.add(outputArea);

        String[] algorithms = {"AES", "RSA", "SHA"};
        algorithmSelector = new JComboBox<>(algorithms);
        algorithmSelector.setBounds(50, 60, 300, 30);
        frame.add(algorithmSelector);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(50, 210, 100, 30);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput(true);
            }
        });
        frame.add(encryptButton);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(250, 210, 100, 30);
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput(false);
            }
        });
        frame.add(decryptButton);

        frame.setVisible(true);
    }

    private void processInput(boolean isEncrypting) {
        String data = inputField.getText();
        String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();

        try {
            switch (selectedAlgorithm) {
                case "AES":
                    encryptor = new ConcreteDataEncryptor(aesEncryption);
                    break;
                case "RSA":
                    encryptor = new ConcreteDataEncryptor(rsaEncryption);
                    break;
                case "SHA":
                    if (isEncrypting) {
                        encryptor = new ConcreteDataEncryptor(new SHAEncryption());
                    } else {
                        outputArea.setText("SHA is a one-way hash function and cannot be decrypted.");
                        return;
                    }
                    break;
            }
            String result = isEncrypting ? encryptor.encryptData(data) : encryptor.decryptData(data);
            outputArea.setText(result);
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
