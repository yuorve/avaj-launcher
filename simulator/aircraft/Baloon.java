package simulator.aircraft;

import simulator.coordinates.Coordinates;
import simulator.coordinates.CoordinatesFactory;
import simulator.weather.WeatherTower;

public class Baloon extends Aircraft {

    public Baloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        switch (weather) {
            case "SUN":
                longitude += 2;
                height += 4;
                System.out.println(this + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                height -= 5;
                System.out.println(this + ": Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                height -= 3;
                System.out.println(this + ": This is foggy, I can't see.");
                break;
            case "SNOW":
                height -= 15;
                System.out.println(this + ": It's snowing. We're gonna crash.");
                break;
        }

        // Ensure height stays within bounds
        if (height > 100) {
            height = 100;
        }

        this.coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);

        // Check if aircraft should land
        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this + ": landing.");
            this.weatherTower.unregister(this);
        }
    }
}
