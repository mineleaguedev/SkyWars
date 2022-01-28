package net.abdymazhit.skywars.events;

import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

/**
 * Событие открытия инвентаря
 */
public class InventoryOpenListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if(event.getInventory() instanceof EnchantingInventory) {
            Dye dye = new Dye();
            dye.setColor(DyeColor.BLUE);
            ItemStack itemStack = dye.toItemStack(64);
            event.getInventory().setItem(1, itemStack);
        }
    }
}
