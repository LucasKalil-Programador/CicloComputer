package br.com.ciclocomputador.tracker.info;

/**
 * Represents a distance value in meters and provides conversions to other units.
 */
public class Distance {
    private double meters;

    /**
     * Constructs a Distance object with the specified meters.
     *
     * @param meters the distance value in meters
     */
    public Distance(double meters) {
        this.meters = meters;
    }

    /**
     * Retrieves the distance value in meters.
     *
     * @return the distance value in meters
     */
    public double getMeters() {
        return meters;
    }

    /**
     * Converts the distance value to kilometers.
     *
     * @return the distance value in kilometers
     */
    public double getKilometers() {
        return meters / 1000.0;
    }

    /**
     * Converts the distance value to miles.
     *
     * @return the distance value in miles
     */
    public double getMiles() {
        return meters / 1609.34;
    }

    /**
     * Returns a string representation of the distance in the specified unit.
     *
     * @param unit the desired unit of measurement ("m", "km", or "mi")
     * @return the string representation of the distance in the specified unit, or "Invalid unit" if the unit is not supported
     */
    public String toString(String unit) {
        double value = 0;
        String unitLabel = "";

        switch (unit.toLowerCase()) {
            case "m":
                value = meters;
                unitLabel = "m";
                break;
            case "km":
                value = getKilometers();
                unitLabel = "km";
                break;
            case "mi":
                value = getMiles();
                unitLabel = "mi";
                break;
            default:
                return "Invalid unit";
        }
        if(Double.isNaN(value)) value = 0;
        return String.format("%.1f %s", value, unit);
    }

    /**
     * Returns a string representation of the distance in kilometers.
     *
     * @return the string representation of the distance in kilometers
     */
    @Override
    public String toString() {
        return toString("km");
    }
}

