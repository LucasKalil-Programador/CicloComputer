package br.com.ciclocomputador.tracker.info;

import android.location.Location;

/**
 * Represents information about a tracker.
 */
public class TrackerInfo {

    // Member variables representing tracker information

    private Speed currentSpeed, avgSpeed, maxSpeed;
    private Distance distance;
    private Time elapsedTime;
    private Location lastLocation;

    /**
     * Constructs a new TrackerInfo object with the given parameters.
     *
     * @param currentSpeed  The current speed.
     * @param avgSpeed      The average speed.
     * @param maxSpeed      The maximum speed.
     * @param distance      The distance traveled.
     * @param elapsedTime   The elapsed time.
     * @param lastLocation  The last known location.
     */
    public TrackerInfo(Speed currentSpeed, Speed avgSpeed, Speed maxSpeed, Distance distance, Time elapsedTime, Location lastLocation) {
        this.currentSpeed = currentSpeed;
        this.avgSpeed = avgSpeed;
        this.maxSpeed = maxSpeed;
        this.distance = distance;
        this.elapsedTime = elapsedTime;
        this.lastLocation = lastLocation;
    }

    // Getter and setter methods for member variables

    public Speed getCurrentSpeed() {
        return currentSpeed;
    }

    public Speed getAvgSpeed() {
        return avgSpeed;
    }

    public Speed getMaxSpeed() {
        return maxSpeed;
    }

    public Distance getDistance() {
        return distance;
    }

    public Time getElapsedTime() {
        return elapsedTime;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    /**
     * Checks if this TrackerInfo object is equal to another object.
     *
     * @param o The object to compare.
     * @return  True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackerInfo)) return false;

        TrackerInfo that = (TrackerInfo) o;

        if (getCurrentSpeed() != null ? !getCurrentSpeed().equals(that.getCurrentSpeed()) : that.getCurrentSpeed() != null)
            return false;
        if (getAvgSpeed() != null ? !getAvgSpeed().equals(that.getAvgSpeed()) : that.getAvgSpeed() != null)
            return false;
        if (getMaxSpeed() != null ? !getMaxSpeed().equals(that.getMaxSpeed()) : that.getMaxSpeed() != null)
            return false;
        if (getDistance() != null ? !getDistance().equals(that.getDistance()) : that.getDistance() != null)
            return false;
        if (getElapsedTime() != null ? !getElapsedTime().equals(that.getElapsedTime()) : that.getElapsedTime() != null)
            return false;
        return getLastLocation() != null ? getLastLocation().equals(that.getLastLocation()) : that.getLastLocation() == null;
    }

    /**
     * Generates a hash code for this TrackerInfo object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int result = getCurrentSpeed() != null ? getCurrentSpeed().hashCode() : 0;
        result = 31 * result + (getAvgSpeed() != null ? getAvgSpeed().hashCode() : 0);
        result = 31 * result + (getMaxSpeed() != null ? getMaxSpeed().hashCode() : 0);
        result = 31 * result + (getDistance() != null ? getDistance().hashCode() : 0);
        result = 31 * result + (getElapsedTime() != null ? getElapsedTime().hashCode() : 0);
        result = 31 * result + (getLastLocation() != null ? getLastLocation().hashCode() : 0);
        return result;
    }
}
