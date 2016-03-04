package pl.jaca.eventchain.builder;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class StageBuilder {
    public static <T> ConditionBuilder<T> when(Function<T, Boolean> condition){
        return new ConditionBuilder<>(condition);
    }


}
