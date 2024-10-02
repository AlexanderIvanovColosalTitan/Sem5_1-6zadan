public class Vulkan implements GraphicsLibrary {
    @Override
    public void initialize() {
        System.out.println("Инициализация Vulkan...");
    }

    @Override
    public void render() {
        System.out.println("Рендеринг с помощью Vulkan...");
    }

    @Override
    public void cleanup() {
        System.out.println("Очистка Vulkan...");
    }
}
