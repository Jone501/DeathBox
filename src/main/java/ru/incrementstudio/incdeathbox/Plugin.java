package ru.incrementstudio.incdeathbox;

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    private static Plugin instance;
    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger.info("");
        Logger.info("&f| ---------------------------" + getDescription().getName() + "---------------------------");
        Logger.info("&f| Запуск плагина: &7" + getDescription().getName() + " &8| &fВерсия: &7" + getDescription().getVersion());
        Logger.info("&f| Информация о сервере:");
        Logger.info("&f| Ядро: &7" + getServer().getName() + " &8| &fТребуется: &7Paper и все его форки");
        Logger.info("&f| Версия: &7" + getServer().getVersion() + " &8| &fТребуется: &7" + getDescription().getAPIVersion() + "+");
        Logger.info("&f| Айпи: &7" + getServer().getIp() + ":" + getServer().getPort());
        Logger.info("");

        Logger.info("&f| Загрузка конфигов...");
        Files.updateAllFiles();

        Logger.info("&f| Загрузка ивентов...");
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);

        Logger.info("&f| Плагин успешно запущен!");
    }

    @Override
    public void onDisable() {
        Logger.info("&f| Выключение плагина...");
        Logger.info("&f| Плагин успешно выключен!");
    }
}
