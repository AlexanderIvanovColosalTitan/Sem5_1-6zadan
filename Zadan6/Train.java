class Train extends Transport {
    @Override
    protected void prepare() {
        System.out.println("Подготовка поезда...");
    }

    @Override
    protected void start() {
        System.out.println("Поезд отправляется.");
    }

    @Override
    protected void drive() {
        System.out.println("Поезд движется по рельсам.");
    }

    @Override
    public void stop() {
        System.out.println("Поезд остановлен на станции.");
    }
}