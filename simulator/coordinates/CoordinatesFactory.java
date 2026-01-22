package simulator.coordinates;

public class CoordinatesFactory {
    public static Coordinates createCoordinates(int longitude, int latitude, int height) {
        return new Coordinates(longitude, latitude, height);
    }
}
