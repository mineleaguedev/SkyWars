package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * Событие ломания блока игроком
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }

        if(!event.isCancelled()) {
            Player player = event.getPlayer();

            SkyWars.getGameManager().getPlayerInfo(player).addBlocksBroken();

            Material material = event.getBlock().getType();

            if(material.equals(Material.ENDER_CHEST)) {
                if (Config.mysteryChest.equals(event.getBlock().getLocation())) {
                    event.setCancelled(true);
                }
                return;
            }

            ItemStack itemStack = SkyWars.getOresManager().getRandomItem(material);
            if(itemStack != null) {
                if(!itemStack.getType().equals(Material.AIR)) {
                    Location location = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                    location.getWorld().dropItem(location, itemStack);
                }
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                return;
            }

            List<ItemStack> itemStacks = SkyWars.getOresManager().getRandomItems(material);
            if(itemStacks != null && !itemStacks.isEmpty()) {
                for(ItemStack item : itemStacks) {
                    if(item != null && !item.getType().equals(Material.AIR)) {
                        Location location = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                        location.getWorld().dropItem(location, item);
                    }
                }
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                return;
            }

            int level = SkyWars.getOresManager().getRandomLevel(material);
            if(level != 0) {
                player.giveExpLevels(level);
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                return;
            }

            PotionEffect potionEffect = SkyWars.getOresManager().getRandomPotionEffect(material, player.getActivePotionEffects());
            if(potionEffect != null) {
                if(player.getActivePotionEffects().isEmpty()) {
                    player.addPotionEffect(potionEffect);
                    SkyWars.getGameManager().getPlayerInfo(player).setNextRemovablePotionEffectType(potionEffect.getType());
                } else {
                    if(player.getActivePotionEffects().size() != 1) {
                        player.removePotionEffect(SkyWars.getGameManager().getPlayerInfo(player).getNextRemovablePotionEffectType());
                        SkyWars.getGameManager().getPlayerInfo(player).setNextRemovablePotionEffectType(player.getActivePotionEffects().iterator().next().getType());
                    }
                    player.addPotionEffect(potionEffect);
                }
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
            }
        }
    }
}
