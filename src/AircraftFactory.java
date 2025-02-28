public class AircraftFactory
{
    private static final AircraftFactory Instance = new AircraftFactory();
    public static AircraftFactory getInstance() {
        return Instance;
    }

    private static long id = 1;
    public Aircraft newAircraft(String type, String name, Coordinates coordinates)
    {
        switch (type) {
            case "Baloon" -> {
                return new Baloon(generateId(), name, coordinates);
            }
            case "JetPlane" -> {
                return new JetPlane(generateId(), name, coordinates);
            }
            case "Helicopter" -> {
                return new Helicopter(generateId(), name, coordinates);
            }
            default -> {
                throw new UnsupportedOperationException("Unsupported aircraft type: " + type);
            }
        }
    }

    public long generateId()
    {
        return ++id;
    }
}