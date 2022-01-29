package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Игровое событие снижения здоровья у игроков
 */
public class HealthDecreaseEvent extends GameEvent {

    /** Время до начала снижения здоровья у игроков */
    public static int HEALTH_DECREASE_TIME = 60;

    /**
     * Инициализирует игровое событие
     */
    public HealthDecreaseEvent() {
        timeLeft = HEALTH_DECREASE_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWars.getGameManager().getGameBoard().updateEvent("Сниж. здор. игроков " + SkyWars.getGameEventsManager().timeToString(timeLeft));

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщения в центр экрана о начале снижения здоровья игроков
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§cСнижение здоровья игроков!", 4, 20, 4);
            }

            // Начать снижение здоровья игроков
            // Каждую секунду здоровье будет уменьшаться на 2 хп
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player player : SkyWars.getGameManager().getPlayers()) {
                        player.damage(2);
                    }

                    if(SkyWars.getGameManager().getPlayers().size() == 1) {
                        cancel();
                    }
                }
            }.runTaskTimer(SkyWars.getInstance(), 0L, 20L);

            // Отменить текущее событие
            cancel();
        }
    }
}
