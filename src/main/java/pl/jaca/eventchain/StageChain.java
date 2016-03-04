package pl.jaca.eventchain;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 00
 */
public abstract class StageChain<T> {

    /**
     * @param handler
     * @return
     */
    public abstract StageChain<T> add(Stage<T> handler);

    /**
     * @param event
     */
    protected abstract void pass(Context<T> event);

    public static <T> StageChain<T> of(Stage<T> handler) {
        return new StageChainNode<>(handler);
    }

    public abstract StageChain<T> combine(StageChain<T> stageChain);
}
