package net.abdymazhit.skywars.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер меню
 */
public class MenuManager {

    /** Меню телепортации к игрокам */
    private final TeleportMenu teleportMenu;

    /** Меню островов */
    private final IslandsMenu islandsMenu;

    /** Меню наборов для каждого игрока */
    private final Map<Player, KitsMenu> playerKitsMenus;

    /** Меню настроек зрителя для каждого зрителя */
    private final Map<Player, SpectatorSettingsMenu> spectatorSettingsMenus;

    /**
     * Инициализирует менеджер меню
     */
    public MenuManager() {
        teleportMenu = new TeleportMenu();
        islandsMenu = new IslandsMenu();
        playerKitsMenus = new HashMap<>();
        spectatorSettingsMenus = new HashMap<>();
    }

    /**
     * Добавляет игроку меню наборов
     * @param player Игрок
     */
    public void addPlayerKitsMenu(Player player) {
        playerKitsMenus.put(player, new KitsMenu(player));
    }

    /**
     * Добавляет зрителю меню настроек зрителя
     * @param player Зритель
     */
    public void addSpectatorSettingsMenu(Player player) {
        spectatorSettingsMenus.put(player, new SpectatorSettingsMenu());
    }

    /**
     * Получает меню телепортации к игрокам
     * @return Меню телепортации к игрокам
     */
    public TeleportMenu getTeleportMenu() {
        return teleportMenu;
    }

    /**
     * Получает меню островов
     * @return Меню островов
     */
    public IslandsMenu getIslandsMenu() {
        return islandsMenu;
    }

    /**
     * Получает меню наборов игрока
     * @param player Игрок
     * @return Меню наборов игрока
     */
    public KitsMenu getPlayerKitsMenu(Player player) {
        return playerKitsMenus.get(player);
    }

    /**
     * Получает меню настроек зрителя
     * @param player Зритель
     * @return Меню настроек зрителя
     */
    public SpectatorSettingsMenu getSpectatorSettingsMenu(Player player) {
        return spectatorSettingsMenus.get(player);
    }
}
