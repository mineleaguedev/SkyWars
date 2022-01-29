package net.abdymazhit.skywars.items;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.chests.ItemUsage;
import net.abdymazhit.skywars.menu.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер игровых предметов
 */
public class GameItemsManager {

    /** Хранит предметы лобби с их слотом */
    private final Map<ItemStack, Integer> lobbyItems;

    /** Хранит предметы зрителя с их слотом */
    private final Map<ItemStack, Integer> spectatorItems;

    /** Хранит функцию использования предмета */
    private final Map<ItemStack, ItemUsage> itemUsage;

    /** Хранит информацию о меню предмета */
    private final Map<ItemStack, Menu> itemMenu;

    /** Меню телепортации к игрокам */
    private final TeleportMenu teleportMenu;

    /** Меню островов */
    private final IslandsMenu islandsMenu;

    /** Меню настроек зрителя для каждого зрителя */
    private final Map<Player, SpectatorSettingsMenu> spectatorSettingsMenus;

    /** Меню наборов для каждого игрока */
    private final Map<Player, KitsMenu> playerKitsMenus;

    /**
     * Инициализирует игровые предметы
     */
    public GameItemsManager() {
        lobbyItems = new HashMap<>();
        spectatorItems = new HashMap<>();
        itemUsage = new HashMap<>();
        itemMenu = new HashMap<>();
        islandsMenu = new IslandsMenu();
        spectatorSettingsMenus = new HashMap<>();
        playerKitsMenus = new HashMap<>();

        ItemStack teleportItem = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta teleportItemMeta = teleportItem.getItemMeta();
        teleportItemMeta.setDisplayName("§r>> §e§lТелепортация к игрокам §r<<");
        List<String> teleportItemLore = new ArrayList<>();
        teleportItemLore.add("§7Нажмите ПКМ, чтобы открыть");
        teleportItemLore.add("§7меню телепортации к игрокам");
        teleportItemMeta.setLore(teleportItemLore);
        teleportItem.setItemMeta(teleportItemMeta);
        spectatorItems.put(teleportItem, 0);
        itemMenu.put(teleportItem, teleportMenu = new TeleportMenu());
        itemUsage.put(teleportItem, player -> itemMenu.get(teleportItem).open(player));

        ItemStack spectatorSettingsItem = new ItemStack(Material.DIODE);
        ItemMeta spectatorSettingsItemMeta = spectatorSettingsItem.getItemMeta();
        spectatorSettingsItemMeta.setDisplayName("§r>> §e§lНастройки зрителя §r<<");
        List<String> spectatorSettingsItemLore = new ArrayList<>();
        spectatorSettingsItemLore.add("§7Нажмите ПКМ, чтобы открыть");
        spectatorSettingsItemLore.add("§7меню настроек зрителя");
        spectatorSettingsItemMeta.setLore(spectatorSettingsItemLore);
        spectatorSettingsItem.setItemMeta(spectatorSettingsItemMeta);
        spectatorItems.put(spectatorSettingsItem, 1);
        itemUsage.put(spectatorSettingsItem, player -> spectatorSettingsMenus.get(player).open(player));

        // Проверка, зарегистрированы ли слоты в меню выбора острова для текущего формата игры
        if (islandsMenu.getIslandsIdSlot().size() != Config.islands.size()) {
            SkyWars.getInstance().getLogger().warning("Возможность выбора острова отключена!");
            SkyWars.getInstance().getLogger().warning("Для формата игры " + Config.islandPlayers + "x" + Config.islands.size() + " не зарегистрированы слоты для меню выбора острова!");
        }
        // Слоты зарегистрированы, можно выдавать предмет выбора острова
        else {
            ItemStack islandsItem = new ItemStack(Material.NAME_TAG);
            ItemMeta islandsItemMeta = islandsItem.getItemMeta();
            islandsItemMeta.setDisplayName("§r>> §e§lВыбор острова §r<<");
            List<String> islandsItemLore = new ArrayList<>();
            islandsItemLore.add("§7Нажмите ПКМ, чтобы открыть");
            islandsItemLore.add("§7меню выбора острова");
            islandsItemMeta.setLore(islandsItemLore);
            islandsItem.setItemMeta(islandsItemMeta);
            lobbyItems.put(islandsItem, 0);
            itemMenu.put(islandsItem, islandsMenu);
            itemUsage.put(islandsItem, player -> itemMenu.get(islandsItem).open(player));

            islandsMenu.update();
        }

        ItemStack kitsItem = new ItemStack(Material.BOW);
        ItemMeta kitsItemMeta = kitsItem.getItemMeta();
        kitsItemMeta.setDisplayName("§r>> §e§lВыбор набора §r<<");
        List<String> kitsItemLore = new ArrayList<>();
        kitsItemLore.add("§7Нажмите ПКМ, чтобы открыть");
        kitsItemLore.add("§7меню выбора набора");
        kitsItemMeta.setLore(kitsItemLore);
        kitsItem.setItemMeta(kitsItemMeta);
        lobbyItems.put(kitsItem, 2);
        itemUsage.put(kitsItem, player -> playerKitsMenus.get(player).open(player));

        ItemStack cosmeticsItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta cosmeticsItemMeta = cosmeticsItem.getItemMeta();
        cosmeticsItemMeta.setDisplayName("§r>> §e§lКосметика §r<<");
        List<String> cosmeticsItemLore = new ArrayList<>();
        cosmeticsItemLore.add("§7Нажмите ПКМ, чтобы открыть");
        cosmeticsItemLore.add("§7меню косметики");
        cosmeticsItemMeta.setLore(cosmeticsItemLore);
        cosmeticsItem.setItemMeta(cosmeticsItemMeta);
        lobbyItems.put(cosmeticsItem, 3);

        ItemStack meItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meItemMeta = meItem.getItemMeta();
        List<String> meItemLore = new ArrayList<>();
        meItemLore.add("§7Нажмите ПКМ, чтобы посмотреть задания,");
        meItemLore.add("§7достижения или изменить личные настройки");
        meItemMeta.setLore(meItemLore);
        meItem.setItemMeta(meItemMeta);
        lobbyItems.put(meItem, 7);
        spectatorItems.put(meItem, 7);

        ItemStack leaveItem = new ItemStack(Material.COMPASS);
        ItemMeta leaveItemMeta = leaveItem.getItemMeta();
        leaveItemMeta.setDisplayName("§r>> §e§lВернуться в лобби §r<<");
        List<String> leaveItemLore = new ArrayList<>();
        leaveItemLore.add("§7Нажмите ПКМ, чтобы");
        leaveItemLore.add("§7покинуть игру");
        leaveItemMeta.setLore(leaveItemLore);
        leaveItem.setItemMeta(leaveItemMeta);
        lobbyItems.put(leaveItem, 8);
        spectatorItems.put(leaveItem, 8);
    }

    /**
     * Выдает игроку предметы лобби
     * @param player Игрок
     */
    public void giveLobbyItems(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().clear();

        for (ItemStack itemStack : lobbyItems.keySet()) {
            int slot = lobbyItems.get(itemStack);
            player.getInventory().setItem(slot, itemStack);
        }

        ItemStack meItem = player.getInventory().getItem(7);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setDisplayName("§r>> §e§l" + player.getName() + " §r<<");
        meItem.setItemMeta(meItemMeta);
    }

    /**
     * Выдает зрителю предметы зрителя
     * @param player Зритель
     */
    public void giveSpectatorItems(Player player) {
        // Выдать предметы после 1 тика
        // Если выдавать сразу, предметы не выдадутся, потому что событие PlayerDeathEvent очищает инвентарь после выдачи
        new BukkitRunnable() {
            @Override
            public void run() {
                player.getInventory().setHelmet(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setBoots(null);
                player.getInventory().clear();

                for (ItemStack itemStack : spectatorItems.keySet()) {
                    int slot = spectatorItems.get(itemStack);
                    player.getInventory().setItem(slot, itemStack);
                }

                ItemStack meItem = player.getInventory().getItem(7);
                ItemMeta meItemMeta = meItem.getItemMeta();
                meItemMeta.setDisplayName("§r>> §e§l" + player.getName() + " §r<<");
                meItem.setItemMeta(meItemMeta);
            }
        }.runTaskLater(SkyWars.getInstance(), 1L);
    }

    /**
     * Добавляет зрителю меню настроек зрителя
     * @param player Зритель
     */
    public void addSpectatorSettingsMenu(Player player) {
        spectatorSettingsMenus.put(player, new SpectatorSettingsMenu());
    }

    /**
     * Добавляет игроку меню наборов
     * @param player Игрок
     */
    public void addPlayerKitsMenu(Player player) {
        playerKitsMenus.put(player, new KitsMenu(player));
    }

    /**
     * Обрабатывает событие использования предмета
     * @param player Игрок
     * @param itemStack Предмет
     */
    public void useItem(Player player, ItemStack itemStack) {
        if (itemUsage.get(itemStack) != null) {
            itemUsage.get(itemStack).use(player);
        }
    }

    /**
     * Обрабатывает событие клика по инвентарю
     * @param player Игрок
     * @param inventory Инвентарь
     */
    public void clickInventory(Player player, Inventory inventory, int slot) {
        // Значение, найдено ли меню
        boolean isFoundMenu = false;

        for (ItemStack itemStack : itemMenu.keySet()) {
            Menu menu = itemMenu.get(itemStack);
            if (menu.getInventory().equals(inventory)) {
                isFoundMenu = true;
                menu.clickSlot(player, slot);
            }
        }

        // Если меню не найдено, тогда меню является уникальным
        // Уникальное меню для каждого игрока - настройки зрителя
        if (!isFoundMenu) {
            if (spectatorSettingsMenus.get(player) != null) {
                spectatorSettingsMenus.get(player).clickSlot(player, slot);
            }
        }
    }

    /**
     * Получает меню телепортации к игрокам
     * @return Меню телепортации к игрокам
     */
    public TeleportMenu getTeleportMenu() {
        return teleportMenu;
    }

    /**
     * Получает меню островов
     * @return Меню островов
     */
    public IslandsMenu getIslandsMenu() {
        return islandsMenu;
    }
}
