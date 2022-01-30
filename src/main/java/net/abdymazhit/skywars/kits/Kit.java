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

    /** Таблица id набора и набора */
    public static Map<Integer, Kit> idToKit;

    /** Таблица набора и id набора */
    public static Map<Kit, Integer> kitToId;

    /** Id */
    public final int id;

    /** Материал для показа в меню */
    private final Material material;

    /** Название */
    private final String name;

    /** Описание */
    private final List<String> description;

    /** Предметы */
    private final ItemStack[] items;

    /**
     * Регистрирует все наборы
     */
    public static void registerKits() {
        idToKit = new HashMap<>();
        kitToId = new HashMap<>();
        new Warrior(1);
        new Archer(2);
        new Assassin(3);
        new Snowman(4);
        new Battleship(5);
        new Archaeologist(6);
        new Descent(7);
        new Witch(8);
        new Magician(9);
    }

    /**
     * Инициализирует набор
     */
    public Kit(int id, Material material, String name, List<String> description, ItemStack[] items) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.description = description;
        this.items = items;
        idToKit.put(id, this);
        kitToId.put(this, id);
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
}
