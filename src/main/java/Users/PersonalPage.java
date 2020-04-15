package Users;

import SystemLogic.DB;
import Teams.Team;

import java.util.*;

public abstract class PersonalPage extends Observable {
    protected String name;
    protected Team currentTeam;
    protected int age;
    protected ArrayList<Team> teamHistory;



    public String getName() {
        return name;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
        setChanged();
        notifyObservers(currentTeam);
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Team> getTeamHistory() {
        return teamHistory;
    }

    public void setTeamHistory(ArrayList<String>teamHistoryList) {

        DB db = DB.getInstance();
        for(String team: teamHistoryList){
            if(db.getTeam(team)!=null){
                Team t = db.getTeam(team);
                teamHistory.add(t);
            }
            else{
                Team t = new Team(team);
                db.addTeam(t);
                teamHistory.add(t);
            }
        }
    }


    public void setOneTeamToHistory(String team){
        DB db = DB.getInstance();

        Team t = db.getTeam(team);
        if(t!=null) {
            teamHistory.add(t);
        }
        else{
            t = new Team(team);
            db.addTeam(t);
            teamHistory.add(t);
        }
    }




    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }

        return age;
    }
}
