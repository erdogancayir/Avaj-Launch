import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Simulator <scenario_file>");
            return;
        }

        String scenarioFile = args[0];

        try {
            List<String> lines = loadScenarioFile(scenarioFile);

            if (lines.isEmpty()) {
                throw new ScenarioFileException("Scenario file is empty or corrupted.");
            }

            int totalSimulations = extractSimulationSteps(lines.get(0));
            WeatherTower weatherTower = new WeatherTower();

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                Flyable aircraft = parseAircraftData(line);
                aircraft.registerTower(weatherTower);
            }

            for (int step = 0; step < totalSimulations; step++) {
                weatherTower.changeWeather();
            }
            Logger.getInstance().writeToFile("simulation_output.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Scenario file could not be found.");
        } catch (IOException e) {
            System.out.println("Error reading scenario file: " + e.getMessage());
        } catch (ScenarioFileException | AircraftInitializationException e) {
            System.out.println("Processing error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected system error: " + e.getMessage());
        }
    }

    private static List<String> loadScenarioFile(String filePath) throws IOException {
        List<String> scenarioLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scenarioLines.add(line.trim());
            }
        }
        return scenarioLines;
    }

    private static int extractSimulationSteps(String firstLine) throws ScenarioFileException {
        try {
            int steps = Integer.parseInt(firstLine);
            if (steps <= 0) {
                throw new ScenarioFileException("Invalid simulation step count: " + steps);
            }
            return steps;
        } catch (NumberFormatException e) {
            throw new ScenarioFileException("Non-numeric simulation step value detected: " + firstLine);
        }
    }

    private static Flyable parseAircraftData(String dataLine) throws ScenarioFileException, AircraftInitializationException {
        String[] elements = dataLine.split(" ");

        if (elements.length != 5) {
            throw new ScenarioFileException("Malformed aircraft entry in scenario file: " + dataLine);
        }

        String aircraftType = elements[0];
        String aircraftName = elements[1];

        try {
            int longitude = Integer.parseInt(elements[2]);
            int latitude = Integer.parseInt(elements[3]);
            int altitude = Integer.parseInt(elements[4]);

            Coordinates position = new Coordinates(longitude, latitude, altitude);
            Flyable aircraft = AircraftFactory.getInstance().newAircraft(aircraftType, aircraftName, position);

            if (aircraft == null) {
                throw new AircraftInitializationException("Unrecognized aircraft type: " + aircraftType);
            }
            return aircraft;

        } catch (NumberFormatException e) {
            throw new ScenarioFileException("Invalid numeric data in aircraft definition: " + dataLine);
        }
    }
}
