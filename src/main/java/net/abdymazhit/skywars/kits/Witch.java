package net.abdymazhit.skywars.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.List;

/**
 * Набор Ведьма
 */
public class Witch extends Kit {

    /** Материал для показа в меню */
    private static final Material material = Material.NETHER_STAR;

    /** Название */
    private static final String name = "Ведьма";

    /** Описание */
    private static final List<String> description = Arrays.asList(
            "§7Бросательное зелье моментального урона",
            "§7Бросательное зелье замедления",
            "§7Бросательное зелье слабости"
    );

    /** Предметы */
    private static final ItemStack[] items;

    /**
     * Инициализирует набор
     * @param id Id
     */
    public Witch(int id) {
        super(id, material, name, description, items);
    }

    static {
        Potion potion1 = new Potion(PotionType.INSTANT_DAMAGE, 1);
        potion1.setSplash(true);
        ItemStack item1 = potion1.toItemStack(1);

        Potion potion2 = new Potion(PotionType.SLOWNESS, 1);
        potion2.setSplash(true);
        ItemStack item2 = potion2.toItemStack(1);

        Potion potion3 = new Potion(PotionType.WEAKNESS, 1);
        potion3.setSplash(true);
        ItemStack item3 = potion3.toItemStack(1);

        items = new ItemStack[] {
                item1,
                item2,
                item3,
        };
    }
}
