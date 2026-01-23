package simulator.factory;

import simulator.Flyable;
import simulator.aircraft.Balloon;
import simulator.aircraft.Helicopter;
import simulator.aircraft.JetPlane;
import simulator.coordinates.Coordinates;
import simulator.coordinates.CoordinatesFactory;

public class AircraftFactory {
    private static AircraftFactory instance = new AircraftFactory();
    private static long idCounter = 0;

    private AircraftFactory() {
    }

    public static AircraftFactory getInstance() {
        return instance;
    }

    private long nextId() {
        return ++idCounter;
    }

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
        long id = nextId();

        switch (type.toLowerCase()) {
            case "balloon":
                return new Balloon(id, name, coordinates);
            case "helicopter":
                return new Helicopter(id, name, coordinates);
            case "jetplane":
                return new JetPlane(id, name, coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
}
