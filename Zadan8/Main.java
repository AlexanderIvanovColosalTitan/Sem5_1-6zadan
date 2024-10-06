import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextArea contactsArea;
    private ContactManager contactManager;

    public Main() {
        contactManager = new ContactManager();

        frame = new JFrame("Управление контактами");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Имя:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Телефон:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        JButton addButton = new JButton("Добавить контакт");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        JButton saveButton = new JButton("Сохранить список");
        saveButton.addActionListener(this);
        inputPanel.add(saveButton);

        JButton restoreButton = new JButton("Восстановить список");
        restoreButton.addActionListener(this);
        inputPanel.add(restoreButton);

        JButton clearButton = new JButton("Удалить все контакты");
        clearButton.addActionListener(this);
        inputPanel.add(clearButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        contactsArea = new JTextArea();
        contactsArea.setEditable(false);
        panel.add(new JScrollPane(contactsArea), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Добавить контакт")) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                contactManager.addContact(name, phone);
                updateContactsArea();
                nameField.setText("");
                phoneField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Введите имя и телефон.");
            }
        } else if (command.equals("Сохранить список")) {
            contactManager.save();
            JOptionPane.showMessageDialog(frame, "Список контактов сохранен.");
        } else if (command.equals("Восстановить список")) {
            contactManager.restore();
            updateContactsArea();
            JOptionPane.showMessageDialog(frame, "Список контактов восстановлен.");
        } else if (command.equals("Удалить все контакты")) {
            contactManager.clearContacts();
            updateContactsArea();
            JOptionPane.showMessageDialog(frame, "Все контакты удалены.");
        }
    }

    private void updateContactsArea() {
        contactsArea.setText("");
        for (Contact contact : contactManager.getContacts()) {
            contactsArea.append(contact.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
