package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Игровое событие перезаполнения сундуков
 */
public class RefillChestsEvent extends GameEvent {

    /** Время до перезаполнения сундуков */
    public static int REFILL_CHESTS_TIME = 60;

    /**
     * Инициализирует игровое событие
     */
    public RefillChestsEvent() {
        timeLeft = REFILL_CHESTS_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Перезап. сундуков " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Удалить голограммы сундуков
            SkyWars.getChestManager().removeOpenedChestsHolograms();
            SkyWars.getChestManager().removeEmptyChestsHolograms();

            // Перезаполнить сундуки лутом
            SkyWars.getChestManager().refillIslandChests();
            SkyWars.getChestManager().refillBasicChests();
            SkyWars.getChestManager().refillMiddleChests();

            // Отправить звук о перезаполнении сундуков
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1, 1);
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
