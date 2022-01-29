package net.abdymazhit.skywars.chests;

import org.bukkit.entity.Player;

/**
 * Отвечает за использование предмета
 */
@FunctionalInterface
public interface ItemUsage {

    /**
     * Событие использования предмета игроком
     * @param player Игрок
     */
    void use(Player player);
}
