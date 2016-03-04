package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-04 at 18
 */
public class Context<T> {

    private boolean completed;
    private T value;

    public Context(T initialValue) {
        this.value = initialValue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
