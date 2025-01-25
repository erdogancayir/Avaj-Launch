// crate abstract class
abstract class Flyable 
{
    protected WeatherTower weatherTower;
    public abstract void updateConditions();
    public abstract void registerTower(WeatherTower weatherTower);
}