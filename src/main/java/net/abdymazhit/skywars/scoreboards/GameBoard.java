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
 * Scoreboard игры
 */
public class GameBoard {

    /** Scoreboard */
    private final Scoreboard scoreboard;

    /**
     * Инициализирует scoreboard игры
     */
    public GameBoard() {
        scoreboard = SkyWars.getInstance().getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sw1", "sw2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§fКарта: §l" + Config.mapName).setScore(11);
        objective.getScore("   ").setScore(10);

        objective.getScore("§f§lСледующее событие:").setScore(9);

        Team event = scoreboard.registerNewTeam("event");
        event.addEntry("§a");
        objective.getScore("§a").setScore(8);

        objective.getScore("  ").setScore(7);

        Team players = scoreboard.registerNewTeam("livePlayers");
        players.addEntry("§f§lЖивых игроков: §a");
        objective.getScore("§f§lЖивых игроков: §a").setScore(6);

        Team kills = scoreboard.registerNewTeam("kills");
        kills.addEntry("§f§lУбийств: §a");
        objective.getScore("§f§lУбийств: §a").setScore(5);

        objective.getScore(" ").setScore(4);

        Team spectators = scoreboard.registerNewTeam("spectators");
        spectators.addEntry("§f§lЗрителей: §a");
        objective.getScore("§f§lЗрителей: §a").setScore(3);

        objective.getScore("").setScore(2);
        objective.getScore("§a§lMineLeague.ru").setScore(1);

        objective.setDisplayName("§b§lSkyWars");
    }

    /**
     * Установить всем игрокам следующее событие в scoreboard с обратным отсчетом
     * @param text Текст обратного отсчета
     */
    public void updateEvent(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("event").setSuffix(text);
        }
    }

    /**
     * Обновить всем игрокам количество живых игроков в scoreboard
     */
    public void updateLivePlayersCount() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("livePlayers").setSuffix(String.valueOf(SkyWars.getGameManager().getPlayers().size()));
        }
    }

    /**
     * Обновить игроку количество убийств в scoreboard'е
     * @param player Игрок
     */
    public void updateKillsCount(Player player) {
        player.getScoreboard().getTeam("kills").setSuffix(String.valueOf(SkyWars.getGameManager().getPlayerInfo(player).getKills()));
    }

    /**
     * Обновить всем игрокам количество зрителей в scoreboard'е
     */
    public void updateSpectatorsCount() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("spectators").setSuffix(String.valueOf(SkyWars.getGameManager().getSpectators().size()));
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
