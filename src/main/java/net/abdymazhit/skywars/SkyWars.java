package net.abdymazhit.skywars;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 */
public class SkyWars extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWars instance;

    /**
     * Событие включения плагина
     * Инициализирует нужные объекты для работы плагина
     */
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
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
}
