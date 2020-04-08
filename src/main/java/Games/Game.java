package Games;

import Teams.Team;
import Users.Referee;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Team homeTeam;
    private Team awayTeam;
    private List<Referee> gameReferees;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        gameReferees = new LinkedList<>();
    }
}
