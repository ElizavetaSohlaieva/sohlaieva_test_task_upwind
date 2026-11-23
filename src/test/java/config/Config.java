package config;

import java.util.ResourceBundle;

public final class Config {
    private static final ResourceBundle FILE = ResourceBundle.getBundle("config");

    public static String get(String key) {
        String value = System.getProperty(key);
        return value != null ? value : FILE.getString(key);
    }
}