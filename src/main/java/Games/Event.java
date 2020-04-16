package Games;

import Users.Player;
import Users.User;

public class Event {

    public enum eventType {
        goal, offside, foul, redTicket, yellowTicket, injury, substitiotion ;
    }

    private Integer eventTime;
    private eventType type;
    private String playerName; //or just a string??

    public Event(eventType type, int eventTime, String playerName) {
        if (eventTime >= 0 && eventTime <= 120 && playerName != "") {
            this.eventTime = eventTime;
            this.type = type;
            this.playerName = playerName;
        }
    }

    public int getEventTime() {
        return eventTime;
    }

    public eventType getType() {
        return type;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public void setType(eventType type) {
        this.type = type;
    }

    public void setPlayerName(String  playerName) {
        this.playerName = playerName;
    }

    public String evenToString(){
        String event=eventTime+type.name()+playerName;
        return event;
    }
}
