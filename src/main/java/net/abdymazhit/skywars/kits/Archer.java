package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Лучник
 */
public class Archer extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.BOW;

    /** Название */
    private static final String name = "Лучник";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Лук §8(Сила I)",
            "§7Стрела §8x24"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     * @param id Id
     */
    public Archer(int id) {
        super(id, material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.BOW);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        item1.setItemMeta(itemMeta1);

        items = new ItemStack[] {
                item1,
                new ItemStack(Material.ARROW, 24),
        };
    }
}
