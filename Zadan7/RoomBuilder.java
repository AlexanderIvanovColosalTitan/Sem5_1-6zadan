public interface RoomBuilder {
    void setArea(double area);

    void setWallColor(String color);

    void setFurniture(String furniture);

    Room build();
}
