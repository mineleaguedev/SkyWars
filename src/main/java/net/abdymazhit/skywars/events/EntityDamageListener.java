package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Событие нанесения урона по entity
 */
public class EntityDamageListener implements Listener {
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
                if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    player.teleport(Config.lobbyLocation);
                }
                event.setCancelled(true);
            } else if(SkyWars.getGameManager().getGameState().equals(GameState.GAME)) {
                if(SkyWars.getGameManager().getSpectators().contains(player)) {
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        player.teleport(Config.lobbyLocation);
                    }
                    event.setCancelled(true);
                }
            } else if(SkyWars.getGameManager().getGameState().equals(GameState.ENDING)) {
                if(SkyWars.getGameManager().getSpectators().contains(player)) {
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        player.teleport(Config.lobbyLocation);
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
