package SystemLogic;

import Users.User;

public class Notification {

    public enum notificationStatus  {
        notSent, sent, read
    }

    private User sender;
    private String context;
    private User receiver;
    private notificationStatus status;

    /**
     * Contructor
     * @param sender
     * @param context
     * @param receiver
     */
    public Notification(User sender, String context, User receiver) {
        this.sender = sender;
        this.context = context;
        this.receiver = receiver;
        this.status = notificationStatus.notSent;
    }

    /**
     * This method send the notification from the sender to the receiver
     */
    public void send(){
        if(status == notificationStatus.notSent) {
            sender.getSentNotifications().add(this);
            receiver.getReceivedNotifications().add(this);
            status = notificationStatus.sent;
        }
    }

    /**
     *
     * @return the notification's context
     */
    public String read(){
        status = notificationStatus.read;
        return this.context;
    }

    public User getSender() {
        return sender;
    }

    /** ----------------- GETTERS AND SETTERS ----------------- **/
    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public notificationStatus getStatus() {
        return status;
    }

    public void setStatus(notificationStatus status) {
        this.status = status;
    }
}
