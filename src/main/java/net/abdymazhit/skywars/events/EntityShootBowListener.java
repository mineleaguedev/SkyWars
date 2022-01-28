package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Событие стрельбы из лука entity
 */
public class EntityShootBowListener implements Listener {

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
            return;
        }

        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if(SkyWars.getGameManager().getSpectators().contains(player)) {
            event.setCancelled(true);
            return;
        }

        SkyWars.getGameManager().getPlayerInfo(player).addArrowsFired();
    }
}
