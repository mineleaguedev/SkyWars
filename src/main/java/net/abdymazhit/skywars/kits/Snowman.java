package net.abdymazhit.skywars.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Снеговик
 */
public class Snowman extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.SNOW_BALL;

    /** Название */
    private static final String name = "Снеговик";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Кожаная броня §8(Белая)",
            "§7Снежок §8x32",
            "§7Железная лопата"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     */
    public Snowman() {
        super(material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta itemMeta1 = (LeatherArmorMeta) item1.getItemMeta();
        itemMeta1.setColor(Color.WHITE);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta itemMeta2 = (LeatherArmorMeta) item2.getItemMeta();
        itemMeta2.setColor(Color.WHITE);
        item2.setItemMeta(itemMeta2);

        ItemStack item3 = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta itemMeta3 = (LeatherArmorMeta) item3.getItemMeta();
        itemMeta3.setColor(Color.WHITE);
        item3.setItemMeta(itemMeta3);

        ItemStack item4 = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta itemMeta4 = (LeatherArmorMeta) item4.getItemMeta();
        itemMeta4.setColor(Color.WHITE);
        item4.setItemMeta(itemMeta4);

        items = new ItemStack[] {
                item1,
                item2,
                item3,
                item4,
                new ItemStack(Material.SNOW_BALL, 32),
                new ItemStack(Material.IRON_SPADE)
        };
    }
}
