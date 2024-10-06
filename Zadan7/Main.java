import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JTextField areaField;
    private JTextField colorField;
    private JTextField furnitureField;
    private JTextArea outputArea;

    public Main() {
        frame = new JFrame("Строитель комнаты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Площадь (кв.м.):"));
        areaField = new JTextField();
        panel.add(areaField);

        panel.add(new JLabel("Цвет стен:"));
        colorField = new JTextField();
        panel.add(colorField);

        panel.add(new JLabel("Мебель:"));
        furnitureField = new JTextField();
        panel.add(furnitureField);

        JButton buildButton = new JButton("Создать комнату");
        buildButton.addActionListener(this);
        panel.add(buildButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double area = Double.parseDouble(areaField.getText());
            String color = colorField.getText();
            String furniture = furnitureField.getText();

            RoomBuilder builder = new ConcreteRoomBuilder();
            RoomDirector director = new RoomDirector(builder);
            Room room = director.constructRoom(area, color, furniture);

            outputArea.setText(room.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Введите корректное значение площади.");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
