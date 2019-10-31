package ass5;

import ass5.messages.Message;
import ass5.messages.Request;
import ass5.messages.Response;
import ass5.participants.Requester;

import java.util.PriorityQueue;

public class MessagesQueue {

    private PriorityQueue<Message> messages = new PriorityQueue<>();

    public synchronized Request pollRequest() {
        if (!(messages.peek() instanceof Request)) return null;
        return (Request) messages.poll();
    }

    public synchronized Response pollResponse(Requester requester) {
        Message message = messages.peek();
        if (!(message instanceof Response) || !message.getRequester().equals(requester)) return null;
        messages.remove();
        return (Response) message;
    }

    public synchronized void addMessage(Message message) {
        if (message != null) messages.add(message);
    }

}
