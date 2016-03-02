package pl.jaca.eventchain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 13
 */
public class EventSourceTest {

    @Test
    public void testOnNext() throws Exception {
        final boolean[] flag = {false};
        EventSource<Integer> source = new EventSource<>();
        source.add(e -> flag[0] = true);
        source.onNext(3);
        assertTrue(flag[0]);
    }
}