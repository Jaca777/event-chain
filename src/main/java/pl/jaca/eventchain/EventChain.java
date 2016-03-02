package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public abstract class EventChain<T> {

    /**
     * @param handler
     * @return
     */
    public abstract EventChain<T> add(EventHandler<T> handler);

    /**
     * @param event
     */
    protected abstract void handle(T event);

    public static <T> EventChain<T> of(EventHandler<T> handler) {
        return new EventChainNode<>(handler);
    }

    public abstract EventChain<T> combine(EventChain<T> eventChain);
}
