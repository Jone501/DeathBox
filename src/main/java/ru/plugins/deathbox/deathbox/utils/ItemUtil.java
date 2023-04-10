package ru.plugins.deathbox.deathbox.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemUtil {
    public static ItemStack createItemStack(Material material, String amount, String name, List<String> lore, List<String> enchants, String chancedrop) {
        ItemStack itemStack = null;
        if (material != null) {
            itemStack = new ItemStack(material);
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (name != null) {
                itemMeta.setDisplayName(name);
            }
            if (lore != null) {
                itemMeta.setLore(lore);
            }
            for (String fullName: enchants) {
                String[] strings = fullName.split(":");
                if (strings.length != 2) continue;
                Enchantment enchantment = Enchantment.getByName(strings[0]);
                if (enchantment == null) continue;
                int value;
                try {
                    value = Integer.parseInt(strings[1]);
                } catch (NumberFormatException ignored) { continue; }
                if (value == 0) continue;
                itemMeta.addEnchant(enchantment, value, true);
            }
            if (chancedrop != null) {
                itemMeta.getPersistentDataContainer().set(Objects.requireNonNull(
                        NamespacedKey.fromString("chancedrop")),
                        PersistentDataType.STRING,
                        chancedrop);
            }
            if (amount != null) {
                itemMeta.getPersistentDataContainer().set(Objects.requireNonNull(
                        NamespacedKey.fromString("amount")),
                        PersistentDataType.STRING,
                        amount);
            }
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }


}
