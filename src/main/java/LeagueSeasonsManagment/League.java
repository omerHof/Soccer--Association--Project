package LeagueSeasonsManagment;

import java.util.LinkedList;
import java.util.List;

public class League {

    private String name;
    private int numOfTeams;
    private List<Season> allSeasons;

    private IScorePolicy iScorePolicy;

    public League(String name, int numOfTeams) {
        this.name = name;
        this.numOfTeams = numOfTeams;
        allSeasons = new LinkedList<>();
    }
}
