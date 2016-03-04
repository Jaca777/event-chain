package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-04 at 17
 */
public class IdentityStage<T> implements Stage<T> {
    @Override
    public T apply(T t) {
        return t;
    }

    @Override
    public boolean isApplied(T t) {
        return true;
    }
}
