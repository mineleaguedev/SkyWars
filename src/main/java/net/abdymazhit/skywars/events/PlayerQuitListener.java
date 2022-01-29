package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.customs.Island;
import net.abdymazhit.skywars.customs.PlayerKillEvent;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Событие выхода игрока из сервера
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            if(SkyWars.getGameManager().getPlayers().contains(player)) {
                for(Island island : Config.islands) {
                    if(island.getPlayers().contains(player)) {
                        island.removePlayer(player);
                    }
                }

                SkyWars.getGameManager().removePlayer(player);
                SkyWars.getGameManager().getLobbyBoard().updatePlayersCount();

                SkyWars.getGameItemsManager().getIslandsMenu().update();

                event.setQuitMessage("[" + SkyWars.getGameManager().getPlayers().size() + "/" + Config.islands.size() * Config.islandPlayers + "] " + "§e=> §fИгрок " + player.getDisplayName() + " §fотключился");
            } else if(SkyWars.getGameManager().getSpectators().contains(player)) {
                SkyWars.getGameManager().removeSpectator(player);
            }
        } else if(SkyWars.getGameManager().getGameState().equals(GameState.GAME)) {
            if(SkyWars.getGameManager().getPlayers().contains(player)) {
                PlayerKillEvent playerKillEvent = new PlayerKillEvent(player);
                Bukkit.getPluginManager().callEvent(playerKillEvent);

                SkyWars.getGameManager().removeSpectator(player);
                SkyWars.getGameManager().getGameBoard().updateSpectatorsCount();
            } else if(SkyWars.getGameManager().getSpectators().contains(player)) {
                SkyWars.getGameManager().removeSpectator(player);
                SkyWars.getGameManager().getGameBoard().updateSpectatorsCount();
            }
        } else if(SkyWars.getGameManager().getGameState().equals(GameState.ENDING)) {
            SkyWars.getGameManager().removePlayer(player);
            SkyWars.getGameManager().removeSpectator(player);
            SkyWars.getGameManager().getGameBoard().updateSpectatorsCount();
        }

        SkyWars.getGameManager().removePlayerInfo(player);
     }
}
