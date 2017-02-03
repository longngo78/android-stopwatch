package longo.com.stopwatch;

public interface Stopwatch {
    void reset();
    void start();
    void stop();

    boolean isStarted();
    long getElapsedTime();
    String getElapsedString();
}
