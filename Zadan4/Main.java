import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JComboBox<String> libraryComboBox;
    private JTextArea outputArea;
    private GraphicsLibrary currentLibrary;

    public Main() {
        frame = new JFrame("Управление графическими библиотеками");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        String[] libraries = {"OpenGL", "DirectX", "Vulkan"};
        libraryComboBox = new JComboBox<>(libraries);
        panel.add(libraryComboBox);

        JButton initButton = new JButton("Инициализировать");
        initButton.addActionListener(this);
        panel.add(initButton);

        JButton renderButton = new JButton("Рендерить");
        renderButton.addActionListener(this);
        panel.add(renderButton);

        JButton cleanupButton = new JButton("Очистить");
        cleanupButton.addActionListener(this);
        panel.add(cleanupButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedLibrary = (String) libraryComboBox.getSelectedItem();

        switch (selectedLibrary) {
            case "OpenGL":
                currentLibrary = new OpenGL();
                break;
            case "DirectX":
                currentLibrary = new DirectX();
                break;
            case "Vulkan":
                currentLibrary = new Vulkan();
                break;
        }

        if (e.getActionCommand().equals("Инициализировать")) {
            currentLibrary.initialize();
            outputArea.append("Инициализация " + selectedLibrary + " выполнена.\n");
        } else if (e.getActionCommand().equals("Рендерить")) {
            if (currentLibrary != null) {
                currentLibrary.render();
                outputArea.append("Рендеринг с использованием " + selectedLibrary + " выполнен.\n");
            }
        } else if (e.getActionCommand().equals("Очистить")) {
            if (currentLibrary != null) {
                currentLibrary.cleanup();
                outputArea.append("Очистка " + selectedLibrary + " выполнена.\n");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
