package net.abdymazhit.skywars.customs;

import net.abdymazhit.skywars.kits.Kit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Информацию о игроке
 */
public class PlayerInfo {

    /** Статистика игрока */
    private final Stats stats;

    /** Выбранный набор игрока */
    private Kit kit;

    /** Количество убийств игрока в этом матче */
    private int kills;

    /** Количество выпущенных стрел игрока в этом матче */
    private int arrowsFired;

    /** Количество сломанных блоков игрока в этом матче */
    private int blocksBroken;

    /** Количество поставленных блоков игрока в этом матче */
    private int blocksPlaced;

    /** Последний нанесший урон по игроку */
    private Player lastDamager;

    /** Местоположение смерти игрока */
    private Location deathLocation;

    /**
     * Создает информацию о игроке
     * @param stats Статистика
     */
    public PlayerInfo(Stats stats) {
        this.stats = stats;
        this.kit = null;
        this.kills = 0;
        this.arrowsFired = 0;
        this.blocksBroken = 0;
        this.blocksPlaced = 0;
        this.lastDamager = null;
        this.deathLocation = null;
    }

    /**
     * Получает статистику игрока
     * @return Статистика игрока
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Устанавливает выбранный набор игрока
     * @param kit Выбранный набор
     */
    public void setKit(Kit kit) {
        this.kit = kit;
    }

    /**
     * Получает выбранный набор игрока
     * @return Выбранный набор
     */
    public Kit getKit() {
        return kit;
    }

    /**
     * Увеличивает количество убийств игрока в этом матче
     */
    public void addKills() {
        kills++;
    }

    /**
     * Получает количество убийств игрока в этом матче
     * @return Количество убийств игрока в этом матче
     */
    public int getKills() {
        return kills;
    }

    /**
     * Увеличивает количество выпущенных стрел игрока в этом матче
     */
    public void addArrowsFired() {
        arrowsFired++;
    }

    /**
     * Получает количество выпущенных стрел игрока в этом матче
     * @return Количество выпущенных стрел игрока в этом матче
     */
    public int getArrowsFired() {
        return arrowsFired;
    }

    /**
     * Увеличивает количество сломанных блоков игрока в этом матче
     */
    public void addBlocksBroken() {
        blocksBroken++;
    }

    /**
     * Получает количество сломанных блоков игрока в этом матче
     * @return Количество сломанных блоков игрока в этом матче
     */
    public int getBlocksBroken() {
        return blocksBroken;
    }

    /**
     * Увеличивает количество поставленных блоков игрока в этом матче
     */
    public void addBlocksPlaced() {
        blocksPlaced++;
    }

    /**
     * Получает количество поставленных блоков игрока в этом матче
     * @return Количество поставленных блоков игрока в этом матче
     */
    public int getBlocksPlaced() {
        return blocksPlaced;
    }

    /**
     * Установить последнего нанесшего урон по игроку
     * @param lastDamager Последний нанесший урон по игроку
     */
    public void setLastDamager(Player lastDamager) {
        this.lastDamager = lastDamager;
    }

    /**
     * Получает последнего нанесшего урон по игроку
     * @return Последний нанесший урон по игроку
     */
    public Player getLastDamager() {
        return lastDamager;
    }

    /**
     * Устанавливает местоположение смерти игрока
     * @param deathLocation Местоположение смерти игрока
     */
    public void setDeathLocation(Location deathLocation) {
        this.deathLocation = deathLocation;
    }

    /**
     * Получает местоположение смерти игрока
     * @return Местоположение смерти игрока
     */
    public Location getDeathLocation() {
        return deathLocation;
    }
}
