package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие конца сужения игровой зоны
 */
public class GameZoneEndEvent extends GameEvent {

    /** Время до конца сужения игровой зоны */
    public static int GAME_ZONE_END_TIME = 30;
    
    /**
     * Инициализирует игровое событие
     */
    public GameZoneEndEvent() {
        timeLeft = GAME_ZONE_END_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Конец суж. зоны " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft +
                RefillChests2Event.REFILL_CHESTS2_TIME;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о конце сужения зоны
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§fСужение зоны закончилось!", 4, 20, 4);
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
