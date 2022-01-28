package net.abdymazhit.skywars.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Меню
 */
public class Menu {

    /** Инвентарь */
    private Inventory inventory;

    /**
     * Устанавливает инвентарь
     * @param inventory Инвентарь
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Получает инвентарь
     * @return Инвентарь
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Открывает меню для игрока
     * @param player Игрок
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Обрабатывает событие клика по слоту меню
     * @param player Игрок
     * @param slot Слот
     */
    public void clickSlot(Player player, int slot) {

    }
}
