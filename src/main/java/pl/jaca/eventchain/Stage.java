package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public interface Stage<T> {

    T apply(T t);

    boolean isApplied(T t);
}
