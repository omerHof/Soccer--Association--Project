package LeagueSeasonsManagment;

import java.util.ArrayList;

public interface IScorePolicy {
    void scorePolicyAlgoImplementation();
    ArrayList<String> priority(String points,String moreGoals, String goalDiff);
    String getName();
    int getWin();
    int getDraw();
    int getLost();
}
