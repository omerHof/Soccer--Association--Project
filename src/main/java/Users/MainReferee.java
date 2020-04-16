package Users;

import Games.Event;
import Games.Game;
import SystemLogic.MainSystem;
import com.sun.javafx.collections.ArrayListenerHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class MainReferee extends Referee {

    public MainReferee(String userName, String password, String fullName, String userEmail, String qualification) {
        super(userName, password, fullName, userEmail, qualification);
    }

    private void deleteEvent(Event.eventType type, int time, String playerName) {

        Game gameToDeleteFrom = findCloseGame();
        ArrayList<Event> gameEventBook = gameToDeleteFrom.getEventBook();

        for (Event event : gameEventBook){
            if(event.getType().equals(type) && event.getEventTime()==time && event.getPlayerName().equals(playerName)) //the exact event we want to delete.
                gameEventBook.remove(event); //removes the event from original
        }

        gameToDeleteFrom.setEventBook(gameEventBook); // really necessary ???????
    }

    private void editEvent(Game g) {



    }

    private void addEvent(Event.eventType type, int time, String playerName) {

        Game gameToAddTo = findCloseGame();

        if (gameToAddTo != null) { //there is a close game he can still edit (within 5 hours).
            Event addEvent = new Event(type, time, playerName);

            MainSystem.LOG.info("A new event: " + type + " was added to game: " + gameToAddTo.getHomeTeam().getName() + "-" + gameToAddTo.getAwayTeam().getName() + ", " + gameToAddTo.getGameDate());
            gameToAddTo.addEvent(addEvent);
        }
    }

    private Game findCloseGame() {
        for (Game game : super.myGames)
            if(game.getStatus().equals(Game.gameStatus.close))
                return game;

        return null; //no active game at the moment.
    }
}