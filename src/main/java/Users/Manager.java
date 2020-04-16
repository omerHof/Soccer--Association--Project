package Users;

import Teams.Assent;
import Teams.Team;

public class Manager extends User implements Assent {
    private double worth;
    private Team team;

    public Manager(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    public String changePlayerRole(Player player, String new_role){
        player.setCourtRole(new_role);
        return "ok";
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
