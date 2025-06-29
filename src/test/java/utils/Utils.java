package utils;

import org.apache.commons.configuration.PropertiesConfiguration;

import javax.naming.ConfigurationException;

public class Utils {
    public static void setEnvVar(String key, String value) throws ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static int generateRandomNumber(int min, int max) {
        double random = Math.random()*(max-min)+min;
        return (int) random;
    }

}
