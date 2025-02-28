import java.util.Random;

public class WeatherProvider {
    private static final WeatherProvider INSTANCE = new WeatherProvider();
    private static final String[] WEATHER_TYPES = {"RAIN", "FOG", "SUN", "SNOW"};
    private final Random random = new Random();

    private WeatherProvider() {} // Private constructor to enforce singleton pattern

    public static WeatherProvider getInstance() {
        return INSTANCE;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + random.nextInt(50)) % WEATHER_TYPES.length;
        return WEATHER_TYPES[index];
    }
}