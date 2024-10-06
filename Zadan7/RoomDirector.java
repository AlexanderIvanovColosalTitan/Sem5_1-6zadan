public class RoomDirector {
    private RoomBuilder builder;

    public RoomDirector(RoomBuilder builder) {
        this.builder = builder;
    }

    public Room constructRoom(double area, String color, String furniture) {
        builder.setArea(area);
        builder.setWallColor(color);
        builder.setFurniture(furniture);
        return builder.build();
    }
}
