package ru.plugins.deathbox.deathbox.utils;

import org.bukkit.Location;

public class LocationUtil {
    public static Location addToLocation(Location loc, double x, double y, double z) {
        return new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
    }
}
