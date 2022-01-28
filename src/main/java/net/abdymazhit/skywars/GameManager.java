package net.abdymazhit.skywars;

import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Менеджер игры
 */
public class GameManager {

    /** Список живых игроков */
    private final LinkedHashSet<Player> players;

    /** Список зрителей */
    private final LinkedHashSet<Player> spectators;

    /** Стадия игры */
    private GameState gameState;

    /**
     * Инициализирует менеджер игры
     */
    public GameManager() {
        players = new LinkedHashSet<>();
        spectators = new LinkedHashSet<>();
        gameState = GameState.WAITING;
    }

    /**
     * Добавляет игрока в список живых игроков
     * @param player Добавляет игрока в список живых игроков
     */
    private void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Удаляет игрока из списка живых игроков
     * @param player Игрок
     */
    private void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Добавляет игрока в список зрителей
     * @param player Игрок
     */
    private void addSpectator(Player player) {
        spectators.add(player);
    }

    /**
     * Удаляет игрока из списка зрителей
     * @param player Игрок
     */
    private void removeSpectator(Player player) {
        spectators.remove(player);
    }

    /**
     * Устанавливает стадию игры
     * @param gameState Стадия игры
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Получает стадию игры
     * @return Стадия игры
     */
    public GameState getGameState() {
        return gameState;
    }
}
