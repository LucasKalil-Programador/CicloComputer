package br.com.ciclocomputador;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.ciclocomputador.tracker.info.TrackerInfo;

public class LapReport extends Fragment {

    private final TrackerInfo trackerInfo;
    private final int lapNumber;

    public LapReport(TrackerInfo trackerInfo, int lapNumber) {
        this.trackerInfo = trackerInfo;
        this.lapNumber = lapNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lap_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTexts();

        TrackerData trackerData = TrackerData.getInstance();
        trackerData.addTrackerInfo(trackerInfo);
    }

    @SuppressLint("DefaultLocale")
    public void setTexts() {
        TextView lapCountView = getView().findViewById(R.id.lapCount);
        TextView avgView = getView().findViewById(R.id.lapAVGSpeedValue);
        TextView distanceView = getView().findViewById(R.id.lapDistanceValue);
        TextView timeView = getView().findViewById(R.id.lapTimeValue);
        lapCountView.setText(String.format("%d", lapNumber));
        avgView.setText(trackerInfo.getAvgSpeed().toString());
        distanceView.setText(trackerInfo.getDistance().toString());
        timeView.setText(trackerInfo.getElapsedTime().toString());
    }
}