package SystemLogic;

public class DB {

    private static DB db;

    /**
     * implement data structures
     */

    private DB() {
        //Implement constructor
    }

    public static DB getResultsInstance(){
        if(db == null){
            synchronized (DB.class) {
                if(db == null){
                    db = new DB();
                }
            }
        }
        return db;
    }

}
