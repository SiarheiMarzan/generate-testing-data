package utils;

import org.openqa.selenium.NoSuchElementException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private PropertiesReader() {
        throw new IllegalStateException("Utility class");
    }

    public static String readProperty(String property) {
        Properties properties = new Properties();
        try (InputStream stream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(stream);
            return properties.getProperty(property);
        } catch (IOException e) {
            throw new NoSuchElementException("Cannot load file");
        }
    }
}
