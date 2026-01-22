package simulator.aircraft;

import simulator.coordinates.Coordinates;
import simulator.coordinates.CoordinatesFactory;
import simulator.weather.WeatherTower;

public class JetPlane extends Aircraft {

    public JetPlane(long id, String name, Coordinates coordinates) {
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
                latitude += 10;
                height += 2;
                System.out.println(this + ": It's sunny, let's enjoy the flight!");
                break;
            case "RAIN":
                latitude += 5;
                System.out.println(this + ": It's raining. Better watch out for turbulences.");
                break;
            case "FOG":
                latitude += 1;
                System.out.println(this + ": Foggy weather. Visibility is low.");
                break;
            case "SNOW":
                height -= 7;
                System.out.println(this + ": OMG! Winter is coming!");
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
