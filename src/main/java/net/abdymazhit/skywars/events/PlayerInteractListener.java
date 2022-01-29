package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Событие взаимодействия игрока с объектом
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(SkyWars.getGameManager().getSpectators().contains(player) || SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }
            if(event.getItem() == null) {
                return;
            }
            SkyWars.getGameItemsManager().useItem(player, event.getItem());
        } else if(SkyWars.getGameManager().getGameState().equals(GameState.GAME)) {
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }

            // Проверка на взаимодействие с мистическим сундуком
            if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                event.setCancelled(true);
                if (Config.mysteryChest.equals(event.getClickedBlock().getLocation())) {
                    if (!SkyWars.getChestManager().getMysteryChest().isOpen()) {
                        player.sendMessage("§cСундук в данный момент закрыт. Приходите позже.");
                    } else {
                        player.openInventory(SkyWars.getChestManager().getMysteryChest().getInventory());
                    }
                }
            }
            // Проверка на взаимодействие с сундуком
            else if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.CHEST)) {
                Chest chest = (Chest) event.getClickedBlock().getState();
                SkyWars.getChestManager().setCurrentOpenedPlayerChest(player, chest);
                SkyWars.getChestManager().addOpenedChestHologram(chest);
            }
            // Проверка на взаимодействие с GPS трекером
            else if (event.getItem() != null && event.getItem().getType().equals(Material.COMPASS)) {
                event.setCancelled(true);
                if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                    return;
                }
                SkyWars.getGameManager().getPlayerTrackerCompass().useItem(player);
            }
        }
    }
}
