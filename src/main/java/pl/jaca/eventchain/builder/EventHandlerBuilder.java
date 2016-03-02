package pl.jaca.eventchain.builder;

import pl.jaca.eventchain.EventHandler;

import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class EventHandlerBuilder<T> {
    public ConditionBuilder<T> when(Callable<Boolean> condition){
        return new ConditionBuilder<>(condition);
    }
}
