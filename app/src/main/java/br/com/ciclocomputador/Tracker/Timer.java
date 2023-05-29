package br.com.ciclocomputador.Tracker;

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

    /**
     * Returns a string representation of the elapsed time in the format "HH:MM:SS.SSS".
     *
     * @return The string representation of the elapsed time.
     */
    @Override
    public String toString() {
        long totalNanoseconds = getElapsedTime();
        long milliseconds = (totalNanoseconds / 1_000_000) % 1000;
        long totalSeconds = totalNanoseconds / 1_000_000_000;
        long seconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minutes = totalMinutes % 60;
        long hours = totalMinutes / 60;

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}

