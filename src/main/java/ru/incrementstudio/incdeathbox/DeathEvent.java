package ru.incrementstudio.incdeathbox;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import ru.incrementstudio.incdeathbox.utils.ColorUtil;
import ru.incrementstudio.incdeathbox.utils.ConfigUtil;
import ru.incrementstudio.incdeathbox.utils.LocationUtil;
import ru.incrementstudio.incdeathbox.utils.PlayerUtil;

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

            ArmorStand armorstand = (ArmorStand) location.getWorld().spawnEntity(location.add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
            armorstand.setCollidable(false);
            armorstand.setInvulnerable(true);
            armorstand.setSilent(true);
            armorstand.setSmall(true);
            armorstand.setFireTicks(0);
            armorstand.setVisible(false);
            armorstand.setCanMove(false);
            armorstand.setGravity(false);
            armorstand.setCustomName(ColorUtil.toColor(Files.config.get().getString("box-name")
                    .replace("%player%", PlayerUtil.getName(player))
            ));
            armorstand.setCustomNameVisible(true);
        }
        event.getDrops().clear();
    }
}
