class WeatherProvider
{
    private static String weather[] = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider()
    {
    }

    public static String getCurrentWeather(Coordinates p_coordinates)
    {
        return weather[(p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight()) % 4];
    }
}