class AircraftFactory
{
    public static Aircraft newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        long id = 1;
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case "Baloon" -> {
                return new Baloon(id, name, coordinates);
            }
            case "JetPlane" -> {
                return new JetPlane(id, name, coordinates);
            }
            case "Helicopter" -> {
                return new Helicopter(id, name, coordinates);
            }
            default -> {
                throw new UnsupportedOperationException("Unsupported aircraft type: " + type);
            }
        }
    }
}