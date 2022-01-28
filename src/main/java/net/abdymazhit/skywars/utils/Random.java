package net.abdymazhit.skywars.utils;

import org.bukkit.Material;

import java.util.List;
import java.util.SplittableRandom;

/**
 * Рандом
 */
public class Random {

    /** Объект рандома */
    public static SplittableRandom random = new SplittableRandom();

    /**
     * Получает случайный материал из списка
     * @param materials Материалы
     * @return Случайный материал
     */
    public static Material of(Material... materials) {
        return materials[random.nextInt(materials.length)];
    }

    /**
     * Получает случайный тип объект из списка
     * @param lists Список
     * @param <T> Тип объекта
     * @return Случайный тип объекта
     */
    @SafeVarargs
    public static <T> T of(List<T>... lists) {
        int var = 0;
        for (List<T> l : lists) {
            var += l.size();
        }
        var = random.nextInt(var);
        for (List<T> l : lists) {
            if (var < l.size()) {
                return l.get(var);
            }
            var -= l.size();
        }
        return null;
    }
}
