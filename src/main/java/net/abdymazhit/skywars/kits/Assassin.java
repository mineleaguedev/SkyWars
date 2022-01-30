package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Ассасин
 */
public class Assassin extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.SUGAR;

    /** Название */
    private static final String name = "Ассасин";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Железный меч §8(Острота I)",
            "§7Зелье невидимости §8x2"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     * @param id Id
     */
    public Assassin(int id) {
        super(id, material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        item1.setItemMeta(itemMeta1);

        Potion potion2 = new Potion(PotionType.INVISIBILITY, 1);
        ItemStack item2 = potion2.toItemStack(1);

        Potion potion3 = new Potion(PotionType.INVISIBILITY, 1);
        ItemStack item3 = potion3.toItemStack(1);

        items = new ItemStack[] {
                item1,
                item2,
                item3,
        };
    }
}
