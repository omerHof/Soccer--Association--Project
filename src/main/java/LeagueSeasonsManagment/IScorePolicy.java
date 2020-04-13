package LeagueSeasonsManagment;

import java.util.ArrayList;

public interface IScorePolicy {
    void scorePolicyAlgoImplementation();
    String getName();
    int getWin();
    int getDraw();
    int getLost();
    boolean isGoalDiff();

}
