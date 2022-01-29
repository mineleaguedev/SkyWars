package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EnchantingInventory;

/**
 * Событие клика по слоту инвентаря
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null || !(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
            SkyWars.getGameItemsManager().clickInventory(player, event.getInventory(), event.getSlot());
        }

        if(SkyWars.getGameManager().getSpectators().contains(player)) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
            SkyWars.getGameItemsManager().clickInventory(player, event.getInventory(), event.getSlot());
        }

        // Проверка, пытается ли игрок забрать лазурит с стола зачарований
        if (event.getClickedInventory() instanceof EnchantingInventory && event.getSlot() == 1) {
            // Отменить клик, так как игрок пытается забрать себе лазурит
            event.setCancelled(true);
        }
    }
}