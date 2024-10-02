import java.util.ArrayList;

public class ProjectManagementSystem implements Subject {
    private ArrayList<Observer> observers;
    private String task;

    public ProjectManagementSystem() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String task) {
        for (Observer observer : observers) {
            observer.update(task);
        }
    }

    public void setTask(String task) {
        this.task = task;
        notifyObservers(task);
    }
}
