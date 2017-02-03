package longo.com.stopwatch;

import android.util.Log;

public class StopwatchImpl implements Stopwatch {
    private static final String TAG = StopwatchImpl.class.getSimpleName();

    private long mStartTime;
    private boolean mStarted;
    private long mElapasedTime;

    @Override
    public void reset() {
        mStartTime = System.currentTimeMillis();
        mElapasedTime = 0;
    }

    @Override
    public void start() {
        if (mStarted) {
            // already started
            Log.w(TAG, "It's already started!");
            return;
        }

        mStarted = true;
        mStartTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        if (!mStarted) {
            // already started
            Log.w(TAG, "It's already stopped!");
            return;
        }

        mStarted = false;
        mElapasedTime += System.currentTimeMillis() - mStartTime;
    }

    @Override
    public boolean isStarted() {
        return mStarted;
    }

    @Override
    public long getElapsedTime() {
        if (mStarted) {
            return mElapasedTime + System.currentTimeMillis() - mStartTime;
        }

        return mElapasedTime;
    }

    @Override
    public String getElapsedString() {
        long ms = getElapsedTime();
        long s = ms / 1000;
        long m = s / 60;
        long h = m / 60;
        return String.format("%02d : %02d : %02d", h, m % 60, s % 60);
    }
}
