package net.abdymazhit.skywars.menu;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.customs.Island;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Меню островов
 */
public class IslandsMenu extends Menu {

    /** Хранит информацию о id островах и их слотах */
    private final Map<Integer, Integer> islandsIdSlot;

    /**
     * Инициализирует меню островов
     */
    public IslandsMenu() {
        setInventory(Bukkit.createInventory(null, 27, "Выбор острова"));

        islandsIdSlot = new HashMap<>();

        // Добавить слоты для островов по id
        if (Config.islands.size() == 2) {
            islandsIdSlot.put(1, 11);
            islandsIdSlot.put(2, 15);
        } else if (Config.islands.size() == 4) {
            islandsIdSlot.put(1, 10);
            islandsIdSlot.put(2, 11);
            islandsIdSlot.put(3, 15);
            islandsIdSlot.put(4, 16);
        } else if (Config.islands.size() == 6) {
            islandsIdSlot.put(1, 10);
            islandsIdSlot.put(2, 11);
            islandsIdSlot.put(3, 12);
            islandsIdSlot.put(4, 14);
            islandsIdSlot.put(5, 15);
            islandsIdSlot.put(6, 16);
        } else if (Config.islands.size() == 8) {
            islandsIdSlot.put(1, 2);
            islandsIdSlot.put(2, 3);
            islandsIdSlot.put(3, 5);
            islandsIdSlot.put(4, 6);
            islandsIdSlot.put(5, 20);
            islandsIdSlot.put(6, 21);
            islandsIdSlot.put(7, 23);
            islandsIdSlot.put(8, 24);
        } else if (Config.islands.size() == 10) {
            islandsIdSlot.put(1, 1);
            islandsIdSlot.put(2, 2);
            islandsIdSlot.put(3, 3);
            islandsIdSlot.put(4, 5);
            islandsIdSlot.put(5, 6);
            islandsIdSlot.put(6, 7);
            islandsIdSlot.put(7, 10);
            islandsIdSlot.put(8, 11);
            islandsIdSlot.put(9, 15);
            islandsIdSlot.put(10, 16);
        } else if (Config.islands.size() == 12) {
            islandsIdSlot.put(1, 1);
            islandsIdSlot.put(2, 2);
            islandsIdSlot.put(3, 3);
            islandsIdSlot.put(4, 5);
            islandsIdSlot.put(5, 6);
            islandsIdSlot.put(6, 7);
            islandsIdSlot.put(7, 19);
            islandsIdSlot.put(8, 20);
            islandsIdSlot.put(9, 21);
            islandsIdSlot.put(10, 23);
            islandsIdSlot.put(11, 24);
            islandsIdSlot.put(12, 25);
        } else if (Config.islands.size() == 16) {
            islandsIdSlot.put(1, 0);
            islandsIdSlot.put(2, 1);
            islandsIdSlot.put(3, 2);
            islandsIdSlot.put(4, 3);
            islandsIdSlot.put(5, 5);
            islandsIdSlot.put(6, 6);
            islandsIdSlot.put(7, 7);
            islandsIdSlot.put(8, 8);
            islandsIdSlot.put(9, 18);
            islandsIdSlot.put(10, 19);
            islandsIdSlot.put(11, 20);
            islandsIdSlot.put(12, 21);
            islandsIdSlot.put(13, 23);
            islandsIdSlot.put(14, 24);
            islandsIdSlot.put(15, 25);
            islandsIdSlot.put(16, 26);
        } else if (Config.islands.size() == 20) {
            islandsIdSlot.put(1, 0);
            islandsIdSlot.put(2, 1);
            islandsIdSlot.put(3, 2);
            islandsIdSlot.put(4, 3);
            islandsIdSlot.put(5, 5);
            islandsIdSlot.put(6, 6);
            islandsIdSlot.put(7, 7);
            islandsIdSlot.put(8, 8);
            islandsIdSlot.put(9, 9);
            islandsIdSlot.put(10, 10);
            islandsIdSlot.put(11, 16);
            islandsIdSlot.put(12, 17);
            islandsIdSlot.put(13, 18);
            islandsIdSlot.put(14, 19);
            islandsIdSlot.put(15, 20);
            islandsIdSlot.put(16, 21);
            islandsIdSlot.put(17, 23);
            islandsIdSlot.put(18, 24);
            islandsIdSlot.put(19, 25);
            islandsIdSlot.put(20, 26);
        }
    }

    /**
     * Обновляет меню
     */
    public void update() {
        for(Island island : Config.islands) {
            if(island.getPlayers().size() >= Config.islandPlayers) {
                setIslandLockedItem(island);
            } else {
                setIslandAvailableItem(island);
            }
        }
    }

    /**
     * Устанавливает предмет доступности для выбора острова
     * @param island Остров
     */
    private void setIslandAvailableItem(Island island) {
        ItemStack islandItem = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta islandItemMeta = islandItem.getItemMeta();
        islandItemMeta.setDisplayName("§rОстров " + island.getId() + " [" + island.getPlayers().size() + "/" + Config.islandPlayers + "]");
        List<String> lore = new ArrayList<>();
        for(Player player : island.getPlayers()) {
            lore.add("§f" + player.getDisplayName());
        }
        lore.add("§5§oНажмите для выбора");
        islandItemMeta.setLore(lore);
        islandItem.setItemMeta(islandItemMeta);
        getInventory().setItem(islandsIdSlot.get(island.getId()), islandItem);
    }

    /**
     * Устанавливает предмет недоступности для выбора острова
     * @param island Остров
     */
    private void setIslandLockedItem(Island island) {
        ItemStack islandItem = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
        ItemMeta islandItemMeta = islandItem.getItemMeta();
        islandItemMeta.setDisplayName("§rОстров " + island.getId() + " [" + island.getPlayers().size() + "/" + Config.islandPlayers + "]");
        List<String> lore = new ArrayList<>();
        for(Player player : island.getPlayers()) {
            lore.add("§f" + player.getDisplayName());
        }
        islandItemMeta.setLore(lore);
        islandItem.setItemMeta(islandItemMeta);
        getInventory().setItem(islandsIdSlot.get(island.getId()), islandItem);
    }

    /**
     * Обрабатывает событие клика по слоту меню
     * @param player Игрок
     * @param slot Слот
     */
    @Override
    public void clickSlot(Player player, int slot) {
        super.clickSlot(player, slot);

        // Проверка нажатого предмета на EMERALD_BLOCK
        // EMERALD_BLOCK означает, что остров доступен для выбора
        if(getInventory().getItem(slot) != null && getInventory().getItem(slot).getType().equals(Material.EMERALD_BLOCK)) {
            for(int id : islandsIdSlot.keySet()) {
                int islandSlot = islandsIdSlot.get(id);
                if(islandSlot == slot) {
                    select(player, id);
                    break;
                }
            }
        }
    }

    /**
     * Выбирает игроку остров
     * @param player Игрок
     * @param islandId Id острова
     */
    private void select(Player player, int islandId) {
        // Удалить старый остров игрока
        for(Island island : Config.islands) {
            if(island.getPlayers().contains(player)) {
                island.removePlayer(player);
            }
        }

        Config.islands.get(islandId - 1).addPlayer(player);
        update();
    }

    /**
     * Получает информацию о id островах и их слотах
     * @return Информация о id островах и их слотах
     */
    public Map<Integer, Integer> getIslandsIdSlot() {
        return islandsIdSlot;
    }
}
