package LeagueSeasonsManagment;

import java.util.ArrayList;

public class SimpleScorePolicy implements IScorePolicy {

    String name;
    private int win;
    private int draw;
    private int lost;
    private boolean goalDiff;

    public SimpleScorePolicy() {
        name = "SimpleScorePolicy";
        goalDiff= true;
    }

    @Override
    public void scorePolicyAlgoImplementation() {
        win=3;
        draw=1;
        lost=0;

    }

    public boolean isGoalDiff() {
        return goalDiff;
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public String getName() {
        return name;
    }
}
