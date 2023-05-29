package br.com.ciclocomputador.Tracker.info;

/**
 * Represents a time value in nanoseconds and provides conversions to other units.
 */
public class Time {
    private long nanoseconds;

    /**
     * Constructs a Time object with the specified time in nanoseconds.
     *
     * @param nanoseconds the time value in nanoseconds
     */
    public Time(long nanoseconds) {
        this.nanoseconds = nanoseconds;
    }

    /**
     * Retrieves the time value in nanoseconds.
     *
     * @return the time value in nanoseconds
     */
    public long getNanoseconds() {
        return nanoseconds;
    }

    /**
     * Returns a string representation of the elapsed time in the format "HH:MM:SS.SSS".
     *
     * @return The string representation of the elapsed time.
     */
    @Override
    public String toString() {
        long totalNanoseconds = getNanoseconds();
        long milliseconds = (totalNanoseconds / 1_000_000) % 1000;
        long totalSeconds = totalNanoseconds / 1_000_000_000;
        long seconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minutes = totalMinutes % 60;
        long hours = totalMinutes / 60;

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}
