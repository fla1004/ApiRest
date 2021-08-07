package util;

import util.ConfigEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    public  void readProperties() throws IOException {
        Properties properties = new Properties();
        String namePropertiesFile = "qa.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(namePropertiesFile);

        if(inputStream != null)
            properties.load(inputStream);

        util.ConfigEnvironment.host= properties.getProperty("host");
        util.ConfigEnvironment.user= properties.getProperty("user");
        ConfigEnvironment.password= properties.getProperty("password");


    }
}
