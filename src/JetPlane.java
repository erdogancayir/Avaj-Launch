import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft {
    private WeatherTower weatherTower;
    private static final Map<String, String> weatherMessages = new HashMap<>();

    static {
        weatherMessages.put("SUN", "Sunny weather! Time to push the throttle.");
        weatherMessages.put("RAIN", "Raindrops racing me? They don’t stand a chance.");
        weatherMessages.put("FOG", "Thick fog, switching to instruments...");
        weatherMessages.put("SNOW", "Snow is slowing me down! Hope the wings hold up.");
    }

    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int newLongitude = coordinates.getLongitude();
        int newLatitude = coordinates.getLatitude();
        int newHeight = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                newLatitude += 10;
                newHeight += 2;
                break;
            case "RAIN":
                newLatitude += 5;
                break;
            case "FOG":
                newLatitude += 1;
                break;
            case "SNOW":
                newHeight -= 7;
                break;
            default:
                return;
        }

        this.coordinates = new Coordinates(newLongitude, newLatitude, Math.max(newHeight, 0));
        Logger.getInstance().log(String.format("JetPlane#%s(%d): %s", this.name, this.id, weatherMessages.get(weather)));

        if (newHeight <= 0) {
            Logger.getInstance().log(String.format("JetPlane#%s(%d) is landing.", this.name, this.id));
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Logger.getInstance().log(String.format("Tower notification: JetPlane#%s(%d) successfully registered.", this.name, this.id));
    }
}
