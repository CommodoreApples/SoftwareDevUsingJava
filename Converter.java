public class Converter {

    /** Convert from feet to meters */
    public static double footToMeter(double foot) {
        return foot * 0.3048;
    }

    /** Convert from meters to feet */
    public static double meterToFoot(double meter) {
        return meter / 0.3048;
    }
}
