package net.abdymazhit.skywars.events;

import net.abdymazhit.skywars.Config;
import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.customs.Island;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Отвечает за событие нанесения урона по entity от другого entity
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityDamageByEntityListener implements Listener {

    /**
     * Событие нанесения урона по entity от другого entity
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(!SkyWars.getGameManager().getGameState().equals(GameState.GAME)) {
            return;
        }

        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        // Проверка, является ли нанесший урон игроком
        if(event.getDamager() instanceof Player) {
            // Отменить событие, если событие начала битвы не началось
            if(SkyWars.getGameManager().isDisabledPvP()) {
                event.setCancelled(true);
            }
            // Установить игроку последнего нанесшего урон по нему игрока
            else {
                Player damager = (Player) event.getDamager();

                for(Island island : Config.islands) {
                    if(island.getPlayers().contains(player) && island.getPlayers().contains(damager)) {
                        event.setCancelled(true);
                        break;
                    }
                }

                if(!event.isCancelled()) {
                    SkyWars.getGameManager().getPlayerInfo(player).setLastDamager(damager);
                }
            }
        }
        // Проверка, является ли нанесший урон снарядом
        else if(event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();

            if(!(projectile.getShooter() instanceof Player)) {
                return;
            }

            // Отменить событие, если событие начала битвы не началось
            if(SkyWars.getGameManager().isDisabledPvP()) {
                event.setCancelled(true);
            }
            // Установить игроку последнего нанесшего урон по нему игрока
            else {
                Player damager = (Player) projectile.getShooter();

                for(Island island : Config.islands) {
                    if(island.getPlayers().contains(player) && island.getPlayers().contains(damager)) {
                        event.setCancelled(true);
                        break;
                    }
                }

                if(!event.isCancelled()) {
                    SkyWars.getGameManager().getPlayerInfo(player).setLastDamager(damager);
                }
            }
        }
    }
}
