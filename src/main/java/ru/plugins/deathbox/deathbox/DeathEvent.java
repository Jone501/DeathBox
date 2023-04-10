package ru.plugins.deathbox.deathbox;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.plugins.deathbox.deathbox.utils.ColorUtil;
import ru.plugins.deathbox.deathbox.utils.LocationUtil;
import ru.plugins.deathbox.deathbox.utils.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Inventory inventory = player.getInventory();
        Location location = player.getLocation();

        List<ItemStack> items = new ArrayList<>();
        for (final ItemStack item : event.getDrops()) {
            items.add(item);
        }

        if (items.size() > 0) {
            Location signLocation = LocationUtil.addToLocation(location, 0, 1, 0);
            signLocation.getBlock().setType(Material.OAK_SIGN);
            Sign sign = (Sign) signLocation.getBlock().getState();

            List<String> lines = Files.texts.get().getStringList("sign-text");
            for (int i = 0; i < lines.size() && i < 4; i++) {
                sign.setLine(i, ColorUtil.toColor(lines.get(i)
                        .replace("%player%", PlayerUtil.getName(player))
                ));
                sign.update();
            }
        }
        event.getDrops().clear();
    }
}
