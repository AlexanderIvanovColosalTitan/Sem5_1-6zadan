class Bicycle extends Transport {
    @Override
    protected void prepare() {
        System.out.println("Подготовка велосипеда...");
    }

    @Override
    protected void start() {
        System.out.println("Велосипед готов к движению.");
    }

    @Override
    protected void drive() {
        System.out.println("Велосипед едет по тропинке.");
    }

    @Override
    public void stop() {
        System.out.println("Велосипед остановлен.");
    }
}