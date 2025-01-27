class Baloon extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;

    public Baloon(long p_id, String p_name, Coordinates p_coordinates)
    {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        throw new UnsupportedOperationException("Unimplemented method 'updateConditions'");
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Baloon#" + name + "(" + id + ") registered to weather tower.");
    }
}