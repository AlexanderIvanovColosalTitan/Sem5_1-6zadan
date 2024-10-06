import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final DefaultListModel<Composition> compositionListModel = new DefaultListModel<>();
    private final DefaultListModel<Instrument> instrumentListModel = new DefaultListModel<>();
    private final JList<Composition> compositionList = new JList<>(compositionListModel);
    private final JList<Instrument> instrumentList = new JList<>(instrumentListModel);
    private final JTextField compositionNameField = new JTextField();
    private final JSpinner volumeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    private final JSpinner tempoSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));

    public MainFrame() {
        setTitle("Музыкальное приложение");
        setSize(800, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        compositionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        compositionList.addListSelectionListener(e -> updateInstrumentList());

        instrumentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instrumentList.addListSelectionListener(e -> updateSpinnerValues());

        createUI();
        setVisible(true);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createCompositionPanel(), BorderLayout.WEST);
        mainPanel.add(createInstrumentPanel(), BorderLayout.CENTER);
        mainPanel.add(createEditPanel(), BorderLayout.EAST);
        add(mainPanel);
    }

    private JPanel createCompositionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Композиции"));
        panel.add(new JScrollPane(compositionList), BorderLayout.CENTER);
        JButton newButton = new JButton("Создать новую композицию");
        newButton.addActionListener(e -> createNewComposition());
        JButton deleteButton = new JButton("Удалить композицию");
        deleteButton.addActionListener(e -> deleteSelectedComposition());
        panel.add(new JPanel(new GridLayout(1, 2)) {{
            add(newButton); add(deleteButton);
        }}, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createInstrumentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Инструменты"));
        panel.add(new JScrollPane(instrumentList), BorderLayout.CENTER);
        JButton addButton = new JButton("Добавить инструмент");
        addButton.addActionListener(e -> addNewInstrument());
        JButton deleteButton = new JButton("Удалить инструмент");
        deleteButton.addActionListener(e -> deleteSelectedInstrument());
        panel.add(new JPanel(new GridLayout(1, 2)) {{
            add(addButton); add(deleteButton);
        }}, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createEditPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Редактирование"));
        panel.add(new JLabel("Громкость:")); panel.add(volumeSpinner);
        panel.add(new JLabel("Темп:")); panel.add(tempoSpinner);
        return panel;
    }

    private void updateInstrumentList() {
        Composition composition = compositionList.getSelectedValue();
        if (composition != null) {
            instrumentListModel.clear();
            for (Instrument instrument : composition.getInstruments()) {
                instrumentListModel.addElement(instrument);
            }
            compositionNameField.setText(composition.getName());
        }
    }

    private void updateSpinnerValues() {
        Instrument instrument = instrumentList.getSelectedValue();
        if (instrument != null) {
            volumeSpinner.setValue(instrument.getVolume());
            tempoSpinner.setValue(instrument.getTempo());
        }
    }

    private void createNewComposition() {
        String name = JOptionPane.showInputDialog(this, "Введите название композиции:");
        if (name != null && !name.trim().isEmpty()) {
            Composition composition = new Composition(name);
            compositionListModel.addElement(composition);
            compositionList.setSelectedValue(composition, true);
        }
    }

    private void deleteSelectedComposition() {
        int selectedIndex = compositionList.getSelectedIndex();
        if (selectedIndex != -1) {
            compositionListModel.remove(selectedIndex);
        }
    }

    private void addNewInstrument() {
        Composition composition = compositionList.getSelectedValue();
        if (composition != null) {
            String instrumentType = (String) JOptionPane.showInputDialog(this, "Выберите инструмент:", "Добавить инструмент",
                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Гитара", "Фортепиано", "Скрипка"}, "Гитара");
            if (instrumentType != null) {
                Instrument instrument = switch (instrumentType) {
                    case "Гитара" -> new Guitar();
                    case "Фортепиано" -> new Fortepiano();
                    case "Скрипка" -> new Skripka();
                    default -> null;
                };
                if (instrument != null) {
                    composition.addInstrument(instrument);
                    instrumentListModel.addElement(instrument);
                    instrumentList.setSelectedValue(instrument, true);
                }
            }
        }
    }

    private void deleteSelectedInstrument() {
        Composition composition = compositionList.getSelectedValue();
        Instrument instrument = instrumentList.getSelectedValue();
        if (composition != null && instrument != null) {
            composition.removeInstrument(instrument);
            instrumentListModel.removeElement(instrument);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}