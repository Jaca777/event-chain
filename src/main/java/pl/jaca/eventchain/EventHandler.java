package pl.jaca.eventchain;

import pl.jaca.eventchain.EventChain;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public interface EventHandler<T> {

    void onNext(T t);

}
