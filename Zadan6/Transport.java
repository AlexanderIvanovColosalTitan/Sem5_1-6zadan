abstract class Transport {
    public final void move() {
        prepare();
        start();
        drive();
    }

    protected abstract void prepare();
    protected abstract void start();
    protected abstract void drive();
    public abstract void stop();
}