public class DirectX implements GraphicsLibrary {
    @Override
    public void initialize() {
        System.out.println("Инициализация DirectX...");
    }

    @Override
    public void render() {
        System.out.println("Рендеринг с помощью DirectX...");
    }

    @Override
    public void cleanup() {
        System.out.println("Очистка DirectX...");
    }
}
