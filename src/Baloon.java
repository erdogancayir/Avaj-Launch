import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft {
    private WeatherTower weatherTower;
    private static final Map<String, String> weatherMessages = new HashMap<>();

    static {
        weatherMessages.put("SUN", "The sun is blazing! Gaining altitude.");
        weatherMessages.put("RAIN", "Rain is pouring down... losing some height.");
        weatherMessages.put("FOG", "Can't see much in this fog... this is risky.");
        weatherMessages.put("SNOW", "It's freezing! The balloon is descending fast.");
    }

    public Baloon(long id, String name, Coordinates coordinates) {
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
                newLongitude += 2;
                newHeight += 4;
                break;
            case "RAIN":
                newHeight -= 5;
                break;
            case "FOG":
                newHeight -= 3;
                break;
            case "SNOW":
                newHeight -= 15;
                break;
            default:
                return;
        }

        this.coordinates = new Coordinates(newLongitude, newLatitude, Math.max(newHeight, 0));
        Logger.getInstance().log(String.format("Balloon#%s(%d): %s", this.name, this.id, weatherMessages.get(weather)));

        if (newHeight <= 0) {
            Logger.getInstance().log(String.format("Balloon#%s(%d) is landing.", this.name, this.id));
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Logger.getInstance().log(String.format("Tower notification: Balloon#%s(%d) successfully registered.", this.name, this.id));
    }
}