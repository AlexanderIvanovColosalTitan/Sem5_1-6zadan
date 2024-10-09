import java.util.ArrayList;
import java.util.List;

public class CompositeDocument implements DocumentElement {
    private List<DocumentElement> elements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        elements.add(element);
    }

    public void removeElement(DocumentElement element) {
        elements.remove(element);
    }

    @Override
    public String render() {
        StringBuilder result = new StringBuilder("Документ:\n");
        for (DocumentElement element : elements) {
            result.append(" - ").append(element.render()).append("\n");
        }
        return result.toString();
    }
}
