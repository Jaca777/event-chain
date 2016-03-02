package pl.jaca.eventchain;

import org.junit.Test;
import org.omg.PortableInterceptor.Interceptor;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 13
 */
public class EventChainNodeTest {

    @Test
    public void testHandle() throws Exception {
        final boolean[] flag = {false};
        EventChain<Integer> chain = EventChain.of((Integer i) -> flag[0] = true);
        chain.handle(0);
        assertTrue(flag[0]);
    }

    @Test
    public void testAdd() throws Exception {
        StringBuilder log = new StringBuilder();
        EventChain<Integer> chain = EventChain.of(log::append);
        chain.add(e -> log.append(e + 1)).add(e -> log.append(e + 2));
        chain.handle(0);
        assertEquals("012", log.toString());
    }


    @Test
    public void testCombine() throws Exception {
        StringBuilder log = new StringBuilder();
        EventChain<Integer> node1 = EventChain.of(log::append);
        EventChain<Integer> node2 = EventChain.of(e -> log.append(e + 1));
        EventChain<Integer> node3 = EventChain.of(e -> log.append(e + 2));
        node1.combine(node2).combine(node3);
        node1.handle(0);
        node2.handle(1);
        node3.handle(2);
        assertEquals("012234", log.toString());


    }
}