import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JTextField amountField;
    private JTextArea outputArea;
    private OptionsFasad atmFacade;

    public Main() {
        Account account = new Account("111", 1000.0);
        atmFacade = new OptionsFasad(account);

        frame = new JFrame("Банкомат");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Сумма:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JButton withdrawButton = new JButton("Снять наличные");
        withdrawButton.addActionListener(this);
        inputPanel.add(withdrawButton);

        JButton depositButton = new JButton("Пополнить счет");
        depositButton.addActionListener(this);
        inputPanel.add(depositButton);

        JButton checkBalanceButton = new JButton("Проверить баланс");
        checkBalanceButton.addActionListener(this);
        inputPanel.add(checkBalanceButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            outputArea.setText("Введите корректную сумму.");
            return;
        }

        if (command.equals("Снять наличные")) {
            if (atmFacade.withdraw(amount)) {
                outputArea.setText("Успешно снято: " + amount);
            } else {
                outputArea.setText("Недостаточно средств.");
            }
        } else if (command.equals("Пополнить счет")) {
            atmFacade.deposit(amount);
            outputArea.setText("Счет пополнен на: " + amount);
        } else if (command.equals("Проверить баланс")) {
            double balance = atmFacade.checkBalance();
            outputArea.setText("Текущий баланс для счета " + OptionsFasad.getAccountNumber() + ": " + balance);

        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
