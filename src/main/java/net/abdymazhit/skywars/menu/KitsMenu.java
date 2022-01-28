package net.abdymazhit.skywars.menu;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Меню наборов
 */
public class KitsMenu extends Menu {

    /**
     * Инициализирует меню наборов
     */
    public KitsMenu(Player player) {
        setInventory(Bukkit.createInventory(null, 45, "Выбор набора"));

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
            try {
                Class<? extends Kit> clazz = Kit.idToKit.get(id);
                if(clazz == null) {
                    return;
                }

                Kit kit = clazz.newInstance();

                int slot = slots.get(index);
                index++;

                getInventory().setItem(slot, getKitItem(player, kit));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
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
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = kit.getDescription();
        lore.add("");
        if(SkyWars.getGameManager().getPlayerInfo(player).getKit() == kit) {
            lore.add("§aВЫБРАНО");
            itemMeta.setDisplayName("§a" + kit.getName());
        } else {
            lore.add("§eНажмите, чтобы выбрать");
            itemMeta.setDisplayName("§e" + kit.getName());
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
