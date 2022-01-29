package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Событие входа игрока в сервер
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            SkyWars.getGameManager().getLobbyBoard().setScoreboard(player);
            SkyWars.getGameManager().getLobbyBoard().setWaitingStatus(player);

            if(SkyWars.getGameManager().getPlayers().size() < Config.islands.size() * Config.islandPlayers) {
                SkyWars.getGameManager().addPlayer(player);
                event.setJoinMessage("[" + SkyWars.getGameManager().getPlayers().size() + "/" + Config.islands.size() * Config.islandPlayers + "] " + "§e=> §fИгрок " + player.getDisplayName() + " §fподключился");
                SkyWars.getGameManager().getLobbyBoard().updatePlayersCount();
            } else {
                SkyWars.getGameManager().addSpectator(player);
            }
        } else {
            SkyWars.getGameManager().getGameBoard().setScoreboard(player);
            SkyWars.getGameManager().addSpectator(player);
            SkyWars.getGameManager().getGameBoard().updateSpectatorsCount();
        }
    }
}
