package br.com.ciclocomputador;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.ciclocomputador.tracker.Tracker;
import br.com.ciclocomputador.tracker.info.TrackerInfo;


public class MainMeterFragment extends Fragment {

    private TextView timeView, speedView, avgSpeedView, maxSpeedView, distanceView;
    private Handler updater = new Handler();
    private Tracker tracker;
    private static final int updateDelay = 10;
    private boolean paused = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_meter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.stopButton).setOnClickListener(this::onStopButtonClick);
        view.findViewById(R.id.startAndPauseButton).setOnClickListener(this::onStartAndPauseButtonClick);
        view.findViewById(R.id.lapButton).setOnClickListener(this::onLapButtonClick);
        tracker = new Tracker(getContext());
        updater.post(this::updateScreen);
    }

    public void updateScreen() {
        getMeterViews();
        TrackerInfo info = tracker.getTrackerInfo();
        timeView.setText(info.getElapsedTime().toString());
        speedView.setText(info.getCurrentSpeed().toString());
        avgSpeedView.setText(info.getAvgSpeed().toString());
        maxSpeedView.setText(info.getMaxSpeed().toString());
        distanceView.setText(info.getDistance().toString());
        updater.postDelayed(this::updateScreen, updateDelay);
    }

    public void getMeterViews() {
        if(timeView == null) {
            View view = getView();
            timeView = view.findViewById(R.id.timeValue);
            speedView = view.findViewById(R.id.speedValue);
            avgSpeedView = view.findViewById(R.id.avgSpeedValue);
            maxSpeedView = view.findViewById(R.id.maxSpeedValue);
            distanceView = view.findViewById(R.id.distanceValue);
        }
    }

    private void onStopButtonClick(View v) {

    }

    private void onStartAndPauseButtonClick(View v) {
        ImageButton button = (ImageButton) v;
        if(paused) {
            onStartButtonClick(v);
        } else {
            onPauseButtonClick(v);
        }
        paused = !paused;
        button.setImageResource(paused? R.drawable.play: R.drawable.pause);
    }

    private void onStartButtonClick(View v) {
        tracker.start();
    }

    private void onPauseButtonClick(View v) {
        tracker.pause();
    }

    private void onLapButtonClick(View v) {

    }
}