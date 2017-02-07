package nl.next35.logic;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Oscar de Leeuw
 */
public class PropertiesLoader {
    public static Properties properties() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/services.properties"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return properties;
    }
}
