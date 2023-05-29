package br.com.ciclocomputador.Tracker;

import br.com.ciclocomputador.Tracker.info.Time;

public class Timer {
    private long startTime;      // Stores the start time in nanoseconds
    private long elapsedTime;    // Stores the elapsed time in nanoseconds
    private boolean isRunning;   // Indicates whether the timer is running or paused

    /**
     * Constructs a Timer object with initial values.
     */
    public Timer() {
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    /**
     * Starts the timer. If the timer is already running, this method has no effect.
     */
    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    /**
     * Pauses the timer. If the timer is already paused or not running, this method has no effect.
     */
    public void pause() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
    }

    /**
     * Stops the timer and resets all values. If the timer is already stopped, this method has no effect.
     */
    public void stop() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
        startTime = 0;
        elapsedTime = 0;
    }

    /**
     * Resets the timer to start a new measurement. The elapsed time is set to 0.
     */
    public void reset() {
        startTime = System.nanoTime();
        elapsedTime = 0;
    }

    /**
     * Returns the elapsed time in nanoseconds.
     *
     * @return The elapsed time in nanoseconds.
     */
    public long getElapsedTime() {
        if (isRunning) {
            return elapsedTime + (System.nanoTime() - startTime);
        } else {
            return elapsedTime;
        }
    }

    public Time getTime() {
        return new Time(getElapsedTime());
    }


    /**
     * Returns a string representation of the elapsed time in the format "HH:MM:SS.SSS".
     *
     * @return The string representation of the elapsed time.
     */
    @Override
    public String toString() {
        return getTime().toString();
    }
}

