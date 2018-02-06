package pl.edu.wat.wcy.isi.prz;

import java.io.InputStream;
import java.util.Properties;

public class Prop {

    private static final Properties properties = getProperties();

    private static Properties getProperties() {
        Properties prop = new java.util.Properties();
        ClassLoader cl = Prop.class.getClassLoader();
        try (InputStream stream = cl.getResourceAsStream("app.properties")) {
                prop.load(stream);
        } catch (Exception e) {
            System.out.println("No properties file!");
            e.printStackTrace();
        }
        return prop;
    }

    public static String getValue(String key) { return properties.getProperty(key); }
    public static void setValue (String key, String value) { properties.setProperty(key, value); }
}
