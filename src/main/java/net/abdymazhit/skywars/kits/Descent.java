package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Десант
 */
public class Descent extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.WATER_BUCKET;

    /** Название */
    private static final String name = "Десант";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Железные штаны §8(Взрывоустойчивость II)",
            "§7Динамит §8x16",
            "§7Красная пыль §8x32",
            "§7Каменная кнопка",
            "§7Ведро воды"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     * @param id Id
     */
    public Descent(int id) {
        super(id, material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2, true);
        item1.setItemMeta(itemMeta1);

        items = new ItemStack[] {
                item1,
                new ItemStack(Material.TNT, 16),
                new ItemStack(Material.REDSTONE, 32),
                new ItemStack(Material.STONE_BUTTON),
                new ItemStack(Material.WATER_BUCKET),
        };
    }
}
