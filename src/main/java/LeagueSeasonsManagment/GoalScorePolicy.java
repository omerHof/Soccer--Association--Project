package LeagueSeasonsManagment;

import java.util.ArrayList;

public class GoalScorePolicy implements IScorePolicy {

    private int win;
    private int draw;
    private int lost;
    String name;
    private boolean goalDiff;



    public GoalScorePolicy() {
        name="GoalScorePolicy";
        goalDiff= false;
    }

    @Override
    public void scorePolicyAlgoImplementation() {
        win=2;
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
