package Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();

    public PropertyReader()
    {
        String filePath = "src/test/resources/propertiesFiles/config.properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
    }


    // Get value by key
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Get value with default fallback
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}

