package Teams;

import LeagueSeasonsManagment.IScorePolicy;

public class Statistics implements Comparable {

    private int score;
    private int wins;
    private int loses;
    private int tie;
    private int gs;
    private int gc;
    private IScorePolicy policy;

    public Statistics(IScorePolicy policy) {
        setNewSeasonStatistics(policy);
    }

    public void setNewSeasonStatistics(IScorePolicy policy) {
        this.score = 0;
        this.wins = 0;
        this.loses = 0;
        this.tie = 0;
        this.gs = 0;
        this.gc = 0;
        this.policy = policy;

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
        return gs;
    }

    public void setGoals(int goals) {
        this.gs += goals;
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

    public int getDif() {
        return gs - gc;
    }

    @Override
    public int compareTo(Object obj) {
        Statistics other = (Statistics) obj;
        if (this.getScore() > other.getScore()) {
            return -1;
        }
        if (this.getScore() < other.getScore()) {
            return 1;
        }
        if (this.getScore() == other.getScore() && policy.isGoalDiff()) {
            if (this.getDif() > other.getDif()) {
                return -1;
            }
            if (this.getDif() < other.getDif()) {
                return 1;
            }
            if (this.getGoals() > other.getGoals()) {
                return -1;
            }
            if (this.getGoals() < other.getGoals()) {
                return 1;
            }
        }
        if (this.getScore() == other.getScore() && !policy.isGoalDiff()) {
            if (this.getGoals() > other.getGoals()) {
                return -1;
            }
            if (this.getGoals() < other.getGoals()) {
                return 1;
            }
            if (this.getDif() > other.getDif()) {
                return -1;
            }
            if (this.getDif() < other.getDif()) {
                return 1;
            }
        }
        return 0;
    }
}

