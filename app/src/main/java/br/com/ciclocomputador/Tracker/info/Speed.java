package br.com.ciclocomputador.Tracker.info;

/**
 * Represents a speed value in meters per second and provides conversions to other units.
 */
public class Speed {
    private long metersPerSecond;

    /**
     * Constructs a Speed object with the specified meters per second.
     *
     * @param metersPerSecond the speed value in meters per second
     */
    public Speed(long metersPerSecond) {
        this.metersPerSecond = metersPerSecond;
    }

    /**
     * Retrieves the speed value in meters per second.
     *
     * @return the speed value in meters per second
     */
    public long getMetersPerSecond() {
        return metersPerSecond;
    }

    /**
     * Converts the speed value to kilometers per hour.
     *
     * @return the speed value in kilometers per hour
     */
    public double getKilometersPerHour() {
        return metersPerSecond * 3.6;
    }

    /**
     * Converts the speed value to miles per hour.
     *
     * @return the speed value in miles per hour
     */
    public double getMilesPerHour() {
        return metersPerSecond * 2.23694;
    }

    /**
     * Returns a string representation of the speed in the specified unit.
     *
     * @param unit the desired unit of measurement ("m/s", "km/h", or "mph")
     * @return the string representation of the speed in the specified unit, or "Invalid unit" if the unit is not supported
     */
    public String toString(String unit) {
        double value = 0;
        String unitLabel = "";

        switch (unit.toLowerCase()) {
            case "m/s":
                value = metersPerSecond;
                unitLabel = "m/s";
                break;
            case "km/h":
                value = getKilometersPerHour();
                unitLabel = "km/h";
                break;
            case "mph":
                value = getMilesPerHour();
                unitLabel = "mph";
                break;
            default:
                return "Invalid unit";
        }

        return value + " " + unitLabel;
    }

    /**
     * Returns a string representation of the speed in kilometers per hour.
     *
     * @return the string representation of the speed in kilometers per hour
     */
    @Override
    public String toString() {
        return toString("km/h");
    }
}


