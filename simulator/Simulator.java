package simulator;

import simulator.factory.AircraftFactory;
import simulator.weather.WeatherTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java simulator <scenario_file>");
            System.exit(1);
        }

        String scenarioFile = args[0];
        WeatherTower weatherTower = new WeatherTower();
        List<Flyable> aircrafts = new ArrayList<>();
        int simulationCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFile))) {
            // Read simulation count
            String firstLine = reader.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid scenario file: missing simulation count");
            }

            try {
                simulationCount = Integer.parseInt(firstLine.trim());
                if (simulationCount <= 0) {
                    throw new IllegalArgumentException("Simulation count must be positive");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid simulation count: " + firstLine);
            }

            // Read aircraft definitions
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Invalid aircraft definition: " + line);
                }

                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);

                // Validate coordinates
                if (longitude < 0 || latitude < 0 || height < 0 || height > 100) {
                    throw new IllegalArgumentException("Invalid coordinates: " + line);
                }

                Flyable aircraft = AircraftFactory.getInstance().newAircraft(type, name, longitude, latitude, height);
                aircrafts.add(aircraft);
            }

            if (aircrafts.isEmpty()) {
                throw new IllegalArgumentException("No aircraft defined in scenario file");
            }

        } catch (IOException e) {
            System.err.println("Error reading scenario file: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing scenario: " + e.getMessage());
            System.exit(1);
        }

        // Register all aircraft with the weather tower
        for (Flyable aircraft : aircrafts) {
            aircraft.registerTower(weatherTower);
        }

        // Run simulation
        for (int i = 0; i < simulationCount; i++) {
            weatherTower.changeWeather();
        }
    }
}
