package br.com.ciclocomputer.tracker;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

class InternalLocationListener implements LocationListener {

    long pausedTime = -1;
    long startTime = -1;
    private double speed = 0;
    private double maxSpeed = 0;
    private double distance = 0;

    public Location lastLocation;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(lastLocation != null) {
            distance += location.distanceTo(lastLocation);
        } else {
            lastLocation = location;
        }
        if(location.hasSpeed()) {
            speed = location.getSpeed();
        } else {
            speed = location.distanceTo(lastLocation) / ((location.getTime() - location.getTime()) / 1000);
        }
        lastLocation = location;
    }

    public String getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        if(startTime == -1) {
            startTime = currentTime;
            return "00:00:00.00";
        } else {
            long elapsedTime = currentTime - startTime;
            if(pausedTime != -1) elapsedTime -= currentTime - pausedTime;
            long hours = elapsedTime / 3_600_000;
            elapsedTime = elapsedTime % 3_600_000;
            long minutes = elapsedTime / 60_000;
            elapsedTime = elapsedTime % 60_000;
            long seconds = elapsedTime / 1_000;
            elapsedTime = elapsedTime % 1_000;
            long milliseconds = elapsedTime / 10;
            String formatted = String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds);
            return formatted;
        }
    }
    public String getSpeed() {
        double speedInKM = speed * 3.6;
        return String.format("%.1f km/h", speedInKM);
    }
    public String getMaxSpeed() {
        if(maxSpeed < speed) maxSpeed = speed;
        double maxSpeedInKM = maxSpeed * 3.6;
        return String.format("%.1f km/h", maxSpeedInKM);
    }
    public String getAVGSpeed() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        if(pausedTime != -1) elapsedTime -= currentTime - pausedTime;
        long elapsedSeconds = elapsedTime / 1_000;

        double avgSpeed = (distance / elapsedSeconds) * 3.6;
        return String.format("%.1f km/h", avgSpeed);
    }
    public String getDistance() {
        if(distance >= 1000) {
            double realDistance = distance / 1000;
            return String.format("%.0f km", realDistance);
        } else {
            return String.format("%.0f m", distance);
        }
    }

    public GPSInfo getInfo() {
        return new GPSInfo(getElapsedTime(), getDistance(), getSpeed(), getMaxSpeed(), getAVGSpeed());
    }
}
