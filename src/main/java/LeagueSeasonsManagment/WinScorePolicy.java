package LeagueSeasonsManagment;

import java.util.ArrayList;

public class WinScorePolicy implements IScorePolicy {

    private int win;
    private int draw;
    private int lost;
    String name;

    public WinScorePolicy() {
        name="WinScorePolicy";
    }

    @Override
    public void scorePolicyAlgoImplementation() {
        win=2;
        draw=1;
        lost=0;


    }
    @Override
    public ArrayList<String> priority(String points,String moreGoals, String goalDiff){
        ArrayList<String> results= new ArrayList<>();
        results.add(points);
        results.add(goalDiff);
        results.add(moreGoals);
        return results;
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
