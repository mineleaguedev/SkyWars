package net.abdymazhit.skywars.events.cancelled;

import net.abdymazhit.skywars.SkyWars;
import net.abdymazhit.skywars.enums.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

/**
 * Отменяет события связанные с блоками
 */
public class BlockEventsListener implements Listener {

    /**
     * Событие разрушения блока в результате сожжения огнем
     */
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие получения информации можно ли ставить блок
     */
    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setBuildable(false);
        }
    }

    /**
     * Событие повреждения блока игроком
     */
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие выдачи предмета блоком
     */
    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие получения опыта блоком
     */
    @EventHandler
    public void onBlockExp(BlockExpEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setExpToDrop(0);
        }
    }

    /**
     * Событие взрыва блока
     */
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие исчезания блока мировым условием
     */
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока мировым условием
     */
    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока природными событиями
     */
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие роста блока
     */
    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие воспламенения блока
     */
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие поставки двойного блока (кровать и т.д.)
     */
    @EventHandler
    public void onBlockMultiPlace(BlockMultiPlaceEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие проверки физики блока
     */
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие расширения поршня
     */
    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие втягивания поршня
     */
    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения тока redstone
     */
    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setNewCurrent(0);
        }
    }

    /**
     * Событие распространения блока на основе мировых условий
     */
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока сущностями
     */
    @EventHandler
    public void onEntityBlockForm(EntityBlockFormEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие выпадения листьев
     */
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие изменения текста в табличке игроком
     */
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.WAITING) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWars.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
