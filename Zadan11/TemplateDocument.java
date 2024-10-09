public class TemplateDocument implements Document {
    private String content;

    public TemplateDocument(String content) {
        this.content = content;
    }

    @Override
    public Document clone() {
        return new TemplateDocument(content);
    }

    @Override
    public String getContent() {
        return content;
    }
}
