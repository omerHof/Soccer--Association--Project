package Users;

import Games.Event;
import Games.Game;

import java.util.Scanner;

public class MainReferee extends Referee {

    public MainReferee(String userName, String password, String fullName, String qualification) {
        super(userName, password, fullName, qualification);
    }

    public void editGameEvents(Games.Game g) {

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        System.out.print("What do you want to do? /n for adding an event please press 1 /n" +
                "to edit an existing event please press 2 /n" +
                "to delete an event please press 3. /n" +
                "press 0 when you finish update.");
        String chose = sc.nextLine();              //reads string

        while (chose != "0") {
            switch (chose) {
                case ("1"): {//Register a player
                    addEvent(g);
                }
                case ("2"): {
                    editEvent(g);
                }
                case ("3"): {
                    deleteEvent(g);
                }
                default: {  //not legal.
                    System.out.println("unsupported choice input"); //// error alert ?/...
                }
            }
        }
    }

    private void deleteEvent(Game g) {

    }

    private void editEvent(Game g) {


    }

    private void addEvent(Game g) {

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        System.out.println("Please enter an event type: (1 - goal, 2 - offside, 3 - foul, 4 - redTicket, 5 - yellowTicket, 6 - injury, 7 - substitiotion)");
        Integer eventType = sc.nextInt();
        System.out.println("Please enter the event time:");
        Integer eventMinute = sc.nextInt();
        System.out.println("Please enter the player's name:");
        String eventPlayer = sc.nextLine();

        Event newEvent = new Event(eventMinute, null, eventPlayer);

        switch (eventType) {
            case (1): {
                newEvent.setType(Event.eventType.goal);
            }
            case (2): {
                newEvent.setType(Event.eventType.offside);
            }
            case (3): {
                newEvent.setType(Event.eventType.foul);
            }
            case (4): {
                newEvent.setType(Event.eventType.redTicket);
            }
            case (5): {
                newEvent.setType(Event.eventType.yellowTicket);
            }
            case (6): {
                newEvent.setType(Event.eventType.injury);
            }
            case (7): {
                newEvent.setType(Event.eventType.substitiotion);
            }
            default: {  //not legal.
                System.out.println("unsupported choice input"); //// error alert ?/...
            }
        }
    }

    public boolean approveRegistration (String fullName, String role){
        return true;
    }
}