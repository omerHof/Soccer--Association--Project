package LeagueSeasonsManagment;

public class WinScorePolicy implements IScorePolicy {

    private int win;
    private int draw;
    private int lost;

    @Override
    public void scorePolicyAlgoImplementation() {
        win=1;
        draw=0;
        lost=0;

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
}
