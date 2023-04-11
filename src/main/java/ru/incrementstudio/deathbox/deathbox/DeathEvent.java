package ru.incrementstudio.deathbox.deathbox;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import ru.incrementstudio.deathbox.deathbox.utils.ConfigUtil;
import ru.incrementstudio.deathbox.deathbox.utils.LocationUtil;
import ru.incrementstudio.deathbox.deathbox.utils.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = player.getLocation().toBlockLocation();

        List<ItemStack> items = new ArrayList<>();
        for (ItemStack item : event.getDrops()) {
            items.add(item);
        }

        if (items.size() > 0) {
            Files.deathboxes.get().set(ConfigUtil.combinePath(LocationUtil.serializeInt(location), "player-name"), PlayerUtil.getName(player));
            for (int i = 0; i < items.size(); i++) {
                Files.deathboxes.get().set(ConfigUtil.combinePath(LocationUtil.serializeInt(location), "items", String.valueOf(i)), items.get(i));
            }
            Files.deathboxes.save();

            location.getBlock().setType(Material.valueOf(Files.config.get().getString("box-block-type")));
        }
        event.getDrops().clear();
    }
}
