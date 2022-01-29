package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие начала детматча
 */
public class DeathmatchStartEvent extends GameEvent {

    /** Время до начала детматча */
    public static int DEATHMATCH_START_TIME = 60;

    /**
     * Инициализирует игровое событие
     */
    public DeathmatchStartEvent() {
        timeLeft = DEATHMATCH_START_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Детматч " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Отправить сообщения в центр экрана о времени до начала детматча
        if(timeLeft == 5) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§f5", 4, 12, 4);
            }
        } else if(timeLeft == 4) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§f4", 4, 12, 4);
            }
        } else if(timeLeft == 3) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§e3", 4, 12, 4);
            }
        } else if(timeLeft == 2) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§62", 4, 12, 4);
            }
        } else if(timeLeft == 1) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§6§c1", 4, 12, 4);
            }
        }

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщения в центр экрана о начале детматча
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§6§cДетматч!", 4, 12, 4);
            }

            // Телепортировать игроков в местоположение детматча
            int id = 0;
            for(Player player : SkyWars.getGameManager().getPlayers()) {
                player.teleport(Config.deathmatchSpawns.get(id));
                id++;
            }

            // Начать следующее игровое событие
            Class<? extends GameEvent> clazz = GameEvent.getNextEventClass(getClass());
            if(clazz != null) {
                try {
                    SkyWars.getGameEventsManager().task = clazz.newInstance();
                    SkyWars.getGameEventsManager().task.runTaskTimer(SkyWars.getInstance(), 0L, 20L);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // Отменить текущее событие
            cancel();
        }
    }
}
