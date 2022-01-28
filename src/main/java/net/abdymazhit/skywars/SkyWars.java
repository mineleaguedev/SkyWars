package net.abdymazhit.skywars;

import net.abdymazhit.skywars.events.cancelled.*;
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

        // Загружает данные с конфиг файла
        Config.load();

        gameManager = new GameManager();

        // Регистрирует отмененные события
        getServer().getPluginManager().registerEvents(new BlockEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new EntityEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new InventoryEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PaintingEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new WeatherEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new WorldEventsListener(), SkyWars.getInstance());
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
