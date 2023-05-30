package br.com.ciclocomputador.tracker;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

import br.com.ciclocomputador.tracker.info.Distance;
import br.com.ciclocomputador.tracker.info.Speed;
import br.com.ciclocomputador.tracker.info.TrackerInfo;

public class TrackerLocationListener implements LocationListener {

    private Timer timer;
    private Location lastLocation;

    private double speed = 0, distance = 0, avgSpeed = 0, maxSpeed = 0;

    public TrackerLocationListener(Timer timer) {
        this.timer = timer;
    }

    /**
     * @param currentLocation the updated location
     */
    @Override
    public void onLocationChanged(@NonNull Location currentLocation) {
        if (lastLocation != null) {
            if(currentLocation.hasSpeed()) {
                speed = currentLocation.getSpeed();
            } else {
                double distance = lastLocation.distanceTo(currentLocation);
                long timeDifference = currentLocation.getTime() - lastLocation.getTime();
                speed = distance / (timeDifference / 1000f);
            }
            distance += lastLocation.distanceTo(currentLocation);
            avgSpeed = distance / (timer.getElapsedTime() / 1_000_000_000);
            if(speed > maxSpeed && speed < 10_000) maxSpeed = speed;
        }
        lastLocation = currentLocation;
    }

    public TrackerInfo getTrackerInfo() {
        return new TrackerInfo(
                new Speed(speed),
                new Speed(avgSpeed),
                new Speed(maxSpeed),
                new Distance(distance),
                timer.getTime(),
                lastLocation);
    }

    // Getters and Setters

    public Location getLastLocation() {
        return lastLocation;
    }

    public void resetLastLocation() {
        lastLocation = null;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
