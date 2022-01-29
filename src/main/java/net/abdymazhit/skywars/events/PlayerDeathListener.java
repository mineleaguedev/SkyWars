package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.customs.PlayerKillEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Событие смерти игрока
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.setHealth(20);
        event.setDeathMessage(null);

        PlayerKillEvent playerKillEvent = new PlayerKillEvent(player);
        Bukkit.getPluginManager().callEvent(playerKillEvent);

        if(SkyWars.getGameManager().getPlayerInfo(player).getDeathLocation().getY() < 0) {
            event.getDrops().clear();
        }
    }
}
