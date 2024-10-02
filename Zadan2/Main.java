import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private HomeApplianceFactory factory;
    private Refrigerator refrigerator;
    private WashingMachine washingMachine;
    private Microwave microwave;

    private JLabel titleLabel;
    private JComboBox<String> applianceComboBox;
    private JButton operateButton;
    private JLabel statusLabel;

    public Main() {
        factory = new SamsungFactory();
        refrigerator = factory.createRefrigerator();
        washingMachine = factory.createWashingMachine();
        microwave = factory.createMicrowave();

        setTitle("Управление бытовой техникой");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Выберите бытовую технику для управления:");
        applianceComboBox = new JComboBox<>(new String[]{"Холодильник", "Стиральная машина", "Микроволновая печь"});
        operateButton = new JButton("Управлять");
        operateButton.addActionListener(this);
        statusLabel = new JLabel();

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(titleLabel);
        panel.add(applianceComboBox);
        panel.add(operateButton);
        panel.add(statusLabel);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = applianceComboBox.getSelectedIndex();
        HomeAppliance appliance = null;
        switch (choice) {
            case 0:
                appliance = refrigerator;
                break;
            case 1:
                appliance = washingMachine;
                break;
            case 2:
                appliance = microwave;
                break;
        }

        if (appliance != null) {
            appliance.operate();
            statusLabel.setText("Операция выполнена");
        } else {
            statusLabel.setText("Неверный выбор");
        }
    }

    public static void main(String[] args) {
        Main gui = new Main();
        gui.setVisible(true);
    }
}
