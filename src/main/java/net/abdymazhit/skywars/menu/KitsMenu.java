package net.abdymazhit.skywars.menu;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Меню наборов
 */
public class KitsMenu extends Menu {

    /** Таблица слотов и наборов */
    private final Map<Integer, Kit> slotsKitsMap;

    /**
     * Инициализирует меню наборов
     */
    public KitsMenu(Player player) {
        setInventory(Bukkit.createInventory(null, 45, "Выбор набора"));

        slotsKitsMap = new HashMap<>();

        // Добавить слоты, которые доступны для наборов
        List<Integer> slots = new ArrayList<>();
        for(int slot = 10; slot < 17; slot++) {
            slots.add(slot);
        }
        for(int slot = 19; slot < 26; slot++) {
            slots.add(slot);
        }
        for(int slot = 28; slot < 35; slot++) {
            slots.add(slot);
        }

        // Установить предметы наборов для выбора
        int index = 0;
        for(int id = 1; id < Kit.idToKit.size() + 1; id++) {
            Kit kit = Kit.idToKit.get(id);

            int slot = slots.get(index);
            index++;

            slotsKitsMap.put(slot, kit);
            getInventory().setItem(slot, getKitItem(player, slotsKitsMap.get(slot)));
        }
    }

    /**
     * Обрабатывает событие клика по слоту меню
     * @param player Игрок
     * @param slot Слот
     */
    @Override
    public void clickSlot(Player player, int slot) {
        super.clickSlot(player, slot);

        int previousSlot = 0;
        Kit previousKit = null;
        for(int s : slotsKitsMap.keySet()) {
            Kit kit = slotsKitsMap.get(s);
            if(SkyWars.getGameManager().getPlayerInfo(player).getKit().equals(kit)) {
                previousSlot = s;
                previousKit = kit;
                break;
            }
        }

        for(int s : slotsKitsMap.keySet()) {
            if(slot == s) {
                Kit kit = slotsKitsMap.get(s);
                SkyWars.getGameManager().getPlayerInfo(player).setKit(kit);
                getInventory().setItem(s, getKitItem(player, kit));
                if(previousKit != null) {
                    getInventory().setItem(previousSlot, getKitItem(player, previousKit));
                }
                break;
            }
        }
    }

    /**
     * Получает предмет набора
     * @param kit Набор
     * @return Предмет набора
     */
    public ItemStack getKitItem(Player player, Kit kit) {
        ItemStack itemStack = new ItemStack(kit.getMaterial());
        if(SkyWars.getGameManager().getPlayerInfo(player).getKit().equals(kit)) {
            itemStack.addUnsafeEnchantment(Enchantment.LURE, 1);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(kit.getDescription());
        if(SkyWars.getGameManager().getPlayerInfo(player).getKit().equals(kit)) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.setDisplayName("§a" + kit.getName() + " | Выбрано");
        } else {
            lore.add("");
            lore.add("§eНажмите, чтобы выбрать");
            itemMeta.setDisplayName("§e" + kit.getName());
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
