import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private JFrame frame;
    private JTextField templateNameField;
    private JTextField documentNameField;
    private JTextArea outputArea;
    private DocumentManager documentManager;

    public Main() {
        documentManager = new DocumentManager();

        frame = new JFrame("Управление шаблонами документов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Имя шаблона:"));
        templateNameField = new JTextField();
        inputPanel.add(templateNameField);

        JButton addButton = new JButton("Добавить шаблон");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        JButton deleteTemplateButton = new JButton("Удалить шаблон");
        deleteTemplateButton.addActionListener(this);
        inputPanel.add(deleteTemplateButton);

        inputPanel.add(new JLabel("Имя документа:"));
        documentNameField = new JTextField();
        inputPanel.add(documentNameField);

        JButton createButton = new JButton("Создать документ");
        createButton.addActionListener(this);
        inputPanel.add(createButton);

        JButton deleteDocumentButton = new JButton("Удалить документ");
        deleteDocumentButton.addActionListener(this);
        inputPanel.add(deleteDocumentButton);

        JButton listTemplatesButton = new JButton("Список шаблонов");
        listTemplatesButton.addActionListener(this);
        inputPanel.add(listTemplatesButton);

        JButton listDocumentsButton = new JButton("Список документов");
        listDocumentsButton.addActionListener(this);
        inputPanel.add(listDocumentsButton);

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
        String templateName = templateNameField.getText();
        String documentName = documentNameField.getText();

        if (command.equals("Добавить шаблон")) {
            String content = JOptionPane.showInputDialog(frame, "Введите содержание шаблона:");
            if (content != null && !content.trim().isEmpty()) {
                TemplateDocument template = new TemplateDocument(content);
                documentManager.addTemplate(templateName, template);
                outputArea.append("Шаблон '" + templateName + "' добавлен.\n");
            }
        } else if (command.equals("Удалить шаблон")) {
            documentManager.deleteTemplate(templateName);
            outputArea.append("Шаблон '" + templateName + "' удален.\n");
        } else if (command.equals("Создать документ")) {
            Document newDocument = documentManager.createDocument(templateName);
            if (newDocument != null) {
                outputArea.append("Создан документ на основе шаблона '" + templateName + "':\n");
                outputArea.append(newDocument.getContent() + "\n");
            } else {
                outputArea.append("Шаблон '" + templateName + "' не найден.\n");
            }
        } else if (command.equals("Удалить документ")) {
            documentManager.deleteDocument(documentName);
            outputArea.append("Документ '" + documentName + "' удален.\n");
        } else if (command.equals("Список шаблонов")) {
            String templates = documentManager.listTemplates();
            outputArea.append("Доступные шаблоны: " + templates + "\n");
        } else if (command.equals("Список документов")) {
            String documents = documentManager.listDocuments();
            outputArea.append("Созданные документы: " + documents + "\n");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
