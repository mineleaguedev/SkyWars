package net.abdymazhit.skywars;

import net.abdymazhit.skywars.customs.Island;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Менеджер рейтинга
 */
public class RatingManager {

    /** Список мест островов (1-й элемент = последнее место) */
    private final List<Island> islandsPlaces;

    /**
     * Инициализирует менеджер рейтинга
     */
    public RatingManager() {
        islandsPlaces = new ArrayList<>();
    }

    /**
     * Добавляет место острову
     * @param island Остров
     */
    public void addIslandPlace(Island island) {
        islandsPlaces.add(island);
    }

    /**
     * Подвести итог игры и установить рейтинги
     */
    public void setRatings() {
        // Вычисляем средний рейтинг
        int totalRating = 0;
        int teamsCount = 0;

        for(Island island : islandsPlaces) {
            int totalTeamRating = 0;
            int teamPlayersCount = 0;

            for(Player player : island.getPlayers()) {
                totalTeamRating += SkyWars.getGameManager().getPlayerInfo(player).getStats().getRating();
                teamPlayersCount++;
            }

            totalRating += totalTeamRating / teamPlayersCount;
            teamsCount++;
        }

        int averageRating = totalRating / teamsCount;

        // Получаем первые две цифры
        int firstTwoNumbers = averageRating / 100;

        // Изначальное исчисляемое
        int initialCountable = 20;

        // Разница первоначального исчисляемого
        int difference = Math.abs(initialCountable - firstTwoNumbers);

        // Первоначальное
        int initial = 20;

        // Вычисляем рейтинг после разницы
        int winnersRating = initial + difference;
        int losersRating = initial - difference;

        // Добавляем в рейтинг количество проигравших команд
        winnersRating += islandsPlaces.size() - 1;
        losersRating += islandsPlaces.size() - 1;

        // Проценты получаемого/теряемого рейтинга
        int[] percents = new int[] {
                35,
                30,
                20,
                15,
        };

        // Рейтинги победителей
        int[] winnersRatings = new int[islandsPlaces.size()];
        for(int i = 0; i < (islandsPlaces.size() / 2) - 1; i++) {
            winnersRatings[i] = (winnersRating / 100) * percents[i];
        }

        // Рейтинги проигравших
        int[] losersRatings = new int[islandsPlaces.size()];
        for(int i = 0; i < (islandsPlaces.size() / 2) - 1; i++) {
            losersRatings[i] = (losersRating / 100) * percents[i];
        }

        // Выдать рейтинг проигравшим
        for(int i = 0; i < (islandsPlaces.size() / 2) - 1; i++) {
            Island island = islandsPlaces.get(i);
            for(Player player : island.getPlayers()) {
                player.sendMessage("Вы потеряли " + losersRatings[i] + " рейтинга!");
            }
        }

        // Выдать рейтинг выигравшим
        Collections.reverse(islandsPlaces);
        for(int i = 0; i < (islandsPlaces.size() / 2) - 1; i++) {
            Island island = islandsPlaces.get(i);
            for(Player player : island.getPlayers()) {
                player.sendMessage("Вы получили " + winnersRatings[i] + " рейтинга!");
            }
        }
    }
}
