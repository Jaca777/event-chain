package pl.jaca.eventchain.builder;

import pl.jaca.eventchain.EventHandler;

import java.util.concurrent.Callable;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class ConditionBuilder<T> {
    private Callable<Boolean> condition;

    public ConditionBuilder(Callable<Boolean> condition) {
        this.condition = condition;
    }

    public ConditionBuilder<T> and(Callable<Boolean> condition) {
        return new ConditionBuilder<>(() -> this.condition.call() && condition.call());
    }

    public ConditionBuilder<T> or(Callable<Boolean> condition) {
        return new ConditionBuilder<>(() -> this.condition.call() || condition.call());
    }

    public ElseBuilder<T> then(EventHandler<T> handler) {
        return new ElseBuilder<>(handler, condition);
    }
}
