package Users;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import SystemLogic.Notification;
import Teams.Team;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.acl.Owner;
import java.util.*;

public class Administrator extends User {

    public Administrator(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.userFullName = fullName;
    }


    /*


    1.	הסרת קבוצה לצמיתות –
a.	הפונקציה מקבלת כקלט את שם הקבוצה
b.	שולפת את הקבוצה מהDB
c.	מוודא שאין לה משחקים פתוחים/ משחקים שעוד לא התקיימו.
d.	במידה וכך, משנה את הסטטאוס שלה ל**"לא פעילה לצמיתות".
e.	**הסבר לגבי לא פעילה לצמיתות – לא יהיה ניתן לשייך אותה לעונות לעולם.
f.	שולף את בעלי הקבוצה ואת מנהלי הקבוצה.
g.	שולח להם התראה (?).


     */

    public void closeTeamForPermanent(String name) {
        DB db = DB.getInstance();
        Team team = db.getTeam(name);
        boolean hasMoreGames = false;
        if (team != null) {
            ArrayList<Game> gameList = team.getGameList();
            if(gameList!=null) {
                for (Game g : gameList) {
                    if ((g.getStatus() == Game.gameStatus.active) || (g.getStatus() == Game.gameStatus.preGame)) {
                        hasMoreGames = true;
                    }
                }
            }
            if (hasMoreGames == false) {
                team.setStatus(Team.teamStatus.PermanentlyClosed);
                MainSystem.LOG.info("the team " +name+" is permanently closed by the administrator");

                HashMap<String, TeamOwner> owners = team.getTeamOwners();
                HashMap<String, Manager> managers = team.getManagers();


                //send notification to all owners
                Iterator it = owners.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    TeamOwner owner = (TeamOwner) pair.getValue();
                    Notification notification1 = new Notification(this, "the team "+name+" is permanently closed", owner);
                    notification1.send();
                    MainSystem.LOG.info("notification sent to the team owner "+owner.getUserFullName());

                    it.remove();
                }

                //send notification to all managers
                Iterator it2 = managers.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry pair = (Map.Entry) it2.next();
                    Manager manager = (Manager) pair.getValue();
                    Notification notification2 = new Notification(this, "the team "+name+" is permanently closed", manager);
                    notification2.send();
                    MainSystem.LOG.info("notification sent to the manager "+manager.getUserFullName());

                    it2.remove();
                }
            } else {
                System.out.println("the team cant be closed for permanent because it has open games");
            }
        }
    }


    public void deleteUserFromSystem(String name) {
        DB db = DB.getInstance();
        User user = db.getUserByFullName(name);
        if (user != null) {
            if (user instanceof AssociationRepresentative) {
                if (db.checkQuantityOfUsersByType("AssociationRepresentative") >= 2) {
                    AssociationRepresentative associationRep = (AssociationRepresentative) user;

                    if (associationRep.findActiveGame() != null) {//not sure ask tali
                        associationRep.passMyGames();//// not sure, ask tali
                    }
                    db.removeUser(user.getUserName());
                    MainSystem.LOG.info("the user of Association representative " +name+" is deleted by the administrator");

                }
                else{
                    System.out.println("no delete! the system has less then 2 Association representatives");
                }
            } else if (user instanceof Administrator) {
                if (db.checkQuantityOfUsersByType("Administrator") >= 2) {
                    db.removeUser(user.getUserName());
                    MainSystem.LOG.info("the user of administarator " +name+" is deleted by the administrator");

                }
                else{
                    System.out.println("no delete! the system has less then 2 administrator");
                }
            } else if (user instanceof Fan) {
                Fan fan = (Fan) user;
                fan.stopFollowAllTeams();
                fan.stopFollowAllPages();
                //2.	נדרש להסיר את המעקב שלו מכל המשחקים (לדעתי יקרה אוטומטי כאשר יוסר המעקב מקבוצה ?)
                ///do the delete
                db.removeUser(user.getUserName());
            } else if (user instanceof TeamOwner) {
                if (db.checkQuantityOfUsersByType("TeamOwner") >= 2) {
                    TeamOwner owner = (TeamOwner) user;
                    owner.removeAppointmentTeamOwner(owner);              //ask katzi

                    ///do the delete
                    db.removeUser(user.getUserName());
                }

            }


            else if(user instanceof Referee) {
                Referee ref = (Referee) user;
                LinkedList<Game> games = ref.getMyGames();
                boolean isActive = false;
                for (Game g : games) {
                    if ((g.getStatus() == Game.gameStatus.active) || (g.getStatus() == Game.gameStatus.preGame)) {
                        isActive = true;
                        break;
                    }
                }
                    if(isActive==false){
                        db.removeUser(user.getUserName());
                    }
            }


            else if (user instanceof Player) {
                Player player = (Player) user;
                PersonalPage page = player.getPage();
                if (page != null) {
                    Team team = page.getCurrentTeam();
                    if (team != null) {
                        ArrayList<Game> gamesList = team.getGameList();
                        boolean isActive = false;
                        for (Game game : gamesList) {
                            if ((game.getStatus() == Game.gameStatus.active) || (game.getStatus() == Game.gameStatus.preGame)) {
                                isActive = true;
                                break;
                            }
                        }
                        if (isActive == false) {
                            team.removePlayer(player);
                            db.removeUser(user.getUserName());
                        }
                    }
                }
            }

            else if (user instanceof Coach){
                Coach coach = (Coach) user;
                PersonalPage page = coach.getPage();
                if (page != null) {
                    Team team = page.getCurrentTeam();
                    if (team != null) {
                        ArrayList<Game> gamesList = team.getGameList();
                        boolean isActive = false;
                        for (Game game : gamesList) {
                            if ((game.getStatus() == Game.gameStatus.active) || (game.getStatus() == Game.gameStatus.preGame)) {
                                isActive = true;
                                break;
                            }
                        }
                        if (isActive == false) {
                            team.removeCoach(coach);
                            db.removeUser(user.getUserName());
                        }
                    }
                }
            }
        }
    }


    ////need to do
    public String watchLog(){
        String logString ="";


        try{
            FileInputStream fstream = new FileInputStream("System.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null)   {
                /* parse strLine to obtain what you want */
                logString =logString +"\n"+ strLine;
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return logString;
    }


}
