package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public class EventSource<T> extends EventChainNode<T> implements EventHandler<T> {

    public EventSource() {
        super(e -> {});
    }

    @Override
    public void onNext(T event) {
        this.handle(event);
    }
}
