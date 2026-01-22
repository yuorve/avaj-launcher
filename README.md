# avaj-launcher

A Java-based aircraft weather simulation program that demonstrates object-oriented programming principles, design patterns, and file I/O operations.

## 📋 Description

This project simulates the behavior of different types of aircraft (Helicopters, Balloons, and JetPlanes) under varying weather conditions. Each aircraft type reacts differently to weather changes (SUN, RAIN, FOG, SNOW), and the simulation runs for a specified number of weather cycles.

The project implements key design patterns including:
- **Factory Pattern** - for aircraft creation
- **Observer Pattern** - for weather tower notifications
- **Singleton Pattern** - for weather provider

## ✨ Features

- **Multiple Aircraft Types**: Supports Helicopters, Balloons, and JetPlanes
- **Dynamic Weather System**: Weather changes affect aircraft coordinates differently
- **Observer Pattern**: Aircraft register with a weather tower and receive weather updates
- **Scenario-Based Simulation**: Load aircraft configurations from text files
- **Automatic Landing**: Aircraft land when they reach height 0
- **Input Validation**: Comprehensive error handling for invalid scenarios

## 🚀 Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

### Compilation

```bash
# Compile all Java files
javac -d . @sources.txt

# Or compile manually
javac simulator/*.java simulator/**/*.java
```

## 📖 Usage

### Running the Simulation

```bash
java simulator.Simulator scenario.txt
```

### Scenario File Format

The scenario file must follow this format:

```
<number_of_simulations>
<aircraft_type> <name> <longitude> <latitude> <height>
<aircraft_type> <name> <longitude> <latitude> <height>
...
```

**Example** (`scenario.txt`):
```
25
Baloon B1 2 3 20
Baloon B2 1 8 66
JetPlane J1 23 44 32
Helicopter H1 654 33 20
Helicopter H2 22 33 44
```

### Valid Aircraft Types
- `Baloon`
- `Helicopter`
- `JetPlane`

### Coordinate Constraints
- **Longitude**: ≥ 0
- **Latitude**: ≥ 0
- **Height**: 0-100 (aircraft lands at height 0)

## 📁 Project Structure

```
avaj_launcher/
├── simulator/
│   ├── Simulator.java           # Main entry point
│   ├── Flyable.java             # Interface for flyable objects
│   ├── Tower.java               # Observer pattern implementation
│   ├── aircraft/
│   │   ├── Aircraft.java        # Abstract base class
│   │   ├── Baloon.java          # Balloon implementation
│   │   ├── Helicopter.java      # Helicopter implementation
│   │   └── JetPlane.java        # JetPlane implementation
│   ├── coordinates/
│   │   └── Coordinates.java     # Position management
│   ├── factory/
│   │   └── AircraftFactory.java # Factory pattern for aircraft creation
│   └── weather/
│       ├── WeatherProvider.java # Singleton weather generator
│       └── WeatherTower.java    # Weather-specific tower
└── README.md                    # This file
```

## 🎮 How It Works

1. **Initialization**: The simulator reads the scenario file and creates aircraft using the Factory pattern
2. **Registration**: All aircraft register with the WeatherTower (Observer pattern)
3. **Simulation Loop**: For each simulation cycle:
   - The weather tower changes weather conditions
   - Each registered aircraft receives the weather update
   - Aircraft adjust their coordinates based on weather and type
   - Aircraft that reach height 0 automatically land and unregister

### Weather Effects by Aircraft Type

#### Helicopter
- **SUN**: longitude +10, height +2
- **RAIN**: longitude +5
- **FOG**: longitude +1
- **SNOW**: height -12

#### Balloon
- **SUN**: longitude +2, height +4
- **RAIN**: height -5
- **FOG**: height -3
- **SNOW**: height -15

#### JetPlane
- **SUN**: latitude +10, height +2
- **RAIN**: latitude +5
- **FOG**: latitude +1
- **SNOW**: height -7

## 🛠️ Design Patterns

### Factory Pattern
The `AircraftFactory` class creates aircraft instances based on type strings, encapsulating object creation logic.

### Observer Pattern
The `Tower` class maintains a list of observers (aircraft) and notifies them of weather changes. Aircraft implement the `Flyable` interface to receive updates.

### Singleton Pattern
The `WeatherProvider` ensures only one instance exists to generate consistent weather conditions.

## ⚠️ Error Handling

The program validates:
- Correct number of command-line arguments
- Valid scenario file format
- Positive simulation count
- Valid aircraft types
- Valid coordinate ranges
- Non-empty aircraft list

## 📝 Example Output

```
Tower says: Helicopter#H1(1) registered to weather tower.
Tower says: Helicopter#H2(2) registered to weather tower.
Tower says: Baloon#B1(3) registered to weather tower.
Helicopter#H1(1): This is hot! Time to cool down.
Helicopter#H2(2): Let's enjoy the good weather and take some pics.
Baloon#B1(3): Let's enjoy the good weather and take some pics.
...
Tower says: Helicopter#H1(1) unregistering from weather tower.
Helicopter#H1(1): Landing.
```

## 🎓 Learning Objectives

This project teaches:
- Object-oriented programming in Java
- Design pattern implementation
- File I/O operations
- Exception handling
- Interface and abstract class usage
- Package organization

## 📄 License

This project is part of the 42 School curriculum.

## 👤 Author

Created as part of the 42 School avaj-launcher project.
