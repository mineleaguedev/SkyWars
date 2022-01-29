package net.abdymazhit.skywars.gameEvents.state;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.gameEvents.GameEvent;

/**
 * Игровое событие начала игры
 */
public class GameStartEvent extends GameEvent {

    /** Время до начала игры */
    public static int GAME_START_TIME = 15;

    /**
     * Инициализирует игровое событие
     */
    public GameStartEvent() {
        timeLeft = GAME_START_TIME;
    }

    /**
     * Таймер обратного отсчета
     */
    @Override
    public void run() {
        // Проверить, не вышли ли игроки с игры
        if (SkyWars.getGameManager().getPlayers().size() < 2) {
            // Установить игру на WAITING, так как игроки вышли из игры
            SkyWars.getGameEventsManager().startWaitingState();
            cancel();
        } else {
            // Изменить таймер начала игры в scoreboard'е лобби
            SkyWars.getGameManager().getLobbyBoard().setStartingStatus("До начала: §a" + SkyWars.getGameEventsManager().timeToString(timeLeft));

            // Начать стадию игры GAME
            if (timeLeft-- <= 0) {
                SkyWars.getGameEventsManager().startGameState();
                cancel();
            }
        }
    }
}
