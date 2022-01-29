package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

/**
 * Игровое событие начала сужения зоны
 */
public class GameZoneStartEvent extends GameEvent {

    /** Время до начала сужения зоны */
    public static int GAME_ZONE_START_TIME = 50;

    /**
     * Инициализирует игровое событие
     */
    public GameZoneStartEvent() {
        timeLeft = GAME_ZONE_START_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Начало суж. зоны " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft +
                MysteryChestOpenEvent.MYSTERY_CHEST_OPEN_TIME +
                MysteryChestCloseEvent.MYSTERY_CHEST_CLOSE_TIME +
                RefillChestsEvent.REFILL_CHESTS_TIME;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о начале сужения зоны
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§fСужение зоны началось!", 4, 20, 4);
            }

            // Начать сужение зоны
            WorldBorder worldBorder = Config.world.getWorldBorder();
            worldBorder.setSize(40, 180);

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
