package net.abdymazhit.skywars.gameEvents;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Игровое событие
 */
public class GameEvent extends BukkitRunnable {

    /** Список игровых событий */
    public static final List<Class<? extends GameEvent>> gameEvents;

    /** Оставшееся время до игрового события */
    public int timeLeft;

    /**
     * Регистрирует игровое событие
     * @param clazz Класс игрового события
     */
    private static void registerGameEvent(Class<? extends GameEvent> clazz) {
        gameEvents.add(clazz);
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {

    }

    /**
     * Получает класс следующего события
     * @param clazz Класс текущего события
     * @return Класс следующего события
     */
    public static Class<? extends GameEvent> getNextEventClass(Class<? extends GameEvent> clazz) {
        for(int i = 0; i < gameEvents.size(); i++) {
            if(gameEvents.get(i) == clazz) {
                Class<? extends GameEvent> c = gameEvents.get(i + 1);
                if(c != null) {
                    return c;
                }
            }
        }
        return null;
    }

    static {
        gameEvents = new ArrayList<>();
        registerGameEvent(BattleStartEvent.class);
        registerGameEvent(GameZoneStartEvent.class);
        registerGameEvent(MysteryChestOpenEvent.class);
        registerGameEvent(MysteryChestCloseEvent.class);
        registerGameEvent(RefillChestsEvent.class);
        registerGameEvent(GameZoneEndEvent.class);
        registerGameEvent(RefillChests2Event.class);
        registerGameEvent(DeathmatchStartEvent.class);
        registerGameEvent(HealthDecreaseEvent.class);
    }
}
