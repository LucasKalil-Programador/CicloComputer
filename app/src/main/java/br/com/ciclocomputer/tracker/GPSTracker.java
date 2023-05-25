package br.com.ciclocomputer.tracker;


import static android.content.Context.LOCATION_SERVICE;
import static android.location.LocationManager.GPS_PROVIDER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;

import br.com.ciclocomputer.MainActivity;

public class GPSTracker {

    private Context context;
    private LocationManager locationManager;
    private InternalLocationListener locationListener = new InternalLocationListener();

    private boolean paused = true;

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
            if(locationListener.pausedTime != -1) {
                locationListener.startTime += System.currentTimeMillis() - locationListener.pausedTime;
                locationListener.pausedTime = -1;
            }
            paused = false;
            return true;
        }
        return false;
    }

    public void pauseTracking() {
        locationManager.removeUpdates(locationListener);
        locationListener.pausedTime = System.currentTimeMillis();
        paused = true;
    }

    public GPSInfo getInfo() {
        return locationListener.getInfo();
    }
}
