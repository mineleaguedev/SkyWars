package net.abdymazhit.skywars.events.cancelled;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

/**
 * Отменяет события связанные с инвентарем
 */
public class InventoryEventsListener implements Listener {

    /**
     * Событие плавления предмета в печке
     */
    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие извлечения предмета из печки
     */
    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setExpToDrop(0);
        }
    }

    /**
     * Событие завершения плавления предмета в печке
     */
    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие перетаскивания предмета в инвентаре
     */
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if(SkyWars.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Событие поднятия предмета инвентарём (хоппер)
     */
    @EventHandler
    public void onInventoryPickupItem(InventoryPickupItemEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }
}
