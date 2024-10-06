import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main implements ActionListener {
    private JFrame frame;
    private JTextField nameField;
    private JTextField versionField;
    private JTextArea outputArea;

    public Main() {
        frame = new JFrame("Настройки приложения");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Имя приложения:"));
        nameField = new JTextField(AppConfig.getInstance().getAppName());
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Версия:"));
        versionField = new JTextField(AppConfig.getInstance().getVersion());
        inputPanel.add(versionField);

        JButton saveButton = new JButton("Сохранить настройки");
        saveButton.addActionListener(this);
        inputPanel.add(saveButton);

        JButton loadButton = new JButton("Загрузить настройки");
        loadButton.addActionListener(this);
        inputPanel.add(loadButton);

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

        if (command.equals("Сохранить настройки")) {
            AppConfig config = AppConfig.getInstance();
            config.setAppName(nameField.getText());
            config.setVersion(versionField.getText());
            outputArea.setText("Настройки сохранены:\n" +
                    "Имя приложения: " + config.getAppName() + "\n" +
                    "Версия: " + config.getVersion());
        } else if (command.equals("Загрузить настройки")) {
            AppConfig config = AppConfig.getInstance();
            nameField.setText(config.getAppName());
            versionField.setText(config.getVersion());
            outputArea.setText("Настройки загружены:\n" +
                    "Имя приложения: " + config.getAppName() + "\n" +
                    "Версия: " + config.getVersion());
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
