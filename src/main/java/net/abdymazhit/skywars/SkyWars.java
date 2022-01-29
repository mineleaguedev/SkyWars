package net.abdymazhit.skywars;

import net.abdymazhit.skywars.chests.ChestManager;
import net.abdymazhit.skywars.events.*;
import net.abdymazhit.skywars.events.cancelled.*;
import net.abdymazhit.skywars.gameEvents.GameEventsManager;
import net.abdymazhit.skywars.items.GameItemsManager;
import net.abdymazhit.skywars.ores.OresManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 */
public class SkyWars extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWars instance;

    /** Менеджер игры */
    private static GameManager gameManager;

    /** Менеджер рейтинга */
    private static RatingManager ratingManager;

    /** Менеджер игровых событий */
    private static GameEventsManager gameEventsManager;

    /** Менеджер игровых предметов */
    private static GameItemsManager gameItemsManager;

    /** Менеджер сундуков */
    private static ChestManager chestManager;

    /** Менеджер руд */
    private static OresManager oresManager;

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
        ratingManager = new RatingManager();
        gameEventsManager = new GameEventsManager();
        gameItemsManager = new GameItemsManager();
        chestManager = new ChestManager();
        oresManager = new OresManager();

        // Регистрирует отмененные события
        getServer().getPluginManager().registerEvents(new BlockEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new EntityEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new InventoryEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PaintingEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new WeatherEventsListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new WorldEventsListener(), SkyWars.getInstance());

        // Регистрирует игровые события
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new EntityShootBowListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new InventoryOpenListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), SkyWars.getInstance());
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), SkyWars.getInstance());
    }

    /**
     * Событие выключения плагина
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Получает экземпляр плагина
     * @return Экземпляр плагина
     */
    public static SkyWars getInstance() {
        return instance;
    }

    /**
     * Получает менеджер игры
     * @return Менеджер игры
     */
    public static GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Получает менеджер рейтинга
     * @return Менеджер рейтинга
     */
    public static RatingManager getRatingManager() {
        return ratingManager;
    }

    /**
     * Получает менеджер игровых событий
     * @return Менеджер игровых событий
     */
    public static GameEventsManager getGameEventsManager() {
        return gameEventsManager;
    }

    /**
     * Получает менеджер игровых предметов
     * @return Менеджер игровых предметов
     */
    public static GameItemsManager getGameItemsManager() {
        return gameItemsManager;
    }

    /**
     * Получает менеджер сундуков
     * @return Менеджер сундук
     */
    public static ChestManager getChestManager() {
        return chestManager;
    }

    /**
     * Получает менеджер руд
     * @return Менеджер руд
     */
    public static OresManager getOresManager() {
        return oresManager;
    }
}
