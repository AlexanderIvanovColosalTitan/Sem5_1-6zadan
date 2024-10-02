import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JList<Composition> compositionList;
    private DefaultListModel<Composition> compositionListModel;
    private JTextField compositionNameField;
    private JList<Instrument> instrumentList;
    private DefaultListModel<Instrument> instrumentListModel;
    private JSpinner volumeSpinner;
    private JSpinner tempoSpinner;

    public MainFrame() {
        setTitle("Музыкальное приложение");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        compositionListModel = new DefaultListModel<>();
        compositionList = new JList<>(compositionListModel);
        compositionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        compositionList.addListSelectionListener(e -> {
            Composition composition = compositionList.getSelectedValue();
            if (composition != null) {
                instrumentListModel.clear();
                for (Instrument instrument : composition.getInstruments()) {
                    instrumentListModel.addElement(instrument);
                }
                compositionNameField.setText(composition.getName());
            }
        });

        JButton newCompositionButton = new JButton("Создать новую композицию");
        newCompositionButton.addActionListener(e -> createNewComposition());

        JButton deleteCompositionButton = new JButton("Удалить композицию");
        deleteCompositionButton.addActionListener(e -> deleteSelectedComposition());

        instrumentListModel = new DefaultListModel<>();
        instrumentList = new JList<>(instrumentListModel);
        instrumentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instrumentList.addListSelectionListener(e -> {
            Instrument instrument = instrumentList.getSelectedValue();
            if (instrument != null) {
                volumeSpinner.setValue(instrument.getVolume());
                tempoSpinner.setValue(instrument.getTempo());
            }
        });

        JButton addInstrumentButton = new JButton("Добавить инструмент");
        addInstrumentButton.addActionListener(e -> addNewInstrument());

        JButton deleteInstrumentButton = new JButton("Удалить инструмент");
        deleteInstrumentButton.addActionListener(e -> deleteSelectedInstrument());

        compositionNameField = new JTextField();
        compositionNameField.addActionListener(e -> updateSelectedComposition());

        volumeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        volumeSpinner.addChangeListener(e -> updateSelectedInstrument());

        tempoSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));
        tempoSpinner.addChangeListener(e -> updateSelectedInstrument());

        JPanel compositionPanel = new JPanel(new BorderLayout());
        compositionPanel.setBorder(BorderFactory.createTitledBorder("Композиции"));
        compositionPanel.add(new JScrollPane(compositionList), BorderLayout.CENTER);

        JPanel compositionButtonPanel = new JPanel(new GridLayout(1, 2));
        compositionButtonPanel.add(newCompositionButton);
        compositionButtonPanel.add(deleteCompositionButton);
        compositionPanel.add(compositionButtonPanel, BorderLayout.SOUTH);

        JPanel instrumentPanel = new JPanel(new BorderLayout());
        instrumentPanel.setBorder(BorderFactory.createTitledBorder("Инструменты"));
        instrumentPanel.add(new JScrollPane(instrumentList), BorderLayout.CENTER);

        JPanel instrumentButtonPanel = new JPanel(new GridLayout(1, 2));
        instrumentButtonPanel.add(addInstrumentButton);
        instrumentButtonPanel.add(deleteInstrumentButton);
        instrumentPanel.add(instrumentButtonPanel, BorderLayout.SOUTH);

        JPanel editPanel = new JPanel(new GridLayout(3, 2));
        editPanel.setBorder(BorderFactory.createTitledBorder("Редактирование"));
        editPanel.add(new JLabel("Громкость:"));
        editPanel.add(volumeSpinner);
        editPanel.add(new JLabel("Темп:"));
        editPanel.add(tempoSpinner);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(compositionPanel, BorderLayout.WEST);
        mainPanel.add(instrumentPanel, BorderLayout.CENTER);
        mainPanel.add(editPanel, BorderLayout.EAST);

        add(mainPanel);
        setVisible(true);
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
            String[] options = {"Гитара", "Фортепиано", "Скрипка"};
            String instrumentType = (String) JOptionPane.showInputDialog(this, "Выберите инструмент:",
                    "Добавить инструмент", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            Instrument instrument = null;

            if (instrumentType != null) {
                switch (instrumentType) {
                    case "Гитара":
                        instrument = new Guitar();
                        break;
                    case "Фортепиано":
                        instrument = new Fortepiano();
                        break;
                    case "Скрипка":
                        instrument = new Skripka();
                        break;
                }
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

    private void updateSelectedComposition() {
        Composition composition = compositionList.getSelectedValue();
        if (composition != null) {
            composition.setName(compositionNameField.getText());
            compositionList.repaint();
        }
    }

    private void updateSelectedInstrument() {
        Composition composition = compositionList.getSelectedValue();
        Instrument instrument = instrumentList.getSelectedValue();
        if (composition != null && instrument != null) {
            instrument.setVolume((int) volumeSpinner.getValue());
            instrument.setTempo((int) tempoSpinner.getValue());
            instrumentList.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

