package LeagueSeasonsManagment;

public class SimpleScorePolicy implements IScorePolicy {

    String name;
    private int win;
    private int draw;
    private int lost;
    
    @Override
    public void scorePolicyAlgoImplementation() {
        win=3;
        draw=1;
        lost=0;
        name = "SimpleScorePolicy";

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
