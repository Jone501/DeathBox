package ru.incrementstudio.deathbox.deathbox;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ru.incrementstudio.deathbox.deathbox.utils.ConfigUtil;
import ru.incrementstudio.deathbox.deathbox.utils.LocationUtil;

public class BlockEvent implements Listener {
    @EventHandler
    public void onRightClickOnBlock(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (Files.deathboxes.get().getConfigurationSection("").contains(LocationUtil.serializeInt(block.getLocation()))) {
            block.setType(Material.AIR);
            for (String item : Files.deathboxes.get().getConfigurationSection(ConfigUtil.combinePath(LocationUtil.serializeInt(block.getLocation()), "items")).getKeys(false)) {
                ItemStack itemStack = Files.deathboxes.get().getItemStack(ConfigUtil.combinePath(LocationUtil.serializeInt(block.getLocation()), "items", item));
                block.getWorld().dropItem(block.getLocation().add(0.5, 0.5, 0.5), itemStack);
                block.getWorld().spawnParticle(
                        Particle.valueOf(Files.config.get().getString("open-particle")),
                        block.getLocation().add(0.5, 0, 0.5),
                        Files.config.get().getInt("open-particle-count")
                );
                block.getWorld().playSound(
                        block.getLocation().add(0.5, 0.5, 0.5),
                        Sound.valueOf(Files.config.get().getString("open-sound")),
                        (float) Files.config.get().getDouble("open-sound-volume"),
                        (float) Files.config.get().getDouble("open-sound-pitch")
                );
            }
            Files.deathboxes.get().set(ConfigUtil.combinePath(LocationUtil.serializeInt(block.getLocation())), null);
            Files.deathboxes.save();
        }
    }
}
