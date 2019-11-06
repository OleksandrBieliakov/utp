package ass5.messages;

import ass5.participants.Requester;

import java.time.LocalDateTime;

abstract public class Message implements Comparable<Message> {

    private static int nextID = 0;

    private int messageID;
    private LocalDateTime timeCreated;
    private Requester requester;
    private Priority priority;

    Message(Requester requester, Priority priority) {
        messageID = nextID++;
        timeCreated = LocalDateTime.now();
        this.requester = requester;
        this.priority = priority;
    }

    public Requester getRequester() {
        return requester;
    }

    Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Message other) {
        int result = priority.compareTo(other.priority);
        if (result == 0) result = timeCreated.compareTo(other.timeCreated);
        return result;
    }

    @Override
    public String toString() {
        return "Message" + messageID + " " + priority + " PRIORITY";
    }

}
