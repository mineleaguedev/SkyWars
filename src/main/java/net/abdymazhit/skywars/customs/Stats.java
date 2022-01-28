package net.abdymazhit.skywars.customs;

/**
 * Статистика
 */
public class Stats {

    /** Общее количество побед */
    private final int wins;

    /** Общее количество игр */
    private final int games;

    /** Общее количество убийств */
    private final int kills;

    /** Общее количество смертей */
    private final int deaths;

    /** Текущее количество рейтинга */
    private final int rating;

    /** Максимальное количество рейтинга */
    private final int maxRating;

    /** Общее количество выпущенных стрел */
    private final int arrowsFired;

    /** Общее количество сломанных блоков */
    private final int blocksBroken;

    /** Общее количество поставленных блоков */
    private final int blocksPlaced;

    /** Текущая серия побед */
    private final int currentWinStreak;

    /** Максимальная серия побед */
    private final int winStreak;

    /**
     * Инициализирует статистику
     * @param wins Общее количество побед
     * @param games Общее количество игр
     * @param kills Общее количество убийств
     * @param deaths Общее количество смертей
     * @param rating Текущее количество рейтинга
     * @param maxRating Максимальное количество рейтинга
     * @param arrowsFired Общее количество выпущенных стрел
     * @param blocksBroken Общее количество сломанных блоков
     * @param blocksPlaced Общее количество поставленных блоков
     * @param currentWinStreak Текущая серия побед
     * @param winStreak Максимальная серия побед
     */
    public Stats(int wins, int games, int kills, int deaths, int rating, int maxRating, int arrowsFired, int blocksBroken, int blocksPlaced, int currentWinStreak, int winStreak) {
        this.wins = wins;
        this.games = games;
        this.kills = kills;
        this.deaths = deaths;
        this.rating = rating;
        this.maxRating = maxRating;
        this.arrowsFired = arrowsFired;
        this.blocksBroken = blocksBroken;
        this.blocksPlaced = blocksPlaced;
        this.currentWinStreak = currentWinStreak;
        this.winStreak = winStreak;
    }

    /**
     * Получает общее количество побед
     * @return Общее количество побед
     */
    public int getWins() {
        return wins;
    }

    /**
     * Получает общее количество игр
     * @return Общее количество игр
     */
    public int getGames() {
        return games;
    }

    /**
     * Получает общее количество убийств
     * @return Общее количество убийств
     */
    public int getKills() {
        return kills;
    }

    /**
     * Получает общее количество смертей
     * @return Общее количество смертей
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Получает текущее количество рейтинга
     * @return Текущее количество рейтинга
     */
    public int getRating() {
        return rating;
    }

    /**
     * Получает максимальное количество рейтинга
     * @return Максимальное количество рейтинга
     */
    public int getMaxRating() {
        return maxRating;
    }

    /**
     * Получает общее количество выпущенных стрел
     * @return Общее количество выпущенных стрел
     */
    public int getArrowsFired() {
        return arrowsFired;
    }

    /**
     * Получает общее количество сломанных блоков
     * @return Общее количество сломанных блоков
     */
    public int getBlocksBroken() {
        return blocksBroken;
    }

    /**
     * Получает общее количество поставленных блоков
     * @return Общее количество поставленных блоков
     */
    public int getBlocksPlaced() {
        return blocksPlaced;
    }

    /**
     * Получает текущую серию побед
     * @return Текущая серия побед
     */
    public int getCurrentWinStreak() {
        return currentWinStreak;
    }

    /**
     * Получает максимальную серию побед
     * @return Максимальная серия побед
     */
    public int getWinStreak() {
        return winStreak;
    }
}
