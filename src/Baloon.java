class Baloon extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;

    public Baloon(long p_id, String p_name, int longitude, int latitude, int height)
    {
        super(p_id, p_name, new Coordinates(longitude, latitude, height));
    }

    @Override
    public void updateConditions() {
        throw new UnsupportedOperationException("Unimplemented method 'updateConditions'");
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}