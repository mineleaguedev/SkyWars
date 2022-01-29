package net.abdymazhit.skywars.gameEvents.state;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.gameEvents.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие конца игры
 */
public class GameEndEvent extends GameEvent {

    /** Время до конца игры */
    public static int timeLeft = 15;

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Обновить таймер конца игры в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Конец игры " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Завершить игру
        if (timeLeft-- <= 0) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer("Игра завершена");
            }

            Bukkit.getServer().unloadWorld(Config.world, true);
            Bukkit.shutdown();

            cancel();
        }
    }
}
