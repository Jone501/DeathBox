package ru.incrementstudio.deathbox.deathbox.utils;

import org.bukkit.Location;

public class LocationUtil {
    public static String serializeInt(Location loc) {
        return loc.getWorld().getName() + ", " + (int) loc.getX() + ", " + (int) loc.getY() + ", " + (int) loc.getZ();
    }
}
