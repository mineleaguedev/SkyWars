package net.abdymazhit.skywars;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 */
public class SkyWars extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWars instance;

    /** Менеджер игры */
    private static GameManager gameManager;

    /**
     * Событие включения плагина
     * Инициализирует нужные объекты для работы плагина
     */
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        gameManager = new GameManager();
    }

    /**
     * Событие выключения плагина
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Получает менеджер игры
     * @return Менеджер игры
     */
    public static GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Получает экземпляр плагина
     * @return Экземпляр плагина
     */
    public static SkyWars getInstance() {
        return instance;
    }
}
