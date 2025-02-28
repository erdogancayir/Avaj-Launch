import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft {
    private WeatherTower weatherTower;
    private static final Map<String, String> weatherMessages = new HashMap<>();

    static {
        weatherMessages.put("SUN", "The sun is blazing! Gaining some height.");
        weatherMessages.put("RAIN", "Heavy rain incoming, hope my rotors can handle it.");
        weatherMessages.put("FOG", "Visibility is terrible! This is risky.");
        weatherMessages.put("SNOW", "Ice forming on my blades, losing altitude fast!");
    }

    public Helicopter(long id, String name, Coordinates coordinates) {
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
                newLongitude += 10;
                newHeight += 2;
                break;
            case "RAIN":
                newLongitude += 5;
                break;
            case "FOG":
                newLongitude += 1;
                break;
            case "SNOW":
                newHeight -= 12;
                break;
            default:
                return;
        }

        this.coordinates = new Coordinates(newLongitude, newLatitude, Math.max(newHeight, 0));
        Logger.getInstance().log(String.format("Helicopter#%s(%d): %s", this.name, this.id, weatherMessages.get(weather)));

        if (newHeight <= 0) {
            Logger.getInstance().log(String.format("Helicopter#%s(%d) is landing.", this.name, this.id));
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        Logger.getInstance().log(String.format("Tower notification: Helicopter#%s(%d) successfully registered.", this.name, this.id));
    }
}