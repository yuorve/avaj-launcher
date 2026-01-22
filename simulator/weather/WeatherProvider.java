package simulator.weather;

import simulator.coordinates.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int hash = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        int random = (int) (Math.random() * 4);
        return weather[(hash + random) % 4];
    }
}
