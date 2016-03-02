package pl.jaca.eventchain;

import java.awt.*;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class EventChainNode<T> extends EventChain<T> {

    private EventHandler<? super T> eventHandler;
    private EventChain<T> tail;

    public EventChainNode(EventHandler<? super T> handler) {
        this.eventHandler = handler;
    }

    @Override
    public EventChain<T> add(EventHandler<T> handler) {
        if (this.tail == null) this.tail = new EventChainNode<>(handler);
        else tail.add(handler);
        return this;
    }

    @Override
    protected void handle(T event) {
        eventHandler.onNext(event);
        if(tail != null) tail.handle(event);
    }

    @Override
    public EventChain<T> combine(EventChain<T> eventChain) {
        checkForCycle(eventChain);
        if (this.tail == null) {
            this.tail = eventChain;
        } else tail.combine(eventChain);
        return this;
    }

    private void checkForCycle(EventChain<T> eventChain) {
        if(eventChain instanceof EventChainNode) {
            boolean cycleExists = ((EventChainNode<T>) eventChain).exists(node -> node == this);
            if(cycleExists) throw new RuntimeException("Cycle in event chain detected.");
        }
    }

    private boolean exists(Function<EventChainNode<T>, Boolean> cond) {
        if (cond.apply(this)) return true;
        else if (tail != null && tail instanceof EventChainNode)
            return cond.apply((EventChainNode<T>) tail);
        else return false;
    }
}
