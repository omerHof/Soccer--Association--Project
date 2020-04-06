package Users;

import Teams.Team;

import java.util.ArrayList;
import java.util.HashSet;

public  class CoachPersonalPage extends PersonalPage {
    private String name;
    private String qualification;
    private String teamRole;
    private Team currentTeam;
    private ArrayList<Team> teamHistory;
    private ArrayList<User>followers;


    public CoachPersonalPage(Coach coach){
        name=coach.getFullName();
       teamRole=coach.getTeamRole();
       qualification=coach.getQualification();
        followers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public ArrayList<Team> getTeamHistory() {
        return teamHistory;
    }
    public void addTeamToHistoryCareer(Team team){
        teamHistory.add(team);
    }

    public void addFollower(User user){
        followers.add(user);
    }
    public void removeFollower(User user){
        followers.remove(user);
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }
}
