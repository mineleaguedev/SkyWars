package net.abdymazhit.skywars.events.cancelled;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Отменяет события связанные с entity
 */
public class EntityEventsListener implements Listener {

    /**
     * Событие рождения entity
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        // Проверка причины спавна на CUSTOM (через плагин) или SPAWNER_EGG (через яйцо призыва)
        if(!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && !event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие возгорания entity
     */
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения уровня голода
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(SkyWars.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
