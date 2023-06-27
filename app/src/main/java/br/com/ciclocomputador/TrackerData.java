package br.com.ciclocomputador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.ciclocomputador.tracker.info.Distance;
import br.com.ciclocomputador.tracker.info.Speed;
import br.com.ciclocomputador.tracker.info.Time;
import br.com.ciclocomputador.tracker.info.TrackerInfo;

public class TrackerData {
    private static TrackerData instance;
    private ArrayList<TrackerInfo> trackerInfoList = new ArrayList<>();


    private TrackerData() {
        // Construtor privado para impedir a criação de instâncias diretas
    }

    public static TrackerData getInstance() {
        if (instance == null) {
            instance = new TrackerData();
        }
        return instance;
    }

    public int getTrackerInfoCount() {
        return trackerInfoList.size();
    }

    public void addTrackerInfo(TrackerInfo trackerInfo) {
        trackerInfoList.add(trackerInfo);
    }
}