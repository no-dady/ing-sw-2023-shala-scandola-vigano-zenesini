package it.polimi.observer;

/**
 * <p>Observer interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface Observer<T> {

    /**
     * <p>update.</p>
     *
     * @param message a T object
     */
    void update(T message);

}
