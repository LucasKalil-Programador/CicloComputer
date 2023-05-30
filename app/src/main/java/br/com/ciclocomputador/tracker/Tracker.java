package br.com.ciclocomputador.tracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

import java.security.Permission;

import br.com.ciclocomputador.Permission.LocationPermissionManager;
import br.com.ciclocomputador.R;
import br.com.ciclocomputador.tracker.info.TrackerInfo;

public class Tracker {
    private Context context;
    private LocationManager locationManager;
    private Timer timer = new Timer();
    private TrackerLocationListener listener = new TrackerLocationListener(timer);
    private boolean isListenerRegistered = false;

    public Tracker(Context context) {
        this.context = context;
        if(LocationPermissionManager.checkLocationPermissions(context)) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
    }


    @SuppressLint("MissingPermission")
    public void start() {
        timer.start();
        registerListener();
    }

    public void pause() {
        timer.pause();
        unregisterListener();
    }

    public void Stop() {
        timer.stop();
    }

    public String getBetterProvider() {
        if(locationManager != null) {
            if(locationManager.isProviderEnabled(LocationManager.FUSED_PROVIDER)) return LocationManager.FUSED_PROVIDER;
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) return LocationManager.GPS_PROVIDER;
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    private synchronized void registerListener() {
        if(!isListenerRegistered) {
            String provider = getBetterProvider();
            if(provider != null) {
                locationManager.removeUpdates(listener);
                if(LocationPermissionManager.checkLocationPermissions(context)) {
                    listener.resetLastLocation();
                    locationManager.requestLocationUpdates(provider,0,0, listener);
                    isListenerRegistered = true;
                }
            }
        }
    }

    private synchronized void unregisterListener() {
        locationManager.removeUpdates(listener);
        isListenerRegistered = false;
    }

    public TrackerInfo getTrackerInfo() {
        return listener.getTrackerInfo();
    }
}
