package pl.jaca.eventchain.builder;

import org.junit.Test;
import pl.jaca.eventchain.EventChain;
import pl.jaca.eventchain.EventSource;

import static org.junit.Assert.*;

/**
 * @author Jaca777
 *         Created 2016-03-02 at 12
 */
public class EventHandlerBuilderTest {

    @Test
    public void testWhen() throws Exception {
        {
            EventHandlerBuilder<Integer> builder = new EventHandlerBuilder<>();
            EventSource<Integer> source = new EventSource<>();
            source.add(builder.when(() -> System.currentTimeMillis() % 2 == 0)
                    .then(System.out::println)
                    .otherwise(System.err::println));

            source.onNext(3);
        }

        // lub

        {
            EventHandlerBuilder<Integer> builder = new EventHandlerBuilder<>();
            EventSource<Integer> source = new EventSource<>();
            EventChain<Integer> eventChain = EventChain.of(builder.when(() -> System.currentTimeMillis() % 2 == 0)
                    .then(System.out::println));
            source.combine(eventChain);

            source.onNext(4);
        }
    }
}