package Games;

import SystemLogic.DB;

public class testHelpTest {
    public static void main(String[] args) {
        DB db=DB.getInstance();
        HelpTest test= new HelpTest();
        db.getLeague("Champions league").getSeasonByYear(2020).getSeasonScoreBoard().showTable();

    }
}
