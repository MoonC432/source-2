package au.ntcrs6.utils;

public class ResponseHolder {
    private static String driverDetails;
    private static String vehicles;
    private static String citations;
    private static String sessions;

    public static String getVehicles() {
        return vehicles;
    }

    public static String getCitations() {
        return citations;
    }

    public static void setCitations(String citations) {
        ResponseHolder.citations = citations;
    }

    public static String getSessions() {
        return sessions;
    }

    public static void setSessions(String sessions) {
        ResponseHolder.sessions = sessions;
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
