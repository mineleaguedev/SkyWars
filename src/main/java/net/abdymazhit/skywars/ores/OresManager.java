package net.abdymazhit.skywars.ores;

import net.abdymazhit.skywars.utils.Random;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

/**
 * Менеджер руд
 */
public class OresManager {

    /** Таблица материала и списка списка лута, которые могут выпасть с руды */
    private final Map<Material, List<OreLoot[]>> itemsMap;

    /**
     * Инициализирует руды с лутом
     */
    public OresManager() {
        itemsMap = new HashMap<>();

        itemsMap.put(Material.IRON_ORE, Arrays.asList(
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.STRING), 1),
                        new OreLoot(new ItemStack(Material.FLINT), 1),
                        new OreLoot(new ItemStack(Material.AIR), 3),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.IRON_INGOT, 3), 3),
                        new OreLoot(new ItemStack(Material.IRON_INGOT, 4), 2),
                        new OreLoot(new ItemStack(Material.IRON_INGOT, 5), 1),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.IRON_HELMET), 1),
                        new OreLoot(new ItemStack(Material.IRON_CHESTPLATE), 1),
                        new OreLoot(new ItemStack(Material.IRON_LEGGINGS), 1),
                        new OreLoot(new ItemStack(Material.IRON_BOOTS), 1),
                        new OreLoot(new ItemStack(Material.IRON_SWORD), 1),
                        new OreLoot(new ItemStack(Material.IRON_PICKAXE), 1),
                        new OreLoot(new ItemStack(Material.IRON_AXE), 1),
                        new OreLoot(new ItemStack(Material.AIR), 93),
                }));

        itemsMap.put(Material.DIAMOND_ORE, Arrays.asList(
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.ENDER_PEARL), 1),
                        new OreLoot(new ItemStack(Material.GOLDEN_APPLE), 3),
                        new OreLoot(new ItemStack(Material.AIR), 96),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.DIAMOND, 1), 3),
                        new OreLoot(new ItemStack(Material.DIAMOND, 2), 2),
                        new OreLoot(new ItemStack(Material.DIAMOND, 3), 1),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.DIAMOND_HELMET), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_CHESTPLATE), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_LEGGINGS), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_BOOTS), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_SWORD), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_PICKAXE), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_AXE), 1),
                        new OreLoot(new ItemStack(Material.AIR), 93),
                }));

        itemsMap.put(Material.GOLD_ORE, Arrays.asList(
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.GOLD_HELMET), 9),
                        new OreLoot(new ItemStack(Material.GOLD_CHESTPLATE), 9),
                        new OreLoot(new ItemStack(Material.GOLD_LEGGINGS), 9),
                        new OreLoot(new ItemStack(Material.GOLD_BOOTS), 9),
                        new OreLoot(new ItemStack(Material.GOLD_SWORD), 9),
                        new OreLoot(new ItemStack(Material.GOLD_PICKAXE), 9),
                        new OreLoot(new ItemStack(Material.GOLD_AXE), 9),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.IRON_HELMET), 3),
                        new OreLoot(new ItemStack(Material.IRON_CHESTPLATE), 3),
                        new OreLoot(new ItemStack(Material.IRON_LEGGINGS), 3),
                        new OreLoot(new ItemStack(Material.IRON_BOOTS), 3),
                        new OreLoot(new ItemStack(Material.IRON_SWORD), 3),
                        new OreLoot(new ItemStack(Material.IRON_PICKAXE), 3),
                        new OreLoot(new ItemStack(Material.IRON_AXE), 3),
                },
                new OreLoot[] {
                        new OreLoot(new ItemStack(Material.DIAMOND_HELMET), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_CHESTPLATE), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_LEGGINGS), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_BOOTS), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_SWORD), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_PICKAXE), 1),
                        new OreLoot(new ItemStack(Material.DIAMOND_AXE), 1),
                }));

        itemsMap.put(Material.EMERALD_ORE, Collections.singletonList(
                new OreLoot[] {
                        new OreLoot(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                        new OreLoot(Enchantment.DAMAGE_ALL, 1),
                        new OreLoot(Enchantment.KNOCKBACK, 1),
                        new OreLoot(Enchantment.FIRE_ASPECT, 1),
                        new OreLoot(Enchantment.ARROW_FIRE, 1),
                        new OreLoot(Enchantment.PROTECTION_FIRE, 3),
                        new OreLoot(Enchantment.PROTECTION_EXPLOSIONS, 3),
                        new OreLoot(Enchantment.DIG_SPEED, 3),
                        new OreLoot(Enchantment.PROTECTION_PROJECTILE, 3),
                        new OreLoot(Enchantment.PROTECTION_FALL, 9),
                        new OreLoot(Enchantment.OXYGEN, 9),
                        new OreLoot(Enchantment.DAMAGE_UNDEAD, 9),
                        new OreLoot(Enchantment.DURABILITY, 9),
                        new OreLoot(Enchantment.LUCK, 9),
                }));

        itemsMap.put(Material.LAPIS_ORE, Collections.singletonList(
                new OreLoot[] {
                        new OreLoot(1, 5),
                        new OreLoot(2, 3),
                        new OreLoot(3, 1),
                }));

        itemsMap.put(Material.REDSTONE_ORE, Collections.singletonList(
                new OreLoot[] {
                        new OreLoot(PotionEffectType.NIGHT_VISION.createEffect(90, 0), 3),
                        new OreLoot(PotionEffectType.JUMP.createEffect(60, 0), 2),
                        new OreLoot(PotionEffectType.SPEED.createEffect(60, 0), 2),
                        new OreLoot(PotionEffectType.FIRE_RESISTANCE.createEffect(60, 0), 2),
                        new OreLoot(PotionEffectType.WATER_BREATHING.createEffect(60, 0), 2),
                        new OreLoot(PotionEffectType.INCREASE_DAMAGE.createEffect(30, 0), 1),
                        new OreLoot(PotionEffectType.INVISIBILITY.createEffect(30, 0), 1),
                        new OreLoot(PotionEffectType.REGENERATION.createEffect(30, 0), 1),
                }));
    }

    /**
     * Получает случайный предмет с руды
     * @param material Материал руды
     * @return Случайный предмет с руды
     */
    public ItemStack getRandomItem(Material material) {
        List<OreLoot[]> items = itemsMap.get(material);
        if(items == null) {
            return null;
        }

        OreLoot[] oreLoots = items.get(Random.random.nextInt(items.size()));

        int totalWeight = 0;
        for(OreLoot oreLoot : oreLoots) {
            totalWeight += oreLoot.getWeight();
        }

        int index = 0;
        for(double r = Math.random() * totalWeight; index < oreLoots.length - 1; index++) {
            r -= oreLoots[index].getWeight();
            if(r <= 0.0) {
                break;
            }
        }
        return oreLoots[index].getItemStack();
    }

    /**
     * Получает случайный уровень с руды
     * @param material Материал руды
     * @return Случайный уровень с руды
     */
    public int getRandomLevel(Material material) {
        List<OreLoot[]> levels = itemsMap.get(material);
        if(levels == null) {
            return 0;
        }

        OreLoot[] oreLoots = levels.get(Random.random.nextInt(levels.size()));

        int totalWeight = 0;
        for(OreLoot oreLoot : oreLoots) {
            totalWeight += oreLoot.getWeight();
        }

        int index = 0;
        for(double r = Math.random() * totalWeight; index < oreLoots.length - 1; index++) {
            r -= oreLoots[index].getWeight();
            if(r <= 0.0) {
                break;
            }
        }
        return oreLoots[index].getLevel();
    }

    /**
     * Получает случайный эффект зелья с руды
     * @param material Материал руды
     * @return Случайный эффект зелья
     */
    public PotionEffect getRandomPotionEffect(Material material) {
        List<OreLoot[]> potionEffects = itemsMap.get(material);
        if(potionEffects == null) {
            return null;
        }

        OreLoot[] oreLoots = potionEffects.get(Random.random.nextInt(potionEffects.size()));

        int totalWeight = 0;
        for(OreLoot oreLoot : oreLoots) {
            totalWeight += oreLoot.getWeight();
        }

        int index = 0;
        for(double r = Math.random() * totalWeight; index < oreLoots.length - 1; index++) {
            r -= oreLoots[index].getWeight();
            if(r <= 0.0) {
                break;
            }
        }
        return oreLoots[index].getPotionEffect();
    }
}
