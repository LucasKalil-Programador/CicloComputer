package br.com.ciclocomputer.tracker;

public class GPSInfo {
    public final String elapsedTime;
    public final String distance;
    public final String speed;
    public final String maxSpeed;
    public final String avgSpeed;

    public GPSInfo(String elapsedTime, String distance, String speed, String maxSpeed, String avgSpeed) {
        this.elapsedTime = elapsedTime;
        this.distance = distance;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.avgSpeed = avgSpeed;
    }
}
