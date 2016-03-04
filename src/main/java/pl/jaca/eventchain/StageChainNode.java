package pl.jaca.eventchain;

import java.util.function.Function;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 11
 */
public class StageChainNode<T> extends StageChain<T> {

    private Stage<T> stage;
    private StageChain<T> tail;

    public StageChainNode(Stage<T> stage) {
        this.stage = stage;
    }

    @Override
    public StageChain<T> add(Stage<T> stage) {
        if (this.tail == null) this.tail = new StageChainNode<>(stage);
        else tail.add(stage);
        return this;
    }

    @Override
    protected void pass(Context<T> context) {
        T value = context.getValue();
        if (stage.isApplied(value)) {
            if (this.tail == null) context.complete();
            else this.tail.pass(context);
        } else {
            T newValue = stage.apply(value);
            context.setValue(newValue);
        }
    }


    @Override
    public StageChain<T> combine(StageChain<T> stageChain) {
        checkForCycle(stageChain);
        if (this.tail == null) {
            this.tail = stageChain;
        } else tail.combine(stageChain);
        return this;
    }

    private void checkForCycle(StageChain<T> stageChain) {
        if (stageChain instanceof StageChainNode) {
            boolean cycleExists = ((StageChainNode<T>) stageChain).exists(node -> node == this);
            if (cycleExists) throw new RuntimeException("Cycle in event chain detected.");
        }
    }

    private boolean exists(Function<StageChainNode<T>, Boolean> cond) {
        if (cond.apply(this)) return true;
        else if (tail != null && tail instanceof StageChainNode)
            return cond.apply((StageChainNode<T>) tail);
        else return false;
    }
}
