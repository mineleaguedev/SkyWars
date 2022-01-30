package net.abdymazhit.skywars.events.cancelled;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * Отменяет события связанные с игроком
 */
public class PlayerEventsListener implements Listener {

    /**
     * Событие получения достижении
     */
    @EventHandler
    public void onPlayerAchievementAwarded(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие взаимодействия с подставкой для брони
     */
    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие взаимодействия с кроватью
     */
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие выкидывания предмета
     */
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения опыта
     */
    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setAmount(0);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setAmount(0);
        }
    }

    /**
     * Событие снижения прочности предмета
     */
    @EventHandler
    public void PlayerItemDamage(PlayerItemDamageEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие поднятия предмета
     */
    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие телепорта через портал
     */
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие увеличения статистики игрока
     */
    @EventHandler
    public void PlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) {
        event.setCancelled(true);
    }
}
