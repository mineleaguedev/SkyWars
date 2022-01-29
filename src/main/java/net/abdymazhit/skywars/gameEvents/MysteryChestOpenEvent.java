package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Игровое событие открытия мистического сундука
 */
public class MysteryChestOpenEvent extends GameEvent {

    /** Время до открытия мистического сундука */
    public static int MYSTERY_CHEST_OPEN_TIME = 60;

    /**
     * Инициализирует игровое событие
     */
    public MysteryChestOpenEvent() {
        timeLeft = MYSTERY_CHEST_OPEN_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Отк. Мист. сундука " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        SkyWars.getGameEventsManager().timeBeforeRefillingChests = timeLeft +
                MysteryChestCloseEvent.MYSTERY_CHEST_CLOSE_TIME +
                RefillChestsEvent.REFILL_CHESTS_TIME;
        SkyWars.getChestManager().updateOpenedChestsHologramsTimer(SkyWars.getGameEventsManager().timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение о открытии мистического сундука
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§dМистический сундук §aоткрыт§d! Поспешите забрать свои законные вещи!");
            }

            // Открыть мистический сундук
            SkyWars.getChestManager().getMysteryChest().open();

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
