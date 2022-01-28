package net.abdymazhit.skywars.chests;

import net.abdymazhit.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * Мистический сундук
 */
public class MysteryChest {

    /** Инвентарь мистического сундука */
    private final Inventory inventory;

    /** Значение, открыт ли мистический сундук */
    private boolean isOpen;

    /**
     * Инициализирует мистический сундук
     */
    public MysteryChest() {
        isOpen = false;
        inventory = Bukkit.createInventory(null, 27, "Мистический сундук");
    }

    /**
     * Открывает мистический сундук
     */
    public void open() {
        SkyWars.getChestManager().refillMysteryChest(inventory);
        isOpen = true;
    }

    /**
     * Закрывает мистический сундук
     */
    public void close() {
        for (HumanEntity humanEntity : new ArrayList<>(inventory.getViewers())) {
            humanEntity.closeInventory();
        }

        inventory.clear();
        isOpen = false;
    }

    /**
     * Получает инвентарь мистического сундука
     * @return Инвентарь мистического сундука
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Получает значение, открыт ли мистический сундук
     * @return Значение, открыт ли мистический сундук
     */
    public boolean isOpen() {
        return isOpen;
    }
}
