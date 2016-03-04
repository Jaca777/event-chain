package pl.jaca.eventchain.builder;

import pl.jaca.eventchain.Stage;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class ConditionBuilder<T> {
    private Function<T, Boolean> condition;

    public ConditionBuilder(Function<T, Boolean> condition) {
        this.condition = condition;
    }

    public ConditionBuilder<T> and(Function<T, Boolean> condition) {
        return new ConditionBuilder<>((T t) -> this.condition.apply(t) && condition.apply(t));
    }

    public ConditionBuilder<T> or(Function<T, Boolean> condition) {
        return new ConditionBuilder<>((T t) -> this.condition.apply(t) || condition.apply(t));
    }

    public Stage<T> then(Function<T, T> handler) {
        return new Stage<T>() {
            @Override
            public T apply(T t) {
                return handler.apply(t);
            }

            @Override
            public boolean isApplied(T t) {
                return !condition.apply(t);
            }
        };
    }
}
