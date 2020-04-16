package Users;

import Teams.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public  class CoachPersonalPage extends PersonalPage {

    private String teamRole;


    public CoachPersonalPage(String name, LocalDate birthdate, String teamRole, Team team){
        this.name=name;
        this.age=getCurrentAge(birthdate);
        this.teamRole=teamRole;
        this.currentTeam=team;
//        this.teamHistory.add(team.getName());

    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

}
