package net.abdymazhit.skywars.ores;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

/**
 * Случайный лут с руды
 */
class OreLoot {

    /** Предмет */
    private ItemStack itemStack;

    /** Уровень */
    private int level;

    /** Эффект зелья */
    private PotionEffect potionEffect;

    /** Вес лута */
    private final int weight;

    /**
     * Инициализирует случайный предмет
     * @param itemStack Предмет
     * @param weight Вес лута
     */
    public OreLoot(ItemStack itemStack, int weight) {
        this.itemStack = itemStack;
        this.weight = weight;
    }

    /**
     * Инициализирует случайную книгу с зачарованием
     * @param enchantment Зачарование
     * @param weight Вес лута
     */
    public OreLoot(Enchantment enchantment, int weight) {
        itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, 1, true);
        itemStack.setItemMeta(itemMeta);
        this.weight = weight;
    }

    /**
     * Инициализирует случайный уровень
     * @param level Уровень
     * @param weight Вес лута
     */
    public OreLoot(int level, int weight) {
        this.level = level;
        this.weight = weight;
    }

    /**
     * Инициализирует случайный эффект зелья
     * @param potionEffect Эффект зелья
     * @param weight Вес лута
     */
    public OreLoot(PotionEffect potionEffect, int weight) {
        this.potionEffect = potionEffect;
        this.weight = weight;
    }

    /**
     * Получает предмет
     * @return Предмет
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Получает уровень
     * @return Уровень
     */
    public int getLevel() {
        return level;
    }

    /**
     * Получает эффект зелья
     * @return Эффект зелья
     */
    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    /**
     * Получает вес лута
     * @return Вес лута
     */
    public int getWeight() {
        return weight;
    }
}
