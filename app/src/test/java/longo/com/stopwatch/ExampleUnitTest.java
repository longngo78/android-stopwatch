package longo.com.stopwatch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testStopwatch() throws Exception {
        StopwatchImpl stopwatch = new StopwatchImpl();

        // test start
        stopwatch.start();
        final int seconds = 3;
        Thread.sleep(seconds * 1000); // ms
        assertEquals(seconds, stopwatch.getElapsedTime() / 1000);

        // test stop
        stopwatch.stop();
        Thread.sleep(1000);
        assertEquals(seconds, stopwatch.getElapsedTime() / 1000);

        // test start again
        stopwatch.start();
        Thread.sleep(seconds * 1000); // ms
        assertEquals(seconds * 2, stopwatch.getElapsedTime() / 1000);

        // test reset
        stopwatch.reset();
        assertEquals(0, stopwatch.getElapsedTime());

    }
}