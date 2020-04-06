package Games;

import Teams.Team;

public class Game {

    private Team homeTeam;
    private Team awayTeam;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
