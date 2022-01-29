package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Событие закрытия инвентаря
 */
public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
            Player player = (Player) event.getPlayer();

            Chest chest = SkyWars.getChestManager().getCurrentOpenedPlayerChest(player);
            if(chest != null) {
                SkyWars.getChestManager().setCurrentOpenedPlayerChest(player, null);

                // Добавить голограмму пустого сундука, если инвентарь является пустым
                boolean isEmpty = true;
                for(ItemStack item : event.getInventory().getContents()) {
                    if (item != null && item.getType() != Material.AIR) {
                        isEmpty = false;
                    }
                }

                if(isEmpty) {
                    SkyWars.getChestManager().addEmptyChestHologram(chest);
                } else {
                    SkyWars.getChestManager().removeEmptyChestHologram(chest);
                }
            }
        }
        // Проверка, является ли инвентарь столом зачарований
        else if (event.getInventory() instanceof EnchantingInventory) {
            event.getInventory().setItem(1, null);
        }
    }
}
