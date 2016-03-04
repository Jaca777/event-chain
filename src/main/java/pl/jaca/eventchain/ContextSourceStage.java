package pl.jaca.eventchain;

import java.time.Duration;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public class ContextSourceStage<T> extends StageChainNode<T> implements Stage<T> {

    public ContextSourceStage() {
        super(new IdentityStage<>());
    }

    @Override
    public T apply(T context) {
        Context<T> c = new Context<>(context);
        while (!c.isCompleted()) {
            this.pass(c);
        }
        return c.getValue();
    }

    @Override
    public boolean isApplied(T t) {
        return true;
    }

}
