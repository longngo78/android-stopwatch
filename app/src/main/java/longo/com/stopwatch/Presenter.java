package longo.com.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Presenter {
    private static final String TAG = Presenter.class.getSimpleName();
    private static final int STEP_DELAY = 10;

    private Activity mActivity;
    private Stopwatch mStopwatch;

    // UI Views
    private final FloatingActionButton mStartButton;
    private final FloatingActionButton mResetButton;
    private final TextView mElapsedTimeText;
    private final TextView mMsText;

    // handler
    private Handler mHandler;
    private Runnable mTimeRunnable = new Runnable() {
        @Override
        public void run() {
            // update text
            updateText();

            // next
            mHandler.postDelayed(mTimeRunnable, STEP_DELAY);
        }
    };

    public Presenter(Activity activity, Stopwatch stopwatch) {
        mActivity = activity;
        mStopwatch = stopwatch;

        mHandler = new Handler();

        mStartButton = (FloatingActionButton) mActivity.findViewById(R.id.fabStart);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickStart();
            }
        });

        mResetButton = (FloatingActionButton) mActivity.findViewById(R.id.fabReset);
        mResetButton.setEnabled(false);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickReset();
            }
        });

        mElapsedTimeText = (TextView) mActivity.findViewById(R.id.tv_elapsed_time);
        mMsText = (TextView) mActivity.findViewById(R.id.tv_ms);
    }

    private void OnClickStart() {
        Log.d(TAG, "OnClickStart()");

        if (!mStopwatch.isStarted()) {
            mStopwatch.start();

            // local callback
            onStartTime();
        } else {
            mStopwatch.stop();

            // local callback
            onStopTime();
        }

        //Snackbar.make(mStartButton, "Hello there!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    private void OnClickReset() {
        Log.d(TAG, "OnClickReset()");

        mStopwatch.reset();

        if (!mStopwatch.isStarted()) {
            mResetButton.setEnabled(false);
            // update text
            updateText();
        }
    }

    private void onStartTime() {
        mResetButton.setEnabled(true);
        // change icon
        mStartButton.setImageResource(android.R.drawable.ic_media_pause);

        // keep updating text
        mHandler.postDelayed(mTimeRunnable, STEP_DELAY);
    }

    private void onStopTime() {
        // change icon
        mStartButton.setImageResource(android.R.drawable.ic_media_play);

        // stop updating text
        mHandler.removeCallbacks(mTimeRunnable);
    }

    /**
     * Update all the text views
     */
    private void updateText() {
        // change text
        mElapsedTimeText.setText(mStopwatch.getElapsedString());
        mMsText.setText(String.format("%03d", mStopwatch.getElapsedTime() % 1000));
    }
}
