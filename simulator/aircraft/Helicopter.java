package simulator.aircraft;

import simulator.coordinates.Coordinates;
import simulator.coordinates.CoordinatesFactory;
import simulator.weather.WeatherTower;

public class Helicopter extends Aircraft {

    public Helicopter(long id, String name, Coordinates coordinates) {
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
                longitude += 10;
                height += 2;
                System.out.println(this + ": This is hot.");
                break;
            case "RAIN":
                longitude += 5;
                System.out.println(this + ": Damn you rain! You messed up my rotors.");
                break;
            case "FOG":
                longitude += 1;
                System.out.println(this + ": I can't see anything!");
                break;
            case "SNOW":
                height -= 12;
                System.out.println(this + ": My rotor is going to freeze!");
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
