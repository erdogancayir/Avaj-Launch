import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final Logger INSTANCE = new Logger();
    private static final List<String> logs = new ArrayList<>();

    private Logger() {} // Private constructor to enforce singleton pattern

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(String message) {
        logs.add(message);
    }

    public void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String log : logs) {
                writer.write(log + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Log file write error: " + e.getMessage());
        }
    }
}