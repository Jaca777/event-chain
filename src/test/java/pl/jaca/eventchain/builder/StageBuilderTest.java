package pl.jaca.eventchain.builder;

import org.junit.Test;
import pl.jaca.eventchain.ContextSourceStage;
import pl.jaca.eventchain.Stage;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 12
 */
public class StageBuilderTest {

    @Test
    public void testWhen() throws Exception {
        Stage<Integer> handler1 = StageBuilder.when((Integer i) -> i < 3).or(i -> i < 2).then(i -> i + 1);
        Stage<Integer> handler2 = StageBuilder.when((Integer i) -> i < 5).and(i -> i < 4).then(i -> i + 1);
        int r1 = handler1.apply(2);
        assertEquals(3, r1);
        assertTrue(handler1.isApplied(3));
        assertTrue(handler2.isApplied(4));
    }
}