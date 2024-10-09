public class GraphicElement implements DocumentElement {
    private String graphic;

    public GraphicElement(String graphic) {
        this.graphic = graphic;
    }

    @Override
    public String render() {
        return "Графический: " + graphic;
    }
}
