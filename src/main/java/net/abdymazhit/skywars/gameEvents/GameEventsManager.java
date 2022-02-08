package net.abdymazhit.skywars.gameEvents;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.customs.Island;
import net.abdymazhit.skywars.enums.GameState;
import net.abdymazhit.skywars.gameEvents.state.GameEndEvent;
import net.abdymazhit.skywars.gameEvents.state.GameStartEvent;
import net.abdymazhit.skywars.kits.Kit;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.WorldBorder;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Менеджер игровых событий, отвечает за изменение стадии игры
 */
public class GameEventsManager {

    /** Task игрового события */
    public BukkitRunnable task;

    /** Время до заполнения сундуков */
    public int timeBeforeRefillingChests;

    /**
     * Начинает стадию игры WAITING
     */
    public void startWaitingState() {
        SkyWars.getGameManager().setGameState(GameState.WAITING);
        SkyWars.getGameManager().getLobbyBoard().setWaitingStatus();
    }

    /**
     * Попытается начать стадию игры STARTING
     */
    public void tryStartStartingState() {
        // Начать стадию STARTING, если набрано достаточное количество игроков
        if (SkyWars.getGameManager().getPlayers().size() == 2) {
            startStartingState();
        }
    }

    /**
     * Начинает стадию игры STARTING
     */
    private void startStartingState() {
        SkyWars.getGameManager().setGameState(GameState.STARTING);
        task = new GameStartEvent();
        task.runTaskTimer(SkyWars.getInstance(), 0L, 20L);
    }

    /**
     * Начинает стадию игры GAME
     */
    public void startGameState() {
        SkyWars.getGameManager().setGameState(GameState.GAME);

        // Установить для игрока остров, если игрок не выбрал остров
        for(Player player : SkyWars.getGameManager().getPlayers()) {
            boolean hasIsland = false;
            for(Island island : Config.islands) {
                if(island.getPlayers().contains(player)) {
                    hasIsland = true;
                    break;
                }
            }

            if(!hasIsland) {
                for(Island island : Config.islands) {
                    if(island.getPlayers().size() < Config.islandPlayers) {
                        island.addPlayer(player);
                        break;
                    }
                }
            }
        }

        // Перекинуть игроков в игру
        for(Island island : Config.islands) {
            for(Player player : island.getPlayers()) {
                player.setFireTicks(0);
                player.setNoDamageTicks(200);
                player.setMaxHealth(20.0);
                player.setHealth(20.0);
                player.setFoodLevel(20);
                player.setSaturation(10);
                player.setFlySpeed(0.1f);
                player.setLevel(0);
                player.setExp(0);

                for(PotionEffect potionEffect : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffect.getType());
                }

                player.setItemOnCursor(null);
                player.closeInventory();

                // Очистить инвентарь игрока
                player.getInventory().setHelmet(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setBoots(null);
                player.getInventory().clear();

                // Установить игровой режим на выживание
                player.setGameMode(GameMode.SURVIVAL);

                // Установить игрокам scoreboard игры
                SkyWars.getGameManager().getGameBoard().setScoreboard(player);
                SkyWars.getGameManager().getGameBoard().updateKillsCount(player);

                // Телепортировать игрока в спавн острова
                player.teleport(island.getSpawn());

                // Выдать игроку набор
                Kit.equip(player);

                // Установить игрокам теги
                EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p != player) {
                        String replacement = "";

                        if (Config.islandPlayers > 1) {
                            replacement = "[" + island.getTag() + "] ";
                        }

                        if(island.getPlayers().contains(p)) {
                            replacement = "§a" + replacement;
                        } else {
                            replacement = "§c" + replacement;
                        }

                        entityPlayer.setCustomName(replacement + entityPlayer.getName());
                        entityPlayer.setCustomNameVisible(true);

                        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, entityPlayer);
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                    }
                }
            }
        }

        // Перекинуть зрителей в игру
        for(Player player : SkyWars.getGameManager().getSpectators()) {
            SkyWars.getGameManager().getGameBoard().setScoreboard(player);
            SkyWars.getGameManager().getGameBoard().updateKillsCount(player);
        }

        // Обновить меню телепортации к игрокам
        SkyWars.getMenuManager().getTeleportMenu().update();

        // Обновить scoreboard игры
        SkyWars.getGameManager().getGameBoard().updateLivePlayersCount();
        SkyWars.getGameManager().getGameBoard().updateSpectatorsCount();

        // Установить зону
        WorldBorder worldBorder = Config.world.getWorldBorder();
        worldBorder.setCenter(Config.mysteryChest);
        worldBorder.setSize(300);

        // Заполнить сундуки лутом
        SkyWars.getChestManager().refillIslandChests();
        SkyWars.getChestManager().refillBasicChests();
        SkyWars.getChestManager().refillMiddleChests();

        // Начать игровое событие начала битвы
        if(GameEvent.gameEvents.size() > 0) {
            Class<? extends GameEvent> clazz = GameEvent.gameEvents.get(0);
            if(clazz != null) {
                try {
                    task = clazz.newInstance();
                    task.runTaskTimer(SkyWars.getInstance(), 0L, 20L);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Начинает стадию игры ENDING
     */
    public void startEndingState() {
        task.cancel();
        SkyWars.getGameManager().setGameState(GameState.ENDING);
        task = new GameEndEvent();
        task.runTaskTimer(SkyWars.getInstance(), 0L, 20L);
    }

    /**
     * Получает время до заполнения сундуков
     * @return Время до заполнения сундуков
     */
    public int getTimeBeforeRefillingChests() {
        return timeBeforeRefillingChests;
    }

    /**
     * Конвертирует время в строку
     * @param time Время
     * @return Время для использования в scoreboard'ах
     */
    public String timeToString(int time) {
        int min = time / 60 % 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);
    }
}
