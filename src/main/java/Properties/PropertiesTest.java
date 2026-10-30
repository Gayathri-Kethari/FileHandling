package Properties;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class PropertiesTest {

    private static final Logger logger =
            LogManager.getLogger(PropertiesTest.class);

    public static void main(String[] args) {

        Configurator.setRootLevel(Level.INFO);

        try {

            // Read properties file
            Properties p = PropertiesUtility.readProperties(
                    "src/main/resources/config.properties"
            );

            logger.info("Properties file loaded successfully");

            // Get property values using utility method
            String city = PropertiesUtility.getProperty(p, "city");
            String name = PropertiesUtility.getProperty(p, "name");

            logger.info("City: {}", city);
            logger.info("Name: {}", name);

            // Update properties
            p.setProperty("city", "Bangalore");
            p.setProperty("role", "Associate Technical Consultant");

            // Save updated properties
            PropertiesUtility.writeProperties(
                    p,
                    "src/main/resources/config.properties"
            );

            logger.info("Updated City: {}",
                    PropertiesUtility.getProperty(p, "city"));

            logger.info("Updated Role: {}",
                    PropertiesUtility.getProperty(p, "role"));

            logger.info("Properties file updated successfully");

        } catch (IOException e) {

            logger.error("Error processing properties file", e);
        }
    }
}