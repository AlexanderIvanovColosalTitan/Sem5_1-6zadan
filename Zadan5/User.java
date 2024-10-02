public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String task) {
        System.out.println(name + ", новая задача: " + task);
    }
}
