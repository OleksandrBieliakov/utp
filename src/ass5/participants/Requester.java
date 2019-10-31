package ass5.participants;

import ass5.MessagesQueue;
import ass5.messages.Priority;
import ass5.messages.Request;
import ass5.messages.Response;

import java.util.Random;

public class Requester extends Participant {

    private static int nextRequesterID = 0;

    private int requesterID;
    private Random random = new Random();
    private int val1;
    private int val2;

    public Requester(MessagesQueue queue) {
        super(queue);
        requesterID = nextRequesterID++;
    }

    @Override
    public void run() {
        while (true) {
            queue.addMessage(generateRequest());
            while (true) {
                sleep();
                if (consumeResponse()) break;
            }
        }
    }

    private Request generateRequest() {
        val1 = generateInt();
        val2 = generateInt();
        return new Request(this, Priority.generatePriority(), val1, val2);
    }

    private int generateInt() {
        return random.nextInt(50);
    }

    private boolean consumeResponse() {
        Response response = queue.pollResponse(this);
        if (response == null) return false;
        System.out.println(this.toString() + " " + response + " " + val1 + " + " + val2 + " = " + response.getResult());
        return true;
    }

    @Override
    public String toString() {
        return "Requester" + requesterID;
    }

}
