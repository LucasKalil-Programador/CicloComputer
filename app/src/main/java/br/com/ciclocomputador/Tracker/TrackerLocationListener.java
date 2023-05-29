package br.com.ciclocomputador.Tracker;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

import java.util.List;

public class TrackerLocationListener implements LocationListener {


    /**
     * @param location the updated location
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    /**
     * @param locations the location list
     */
    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }
}
