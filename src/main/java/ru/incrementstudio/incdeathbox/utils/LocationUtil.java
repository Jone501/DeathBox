package ru.incrementstudio.incdeathbox.utils;

import org.bukkit.Location;

public class LocationUtil {
    public static String serializeInt(Location loc) {
        return loc.getWorld().getName() + ", " + (int) loc.getX() + ", " + (int) loc.getY() + ", " + (int) loc.getZ();
    }
}
