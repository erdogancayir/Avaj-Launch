class Aircraft extends Flyable
{
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates)
    {
        id = p_id;
        name = p_name;
        coordinates = p_coordinates;
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        throw new UnsupportedOperationException("Unimplemented method 'registerTower'");
    }

    @Override
    public void updateConditions() {
        throw new UnsupportedOperationException("Unimplemented method 'updateConditions'");
    }
}