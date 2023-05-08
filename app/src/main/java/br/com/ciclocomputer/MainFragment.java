package br.com.ciclocomputer;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment {

    private Button startAndPauseButton, lapButton, saveButton;
    private GPSTracker tracker;
    private boolean paused = true;

    public MainFragment() { /* Required empty public constructor */ }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addClickListeners(view);
        startMeterUpdater();
    }

    private void addClickListeners(View view) {
        startAndPauseButton = (Button) view.findViewById(R.id.start_pause_button);
        startAndPauseButton.setOnClickListener(new OnClickStartPauseButtonListener());

        lapButton = (Button) view.findViewById(R.id.lap_button);
        lapButton.setOnClickListener(new OnLapClickButtonListener());

        saveButton = (Button) view.findViewById(R.id.save_buttton);
        saveButton.setOnClickListener(new OnSaveClickButtonListener());
    }

    private void startMeterUpdater() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            TextView timeText = getView().findViewById(R.id.time_meter_value);
            TextView distanceText = getView().findViewById(R.id.ditance_meter_value);
            TextView speedText = getView().findViewById(R.id.speed_meter_value);
            TextView maxSpeedText = getView().findViewById(R.id.max_speed_meter_value);
            TextView avgSpeedText = getView().findViewById(R.id.avg_speed_meter_value);

            @Override
            public void run() {
                    if(tracker != null) {
                        GPSTracker.GPSTrackerInfo info = tracker.getInfo();
                        timeText.setText(info.elapsedTime);
                        distanceText.setText(info.distance);
                        speedText.setText(info.speed);
                        maxSpeedText.setText(info.maxSpeed);
                        avgSpeedText.setText(info.avgSpeed);
                    }
                    handler.postDelayed(this, 0);
            }
        });
    }

    // Start and Pause click

    private class OnClickStartPauseButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(paused) {
                onStartClick();
            } else {
                onPauseClick();
            }
            Button button = (Button) v;
            button.setText(paused? R.string.start_button: R.string.pause_button);
        }
    }


    private void onStartClick() {
        if(tracker == null) {
            tracker = new GPSTracker(getContext());
        }
        if(paused) {
            tracker.startTracking();
        }
        paused = false;
    }

    private void onPauseClick() {
        if(tracker != null) {
            tracker.pauseTracking();
        }
        paused = true;
    }


    // Lap click

    private class OnLapClickButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OnLapClick();
        }
    }

    private void OnLapClick() {

    }


    // Save Click

    private class OnSaveClickButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OnSaveClick();
        }
    }

    private void OnSaveClick() {

    }
}