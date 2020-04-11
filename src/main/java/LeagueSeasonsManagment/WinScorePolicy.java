package LeagueSeasonsManagment;

public class WinScorePolicy implements IScorePolicy {

    private int win;
    private int draw;
    private int lost;

    @Override
    public void scorePolicyAlgoImplementation() {
        win=2;
        draw=1;
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
