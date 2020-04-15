package Users;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Assent;
import Teams.Team;
import UserGenerator.ManagmentUserGenerator;
import UserGenerator.PremiumUserGenertator;

import java.util.HashMap;

public class TeamOwner extends User implements Assent {
    private Team team = null;
    private double worth;
    private boolean permission;
    private HashMap<String, TeamOwner> team_owners_appointments = new HashMap<>();
    private HashMap<String, Manager> managers_appointments = new HashMap<>();
    //private HashMap<String, Boolean> authorizations = new HashMap<>();

    public TeamOwner(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    public void askPermission (){//todo: necessary?
        DB db = DB.getInstance();
        AssociationRepresentative ar = (AssociationRepresentative) db.getUserType("AssociationRepresentative");
        this.permission = ar.approveRegistration("who", "cares");
    }

    public void openTeam(String team_name){//after he got permission
        if(permission) {
            HashMap<String , TeamOwner> me = new HashMap<>();
            me.put(super.getUserName(), this);
            team = new Team(team_name, me);
            DB.getInstance().addTeam(team);
        }
    }

    public String addAssent(Assent assent, double new_assent){
        if(assent == null){
            return "null";
        }

        if(this.team.containsAssent(assent)){
            return "already added";
        }

        else{
            if (assent.getWorth()==0) {         //means: first time the assent is added to team
                assent.setWorth(new_assent);
            }
            this.team.addAssent(assent);
            buyAssent(assent.getWorth());
        }
        return "added successfully";
    }

    public  String removeAssent(Assent assent){
        if(assent == null){
            return "null";
        }
        if(!this.team.containsAssent(assent)){
            return "not in the team";
        }
        else{
            this.team.removeAssent(assent);
            sellAssent(assent.getWorth());
        }
        return "removed successfully";
    }

    public String changeAssentWorth(Assent assent, double new_worth){
        if(assent == null){
            return "null";
        }
        if(!this.team.containsAssent(assent)){
            return "not in the team";
        }
        else{
            assent.setWorth(new_worth);
        }
        return "changed successfully";
    }

    public String appointTeamOwner(User user, double worth){
        if(user == null){
            return "null";
        }
        if(user instanceof TeamOwner && ((TeamOwner)user).getTeam()!=null){
            return "already has team";
        }
        PremiumUserGenertator premiumUserGenertator = new PremiumUserGenertator();
        DB db = DB.getInstance();
        db.removeUser(user.getUserName());
        TeamOwner teamOwner = (TeamOwner) premiumUserGenertator.generate(user.getUserName(),user.getPassword(),""
                ,"", user.getUserFullName(), user.getUserEmail(), null,"","","");
        db.addUser(teamOwner);
        addAssent(teamOwner, worth);
        team_owners_appointments.put(teamOwner.getUserName(), teamOwner);
        MainSystem.LOG.info(this.userName + " appointed " + teamOwner.getUserName() + " to " + this.team.getName() + "'s owner");
        return "appointed";
    }

    public String appointManager(Manager manager){
        if(manager == null){
            return "null";
        }
        managers_appointments.put(manager.getUserName(), manager);
        MainSystem.LOG.info(this.userName + " appointed " + manager.getUserName() + " to " + this.team.getName() + "'s manager");
        return "appointed";
    }

    public void buyAssent(double price){
        //todo: add sanctions
        double oldBudget = this.team.getBudget();
        double newBudget = oldBudget - price;
        this.team.setBudget(newBudget);
        MainSystem.LOG.info("The team budget decreased from " + oldBudget + " to " + newBudget);
    }

    public void sellAssent(double price){
        double oldBudget = this.team.getBudget();
        double newBudget = oldBudget + price;
        this.team.setBudget(newBudget);
        MainSystem.LOG.info("The team budget increased from " + oldBudget + " to " + newBudget);
    }

    public void closeTeam(){

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }


    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
