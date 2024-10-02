import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JComboBox<String> transportComboBox;
    private JButton moveButton;
    private JButton stopButton;
    private Transport currentTransport;

    public Main() {
        frame = new JFrame("Управление транспортом");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        String[] transports = {"Автомобиль", "Велосипед", "Поезд"};
        transportComboBox = new JComboBox<>(transports);
        panel.add(transportComboBox);

        moveButton = new JButton("Двигаться");
        moveButton.addActionListener(this);
        panel.add(moveButton);

        stopButton = new JButton("Остановить");
        stopButton.addActionListener(this);
        panel.add(stopButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTransport = (String) transportComboBox.getSelectedItem();

        if (e.getSource() == moveButton) {
            switch (selectedTransport) {
                case "Автомобиль":
                    currentTransport = new Car();
                    break;
                case "Велосипед":
                    currentTransport = new Bicycle();
                    break;
                case "Поезд":
                    currentTransport = new Train();
                    break;
            }

            if (currentTransport != null) {
                currentTransport.move();
            }
        } else if (e.getSource() == stopButton) {
            if (currentTransport != null) {
                currentTransport.stop();
            } else {
                System.out.println("Сначала выберите транспорт и начните движение.");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
