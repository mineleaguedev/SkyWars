package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие закрытия мистического сундука
 */
public class MysteryChestCloseEvent extends GameEvent {

    /** Время до закрытия мистического сундука */
    public static int MYSTERY_CHEST_CLOSE_TIME = 30;

    /**
     * Инициализирует игровое событие
     */
    public MysteryChestCloseEvent() {
        timeLeft = MYSTERY_CHEST_CLOSE_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Закр. Мист. сундука " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft +
                RefillChestsEvent.REFILL_CHESTS_TIME;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение о закрытии мистического сундука
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§dМистический сундук §cзакрылся§d!");
            }

            // Закрыть мистический сундук
            SkyWars.getChestManager().getMysteryChest().close();

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
