package pl.jaca.eventchain.builder;

import pl.jaca.eventchain.EventChain;
import pl.jaca.eventchain.EventHandler;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class ElseBuilder<T> implements EventHandler<T> {
    private final EventHandler<T> handler;
    private final Callable<Boolean> condition;

    public ElseBuilder(EventHandler<T> handler, Callable<Boolean> condition) {
        this.handler = handler;
        this.condition = condition;
    }

    @Override
    public void onNext(T t) {
        try {
            if(condition.call()) handler.onNext(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EventHandler<T> otherwise(EventHandler<T> handler){
        return t -> {
            try {
                if (condition.call()) ElseBuilder.this.handler.onNext(t);
                else handler.onNext(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }



}
