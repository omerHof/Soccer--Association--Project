package Users;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void closeTeamForPermanent(String name){
        DB db = DB.getInstance();
        Team team = db.getTeam(name);
        boolean hasMoreGames = false;
        if(team!=null){
            ArrayList<Game> gameList = team.getGameList();
            for(Game g: gameList){
                if((g.getStatus()== Game.gameStatus.active)||(g.getStatus()== Game.gameStatus.preGame)){
                    hasMoreGames=true;
                }
            }
            if(hasMoreGames==false) {
                team.setStatus(Team.teamStatus.PermanentlyClosed);
                HashMap<String, TeamOwner> owners = team.getTeamOwners();
                HashMap<String, Manager> managers = team.getManagers();
                ///send notification to owner and managers
            }
         }
    }


    /*
    2.	הסרת משתמש מהמערכת –
a.	מקבל כקלט מחרוזת עם השם המלא של המשתמש.
b.	שולף את המשתמש מהDB.
c.	בודק מה תפקיד המשתמש. מבצע switch case –
i.	במידה ומדובר במשתמש מסוג נציג התאחדות:
1.	נדרש לוודא כי ישנם לפחות 2 משתמשים מסוג זה במערכת.
2.	במידה ויש לו משחקים פתוחים – להעביר אותם לנציג אחר (פונקציה של נציג התאחדות).
ii.	במידה ומדובר במנהל מערכת –
1.	נדרש לוודא כי ישנם לפחות 2 משתמשים מסוג זה במערכת לפני המחיקה.
iii.	במידה ומדובר במשתמש מסוג אוהד –
1.	נדרש להסיר את המעקב שלו מכל העמודים הרלוונטים (קבוצה, שחקן, מאמן).
2.	נדרש להסיר את המעקב שלו מכל המשחקים (לדעתי יקרה אוטומטי כאשר יוסר המעקב מקבוצה ?)
iv.	במידה ומדובר במשתמש מסוג בעל קבוצה–
1.	נדרש לוודא שלקבוצה ישנם לפחות 2 בעלים.
2.	הפעלת מחיקת בעל קבוצה (קיימת פונקציה שכזאת בבעל קבוצה).
v.	במידה ומדובר במשתמש אחר – (שופט, מאמן, שחקן)
1.	לוודא שלא משובץ למשחק פתוח.
vi.	שים לב שלא קיים כאן משתמש מסוג מנהל קבוצה – תשאל את כצי אם יש אילוץ מיוחד לפני מחיקתו.
d.	במידה והתנאים מתקיימים תבוצע המחיקה (חלק מהמחיקות מתבצעות במחלקות עצמן וחלק יצטרכו להיות מבוצעות כאן תלוי בסוג המשתמש)
e.	להסיר מהDB
f.	לכתוב בקובץ הלוג.

     */

    public void deleteUserFromSystem(String name){
        DB db = DB.getInstance();
        User user = db.getUserByFullName(name);
        if(user!=null){
            if(user instanceof AssociationRepresentative){
                if(db.checkQuantityOfUsersByType("AssociationRepresentative")>=2){
                    //2.	במידה ויש לו משחקים פתוחים – להעביר אותם לנציג אחר (פונקציה של נציג התאחדות).
                    ///do the delete
                    db.removeUser(user.getUserName());
                }

                /*
                AssociationRepresentative associationRep = (AssociationRepresentative)user;
                associationRep.set

                 */

            }
            else if(user instanceof Administrator){
                if(db.checkQuantityOfUsersByType("Administrator")>=2){
                    db.removeUser(user.getUserName());

                    ///do the delete
                }
            }

            else if(user instanceof Fan){
                Fan fan = (Fan)user;
                fan.stopFollowAllTeams();
                fan.stopFollowAllPages();


                //2.	נדרש להסיר את המעקב שלו מכל המשחקים (לדעתי יקרה אוטומטי כאשר יוסר המעקב מקבוצה ?)
                ///do the delete
                db.removeUser(user.getUserName());
            }

           else if(user instanceof TeamOwner){
                if(db.checkQuantityOfUsersByType("TeamOwner")>=2){
                    TeamOwner owner = (TeamOwner)user;
                    //2.	הפעלת מחיקת בעל קבוצה (קיימת פונקציה שכזאת בבעל קבוצה).
                    ///do the delete
                    db.removeUser(user.getUserName());

                }

            }

            else{
                /*
                v.	במידה ומדובר במשתמש אחר – (שופט, מאמן, שחקן)
1.	לוודא שלא משובץ למשחק פתוח.
vi.	שים לב שלא קיים כאן משתמש מסוג מנהל קבוצה – תשאל את כצי אם יש אילוץ מיוחד לפני מחיקתו

                 */

            }

        }

    }


}
