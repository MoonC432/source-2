package au.ntcrs6.utils;

public class ResponseHolder {
    private static String driverDetails;
    private static String vehicles;

    public static String getVehicles() {
        return vehicles;
    }

    public static void setVehicles(String vehicles) {
        ResponseHolder.vehicles = vehicles;
    }

    public static String getDriverDetails() {
        return driverDetails;
    }

    public static void setDriverDetails(String driverDetails) {
        ResponseHolder.driverDetails = driverDetails;
    }
}
