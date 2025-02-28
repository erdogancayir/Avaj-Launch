public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int altitude;

    public Coordinates(int longitude, int latitude, int altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = Math.max(0, Math.min(altitude, 100));
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return altitude;
    }
}
