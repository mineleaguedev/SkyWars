package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Воин
 */
public class Warrior extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.IRON_SWORD;

    /** Название */
    private static final String name = "Воин";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Железный меч §8(Острота I)",
            "§7Золотой шлем §8(Защита II)"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     * @param id Id
     */
    public Warrior(int id) {
        super(id, material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.GOLD_HELMET);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        item2.setItemMeta(itemMeta2);

        items = new ItemStack[] {
                item1,
                item2,
        };
    }
}
