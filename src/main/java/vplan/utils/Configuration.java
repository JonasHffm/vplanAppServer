package vplan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    protected Properties prop;

    public Configuration() {
        this.prop = new Properties();
    }

    public Configuration(Properties prop) {
        this.prop = prop;
    }

    public String getDatabaseDSN() {
        return this.prop.getProperty("databaseDSN", "jdbc:mysql://localhost:3306/vplan");
    }

    public String getDatabaseUser() {
        return this.prop.getProperty("databaseUser", "root");
    }

    public String getDatabasePassword() {
        return this.prop.getProperty("databasePassword", "");
    }

    public int getServerPort() {
        return Integer.parseInt(this.prop.getProperty("port", "1112"));
    }

    public static Configuration fromConfiguration(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = new Properties();
        try {
           fis = new FileInputStream(fileName);
           prop.load(fis);
           System.out.println("Successfully loaded configurations from " + fileName);
        } catch(FileNotFoundException fnfe) {
           fnfe.printStackTrace();
        } catch(IOException ioe) {
           ioe.printStackTrace();
        } finally {
           fis.close();
        }
        return new Configuration(prop);
    }
}
