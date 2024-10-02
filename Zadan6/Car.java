class Car extends Transport {
    @Override
    protected void prepare() {
        System.out.println("Подготовка автомобиля...");
    }

    @Override
    protected void start() {
        System.out.println("Автомобиль заведен.");
    }

    @Override
    protected void drive() {
        System.out.println("Автомобиль едет по дороге.");
    }

    @Override
    public void stop() {
        System.out.println("Автомобиль остановлен.");
    }
}