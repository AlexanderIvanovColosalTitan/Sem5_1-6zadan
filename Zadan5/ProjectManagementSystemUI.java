import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagementSystemUI implements ActionListener {
    private JFrame frame;
    private JTextField taskField;
    private JTextField userField;
    private JButton addButton;
    private JButton addUserButton;
    private ProjectManagementSystem pms;

    public ProjectManagementSystemUI() {
        frame = new JFrame("Управление проектами");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        userField = new JTextField(20);
        panel.add(userField);

        addUserButton = new JButton("Добавить пользователя");
        addUserButton.addActionListener(this);
        panel.add(addUserButton);

        taskField = new JTextField(20);
        panel.add(taskField);

        addButton = new JButton("Добавить задачу");
        addButton.addActionListener(this);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);

        pms = new ProjectManagementSystem();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            String task = taskField.getText();
            pms.setTask(task);
            taskField.setText("");
        } else if (event.getSource() == addUserButton) {
            String userName = userField.getText();
            if (!userName.isEmpty()) {
                User newUser = new User(userName);
                addUser(newUser);
                userField.setText("");
                JOptionPane.showMessageDialog(frame, "Пользователь " + userName + " добавлен.");
            } else {
                JOptionPane.showMessageDialog(frame, "Пожалуйста, введите имя пользователя.");
            }
        }
    }

    public void addUser(User user) {
        pms.registerObserver(user);
    }
}
