package TextFiles;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class TextFileTest {

    private static final Logger logger =
            LogManager.getLogger(TextFileTest.class);

    public static void main(String[] args) {

        Configurator.setRootLevel(Level.INFO);

        String filePath = "src/main/resources/employee.txt";

        try {

            // Write content
            TextFileUtility.writeFile(filePath, "Name: Gayathri");

            logger.info("File written successfully");

            // Append content
            TextFileUtility.appendFile(
                    filePath, "Role: Associate Technical Consultant"
            );

            logger.info("Content appended successfully");

            // Read content
            logger.info("Reading file:");

            TextFileUtility.readFile(filePath);

            logger.info("File reading completed");

        } catch (IOException e) {
            logger.error("Error processing text file", e);
        }
    }
}