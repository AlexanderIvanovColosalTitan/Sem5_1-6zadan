public class OpenGL implements GraphicsLibrary {
    @Override
    public void initialize() {
        System.out.println("Инициализация OpenGL...");
    }

    @Override
    public void render() {
        System.out.println("Рендеринг с помощью OpenGL...");
    }

    @Override
    public void cleanup() {
        System.out.println("Очистка OpenGL...");
    }
}
