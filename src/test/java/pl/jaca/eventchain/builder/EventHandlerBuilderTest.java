package pl.jaca.eventchain.builder;

import org.junit.Test;
import pl.jaca.eventchain.EventChain;
import pl.jaca.eventchain.EventHandler;
import pl.jaca.eventchain.EventSource;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 12
 */
public class EventHandlerBuilderTest {

    @Test
    public void testWhen() throws Exception {
        StringBuilder log = new StringBuilder();
        final boolean[] a = {false, false, true};
        EventHandlerBuilder<Integer> builder = new EventHandlerBuilder<>();
        EventHandler<Integer> handler1 = builder.when(() -> a[0]).then(log::append).otherwise(i -> log.append(i + 1));
        EventHandler<Integer> handler2 = builder.when(() -> a[1]).and(() -> a[2]).then(log::append);
        handler1.onNext(2);
        a[0] = true;
        handler1.onNext(2);
        handler2.onNext(4);
        a[1] = true;
        handler2.onNext(5);
        assertEquals("325", log.toString());

    }
}