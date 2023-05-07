package observer;

public interface Observable<T> {
    void addObserver(Observer<T> observer);
    void notify(T message);
}