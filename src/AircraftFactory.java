class AircraftFactory
{
    public static Aircraft newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        long id = 1;
        switch (type) {
            case "Baloon" -> {
                return new Baloon(id, name, longitude, latitude, height);
            }
            case "JetPlane" -> {
                return new JetPlane(id, name, longitude, latitude, height);
            }
            case "Helicopter" -> {
                return new Helicopter(id, name, longitude, latitude, height);
            }
            default -> {
                throw new UnsupportedOperationException("Unsupported aircraft type: " + type);
            }
        }
    }
}