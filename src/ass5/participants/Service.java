package ass5.participants;

import ass5.MessagesQueue;
import ass5.messages.Request;
import ass5.messages.Response;

public class Service extends Participant {

    private static int nextServiceID = 0;

    private int serviceID;

    public Service(MessagesQueue queue) {
        super(queue);
        serviceID = nextServiceID++;
    }

    @Override
    public void run() {
        while (true) {
            Request request = queue.pollRequest();
            if (request != null) queue.addMessage(calculate(request));
            sleep();
        }
    }

    private Response calculate(Request request) {
        int result = request.getVal1() + request.getVal2();
        return new Response(request, result, this);
    }

    @Override
    public String toString() {
        return "Service" + serviceID;
    }

}
