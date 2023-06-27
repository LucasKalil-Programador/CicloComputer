package br.com.ciclocomputador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import br.com.ciclocomputador.tracker.Timer;
import br.com.ciclocomputador.tracker.Tracker;
import br.com.ciclocomputador.tracker.TrackerLocationListener;
import br.com.ciclocomputador.tracker.info.TrackerInfo;


public class MainMeterFragment extends Fragment {

    private TextView timeView, speedView, avgSpeedView, maxSpeedView, distanceView;
    private Handler updater = new Handler();
    private Tracker tracker;
    private int updateDelay = Configs.batteryEconomy? 250:0;
    private boolean paused = true;

    private Timer lapTimer;
    private TrackerLocationListener lapListener;

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
        if(lapListener != null) lapListener.onLocationChanged(info.getLastLocation());
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
        tracker = new Tracker(getContext());
        lapListener = null;
        lapTimer = null;
        paused = true;
        ImageButton button = (ImageButton)  getView().findViewById(R.id.startAndPauseButton);
        button.setImageResource(paused? R.drawable.play: R.drawable.pause);
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
        if(lapListener == null || lapTimer == null) resetLap();
        lapTimer.start();
        tracker.start();
    }

    private void onPauseButtonClick(View v) {
        tracker.pause();
        lapTimer.pause();
    }

    private void onLapButtonClick(View v) {
        if(lapListener != null) {
            LinearLayout linearLayout = getView().findViewById(R.id.lapReports);
            LapReport lapReport = new LapReport(lapListener.getTrackerInfo(), linearLayout.getChildCount() + 1);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(linearLayout.getId(), lapReport)
                    .commit();

            ScrollView scrollView = getView().findViewById(R.id.lapReportsScroll);
            scrollView.postDelayed(() -> scrollView.fullScroll(View.FOCUS_DOWN), 500);
            resetLap();
            if(!paused) lapTimer.start();
        }
    }

    private void resetLap() {
        lapTimer = new Timer();
        lapListener = new TrackerLocationListener(lapTimer);
    }
}