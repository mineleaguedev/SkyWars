package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Броненосец
 */
public class Battleship extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.IRON_HELMET;

    /** Название */
    private static final String name = "Броненосец";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Железный шлем §8(Защита I)",
            "§7Железный нагрудник §8(Защита I)"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     */
    public Battleship() {
        super(material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        item2.setItemMeta(itemMeta2);

        items = new ItemStack[] {
                item1,
                item2,
        };
    }
}
