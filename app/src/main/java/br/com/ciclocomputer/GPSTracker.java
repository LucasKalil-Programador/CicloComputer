package br.com.ciclocomputer;


import static android.content.Context.LOCATION_SERVICE;
import static android.location.LocationManager.GPS_PROVIDER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class GPSTracker {
    private LocationManager locationManager;
    private InternalLocationListener locationListener = new InternalLocationListener();
    private boolean paused = true;

    private Context context;

    public GPSTracker(Context context) {
        this.context = context;
        if(MainActivity.checkLocationPermissions(this.context)) {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        }
    }

    @SuppressLint("MissingPermission")
    public boolean startTracking() {
        if(MainActivity.checkLocationPermissions(this.context)) {
            locationManager.removeUpdates(locationListener);
            locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
            if(pausedTime != -1) {
                startTime += System.currentTimeMillis() - pausedTime;
                pausedTime = -1;
            }
            paused = false;
            return true;
        }
        return false;
    }

    public void pauseTracking() {
        locationManager.removeUpdates(locationListener);
        pausedTime = System.currentTimeMillis();
        paused = true;
    }

    /* ------------------------------- */

    private long pausedTime = -1;
    private long startTime = -1;
    private double speed = 0;
    private double maxSpeed = 0;
    private double distance = 0;

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

    public GPSTrackerInfo getInfo() {
        return new GPSTrackerInfo(getElapsedTime(), getDistance(), getSpeed(), getMaxSpeed(), getAVGSpeed());
    }

    public static class GPSTrackerInfo {
        public final String elapsedTime;
        public final String distance;
        public final String speed;
        public final String maxSpeed;
        public final String avgSpeed;

        public GPSTrackerInfo(String elapsedTime, String distance, String speed, String maxSpeed, String avgSpeed) {
            this.elapsedTime = elapsedTime;
            this.distance = distance;
            this.speed = speed;
            this.maxSpeed = maxSpeed;
            this.avgSpeed = avgSpeed;
        }
    }

    /* ------------------------------- */

    private class InternalLocationListener implements LocationListener {
        public Location lastLocation;
        public static final double ACCURACY_MOD = 1;

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


            String debug = "Debug\r\nAccuracy: " + location.getAccuracy() + "\r\n";
            debug += "speed Accuracy: " + location.getSpeedAccuracyMetersPerSecond() + "\r\n";
            debug += "distance last -> now: " + location.distanceTo(lastLocation) + "\r\n";
            debug += "speed: " + speed + "\r\n";
            debug += "total distance: " + distance + "\r\n";

            Toast.makeText(context, debug, Toast.LENGTH_SHORT).show();
            lastLocation = location;
        }
    }
}
