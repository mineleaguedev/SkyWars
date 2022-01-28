package net.abdymazhit.skywars.scoreboards;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Scoreboard лобби
 */
public class LobbyBoard {

    /** Scoreboard */
    private final Scoreboard scoreboard;

    /**
     * Инициализирует scoreboard лобби
     */
    public LobbyBoard() {
        scoreboard = SkyWars.getInstance().getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sw1", "sw2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§fКарта: §l" + Config.mapName).setScore(6);
        objective.getScore("  ").setScore(5);

        Team players = scoreboard.registerNewTeam("players");
        players.addEntry("§f§lИгроков: §a");
        objective.getScore("§f§lИгроков: §a").setScore(4);

        Team status = scoreboard.registerNewTeam("status");
        status.addEntry("§f§l");
        objective.getScore("§f§l").setScore(3);

        objective.getScore("").setScore(2);
        objective.getScore("§a§lMineLeague.ru").setScore(1);

        objective.setDisplayName("§b§lSkyWars");
    }

    /**
     * Установить всем игрокам статус scoreboard на WAITING
     */
    public void setWaitingStatus() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("status").setSuffix("Ожидание игроков...");
        }
    }

    /**
     * Установить игроку статус scoreboard'а на WAITING
     * @param player Игрок
     */
    public void setWaitingStatus(Player player) {
        player.getScoreboard().getTeam("status").setSuffix("Ожидание игроков...");
    }

    /**
     * Установить всем игрокам статус scoreboard на начало с обратным отсчетом
     * @param text Текст обратного отсчета
     */
    public void setStartingStatus(String text) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("status").setSuffix(text);
        }
    }

    /**
     * Обновить количество игроков в scoreboard'е
     */
    public void updatePlayersCount() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("players").setSuffix(SkyWars.getGameManager().getPlayers().size() + "/" + Config.islands.size() * Config.islandPlayers);
        }
    }

    /**
     * Установить игроку scoreboard
     * @param player Игрок
     */
    public void setScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }
}
