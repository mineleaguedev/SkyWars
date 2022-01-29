package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Чародей
 */
public class Magician extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.EXP_BOTTLE;

    /** Название */
    private static final String name = "Чародей";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Стол зачарования",
            "§7Пузырёк опыта §8x24",
            "§7Книжная полка §8x9"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     */
    public Magician() {
        super(material, name, description, items);
    }

    static {
        items = new ItemStack[] {
                new ItemStack(Material.ENCHANTMENT_TABLE),
                new ItemStack(Material.EXP_BOTTLE, 24),
                new ItemStack(Material.BOOKSHELF, 9),
        };
    }
}
