package pl.jaca.eventchain;

import org.junit.Test;
import pl.jaca.eventchain.builder.StageBuilder;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 13
 */
public class ContextSourceStageTest {

    @Test
    public void testApply() throws Exception {
        StringBuilder log = new StringBuilder();
        ContextSourceStage<Integer> source = new ContextSourceStage<>();
        source.add(
                StageBuilder.<Integer>when(i -> i < 20).then(i -> i + 1)
        ).add(
                StageBuilder.<Integer>when(i -> i < 20).or(i -> i < 21).then(i -> {
                    log.append(i);
                    return i + 1;
                })
        );
        int result = source.apply(3);
        assertEquals(21, result);
        assertEquals("20", log.toString());
    }
}