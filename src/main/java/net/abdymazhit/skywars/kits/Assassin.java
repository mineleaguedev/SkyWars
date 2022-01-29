package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
     */
    public Assassin() {
        super(material, name, description, items);
    }

    static {
        ItemStack item1 = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.POTION);
        PotionMeta itemMeta2 = (PotionMeta) item2.getItemMeta();
        itemMeta2.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30, 0), true);
        item2.setItemMeta(itemMeta2);

        ItemStack item3 = new ItemStack(Material.POTION);
        PotionMeta itemMeta3 = (PotionMeta) item3.getItemMeta();
        itemMeta3.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30, 0), true);
        item3.setItemMeta(itemMeta3);

        items = new ItemStack[] {
                item1,
                item2,
                item3,
        };
    }
}
