package ru.incrementstudio.incdeathbox;

public class Files {
    public final static Config config = new Config("plugins//IncDeathBox//config.yml");
    public final static Config deathboxes = new Config("plugins//IncDeathBox//deathboxes.yml");

    public static void reloadAllFiles() {
        config.reload();
        deathboxes.reload();
    }

    public static void saveAllFiles() {
        config.save();
        deathboxes.save();
    }

    public static void updateAllFiles() {
        config.update();
        deathboxes.update();
    }
}
