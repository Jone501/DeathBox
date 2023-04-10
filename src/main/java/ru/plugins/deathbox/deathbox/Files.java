package ru.plugins.deathbox.deathbox;

public class Files {
    public final static Config texts = new Config("plugins//DeathBox//texts.yml");

    public static void reloadAllFiles() {
        texts.reload();
    }

    public static void saveAllFiles() {
        texts.save();
    }

    public static void updateAllFiles() {
        texts.update();
    }
}
