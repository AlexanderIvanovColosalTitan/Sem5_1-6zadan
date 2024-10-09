import java.util.HashMap;
import java.util.Map;

public class DocumentManager {
    private Map<String, Document> templates = new HashMap<>();
    private Map<String, Document> createdDocuments = new HashMap<>();

    public void addTemplate(String name, Document document) {
        templates.put(name, document);
    }

    public Document createDocument(String name) {
        Document template = templates.get(name);
        Document newDocument = (template != null) ? template.clone() : null;
        if (newDocument != null) {
            createdDocuments.put(name + " Document", newDocument);
        }
        return newDocument;
    }

    public void deleteTemplate(String name) {
        templates.remove(name);
    }

    public void deleteDocument(String name) {
        createdDocuments.remove(name);
    }

    public String listTemplates() {
        return String.join(", ", templates.keySet());
    }

    public String listDocuments() {
        return String.join(", ", createdDocuments.keySet());
    }
}
