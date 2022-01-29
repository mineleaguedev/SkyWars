package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие начала битвы
 */
public class BattleStartEvent extends GameEvent {

    /** Время до начала битвы */
    public static int BATTLE_START_TIME = 10;

    /**
     * Инициализирует игровое событие
     */
    public BattleStartEvent() {
        timeLeft = BATTLE_START_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Начало битвы " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Отправить сообщение в центр экрана о времени до начале битвы
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

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft +
                GameZoneStartEvent.GAME_ZONE_START_TIME +
                MysteryChestOpenEvent.MYSTERY_CHEST_OPEN_TIME +
                MysteryChestCloseEvent.MYSTERY_CHEST_CLOSE_TIME +
                RefillChestsEvent.REFILL_CHESTS_TIME;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о начале битвы
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§6ИГРА НАЧАЛАСЬ!", 4, 12, 4);
            }

            // Разрешить PvP между игроками
            SkyWars.getGameManager().setEnabledPvP(true);

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
