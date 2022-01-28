package net.abdymazhit.skywars.kits;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.customs.PlayerInfo;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Набор
 */
public class Kit {

    /** Таблица id набора и класса набора */
    public static final Map<Integer, Class<? extends Kit>> idToKit;

    /** Таблица класса набора и id набора */
    public static final Map<Class<? extends Kit>, Integer> kitToId;

    /** Id */
    public final int id;

    /** Материал для показа */
    private final Material material;

    /** Название */
    private final String name;

    /** Описание */
    private final List<String> description;

    /** Предметы */
    private final ItemStack[] items;

    /**
     * Регистрирует набор
     * @param id Id набора
     * @param clazz Класс набора
     */
    private static void registerKit(int id, Class<? extends Kit> clazz) {
        Kit.idToKit.put(id, clazz);
        Kit.kitToId.put(clazz, id);
    }

    /**
     * Инициализирует набор
     */
    public Kit(Material material, String name, List<String> description, ItemStack[] items) {
        id = Kit.kitToId.get(getClass());
        this.material = material;
        this.name = name;
        this.description = description;
        this.items = items;
    }

    /**
     * Выдает набор игроку
     * @param player Игрок
     */
    public static void equip(Player player) {
        PlayerInfo playerInfo = SkyWars.getGameManager().getPlayerInfo(player);
        Kit kit = playerInfo.getKit();
        player.getInventory().addItem(kit.items);
    }

    /**
     * Получает материал
     * @return Материал
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Получает название
     * @return Название
     */
    public String getName() {
        return name;
    }

    /**
     * Получает описание
     * @return Описание
     */
    public List<String> getDescription() {
        return description;
    }

    static {
        idToKit = new HashMap<>();
        kitToId = new HashMap<>();
    }
}
