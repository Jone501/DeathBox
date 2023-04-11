package ru.incrementstudio.deathbox.deathbox;

public class Files {
    public final static Config config = new Config("plugins//DeathBox//config.yml");
    public final static Config deathboxes = new Config("plugins//DeathBox//deathboxes.yml");

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
