package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Событие поставки блока игроком
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
            return;
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }

        SkyWars.getGameManager().getPlayerInfo(event.getPlayer()).addBlocksPlaced();
    }
}
