package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

/**
 * Событие клика по слоту инвентаря
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null || !(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
            SkyWars.getGameItemsManager().clickInventory(player, event.getInventory(), event.getSlot());
        } else {
            onClickWithEnchantmentBook(event);

            if(SkyWars.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
                if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                    return;
                }
                SkyWars.getGameItemsManager().clickInventory(player, event.getInventory(), event.getSlot());
            }

            // Проверка, пытается ли игрок забрать лазурит с стола зачарований
            if (event.getClickedInventory() instanceof EnchantingInventory && event.getSlot() == 1) {
                // Отменить клик, так как игрок пытается забрать себе лазурит
                event.setCancelled(true);
            }
        }
    }

    /**
     * Событие клика по предмету книгой зачарования
     * @param event Событие клика слота инвентаря
     */
    private void onClickWithEnchantmentBook(InventoryClickEvent event) {
        if(!event.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) {
            return;
        }

        ItemStack cursorItem = event.getCursor();
        ItemStack currentItem = event.getCurrentItem();

        if(!cursorItem.getType().equals(Material.ENCHANTED_BOOK) || currentItem.getType().equals(Material.AIR)) {
            return;
        }

        if(!(cursorItem.getItemMeta() instanceof EnchantmentStorageMeta)) {
            return;
        }

        EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) cursorItem.getItemMeta();
        for(Enchantment enchantment : enchantmentStorageMeta.getStoredEnchants().keySet()) {
            if(currentItem.getItemMeta().hasConflictingEnchant(enchantment)) {
                continue;
            }

            if(!enchantment.canEnchantItem(currentItem)) {
                continue;
            }

            currentItem.addEnchantment(enchantment, enchantmentStorageMeta.getStoredEnchants().get(enchantment));
            enchantmentStorageMeta.removeStoredEnchant(enchantment);

            if(enchantmentStorageMeta.getStoredEnchants().isEmpty()) {
                event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
            }
        }
    }
}