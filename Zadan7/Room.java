public class Room {
    private double area;
    private String wallColor;
    private String furniture;

    public Room(double area, String wallColor, String furniture) {
        this.area = area;
        this.wallColor = wallColor;
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "Комната: \n" +
                "Площадь: " + area + " кв.м.\n" +
                "Цвет стен: " + wallColor + "\n" +
                "Мебель: " + furniture;
    }
}
