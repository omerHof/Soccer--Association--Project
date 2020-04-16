package SystemLogic;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Search {

    public enum Category {
        teams, players, referees, leagues ;
    }

    private String toSearch;
    private Category category;

    public Search(String toSearch, Category category) {
        this.toSearch = toSearch;
        this.category = category;
    }

    public Search(String toSearch) {
        this.toSearch = toSearch;
    }


    public List<Object> search (String toSearch, Category category){

        List<Object> toReturn = new LinkedList<>();

        if (category!= null)
            searchByCategory(toSearch, category);
        else
            searchAll(toSearch);

    return toReturn;
    }

    public List<Object> searchByCategory (String toSearch, Category category){

    return null;
    }

    public List<Object> searchAll (String toSearch){

    return null;
    }

}
