package ru.incrementstudio.deathbox.deathbox.utils;

public class ConfigUtil {
    public static String combinePath(String ... elements) {
        String path = "";
        for (int i = 0; i < elements.length; i++) {
            if (i > 0) path += ".";
            path += elements[i];
        }
        return path;
    }
}
