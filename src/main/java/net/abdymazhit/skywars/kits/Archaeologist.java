package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Археолог
 */
public class Archaeologist extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.DIAMOND_PICKAXE;

    /** Название */
    private static final String name = "Археолог";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Железный шлем",
            "§7Алмазная кирка",
            "§7Алмазная лопата"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     */
    public Archaeologist() {
        super(material, name, description, items);
    }

    static {
        items = new ItemStack[] {
                new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_SPADE),
        };
    }
}
