package it.polimi.observer;

/**
 * <p>Observable interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface Observable<T> {
    /**
     * <p>addObserver.</p>
     *
     * @param observer a {@link it.polimi.observer.Observer} object
     */
    void addObserver(Observer<T> observer);
    /**
     * <p>notify.</p>
     *
     * @param message a T object
     */
    void notify(T message);
}
