package Properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    // Read properties from file
    public static Properties readProperties(String filePath)
            throws IOException {

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            p.load(fis);
        }

        return p;
    }

    // Get a property value
    public static String getProperty(Properties p, String key) {

        return p.getProperty(key);
    }

    // Write properties into file
    public static void writeProperties(Properties p, String filePath)
            throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            p.store(fos, "Updated Properties");
        }
    }
}