import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JTextField graphicField;
    private JTextArea outputArea;
    private CompositeDocument compositeDocument;

    public Main() {
        compositeDocument = new CompositeDocument();

        frame = new JFrame("Редактор документа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Текстовый элемент:"));
        textField = new JTextField();
        inputPanel.add(textField);

        JButton addTextButton = new JButton("Добавить текст");
        addTextButton.addActionListener(this);
        inputPanel.add(addTextButton);

        inputPanel.add(new JLabel("Графический элемент:"));
        graphicField = new JTextField();
        inputPanel.add(graphicField);

        JButton addGraphicButton = new JButton("Добавить графику");
        addGraphicButton.addActionListener(this);
        inputPanel.add(addGraphicButton);

        JButton renderButton = new JButton("Отобразить документ");
        renderButton.addActionListener(this);
        inputPanel.add(renderButton);

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

        if (command.equals("Добавить текст")) {
            String text = textField.getText();
            if (!text.trim().isEmpty()) {
                compositeDocument.addElement(new TextElement(text));
                outputArea.append("Добавлен текст: " + text + "\n");
                textField.setText("");
            }
        } else if (command.equals("Добавить графику")) {
            String graphic = graphicField.getText();
            if (!graphic.trim().isEmpty()) {
                compositeDocument.addElement(new GraphicElement(graphic));
                outputArea.append("Добавлена графика: " + graphic + "\n");
                graphicField.setText("");
            }
        } else if (command.equals("Отобразить документ")) {
            outputArea.append("\n" + compositeDocument.render() + "\n");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
