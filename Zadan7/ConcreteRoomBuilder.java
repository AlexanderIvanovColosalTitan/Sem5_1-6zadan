public class ConcreteRoomBuilder implements RoomBuilder {
    private double area;
    private String wallColor;
    private String furniture;

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public void setWallColor(String color) {
        this.wallColor = color;
    }

    @Override
    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    @Override
    public Room build() {
        return new Room(area, wallColor, furniture);
    }
}
