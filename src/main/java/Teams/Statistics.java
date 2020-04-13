package Teams;

public class Statistics {

    private int score;
    private int wins;
    private int loses;
    private int tie;
    private int gs;
    private int gc;

    public Statistics() {
        setNewSeasonStatistics();
    }

    private void setNewSeasonStatistics() {
        this.score = 0;
        this.wins = 0;
        this.loses = 0;
        this.tie = 0;
        this.gs= 0;
        this.gc = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins++;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses() {
        this.loses++;
    }

    public int getGoals() {
        return gc;
    }

    public void setGoals(int goals) {
        this.gc += goals;
    }

    public int getTie() {
        return tie;
    }

    public void setTie() {
        this.tie++;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc += gc;
    }
}
