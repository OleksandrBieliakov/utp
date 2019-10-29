package ass5.requesters;

import ass5.Priority;
import ass5.Request;
import ass5.RequestType;
import ass5.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Requester implements Runnable {

    private PriorityQueue<Request> requests;
    private PriorityQueue<Response> responses;

    private String name;

    private static int count = 0;
    private static int priority = 0;

    private int countSent = 0;
    private int countReceived = 0;

    public Requester(PriorityQueue<Request> requests, PriorityQueue<Response> responses, String name) {
        this.requests = requests;
        this.responses = responses;
        this.name = name;
    }

    public String name() {
        return name;
    }

    private List<String> args() {
        return new ArrayList<>(Arrays.asList(count++ + "", count++ + ""));
    }

    private Priority priority() {
        Priority[] priorities = Priority.values();
        if (priority >= 3) priority = 0;
        return priorities[priority++];
    }

    @Override
    public void run() {
        while (true) {
            Request request = new Request(this, RequestType.SUM, args(), priority());
            requests.add(request);
            System.out.println("REQUEST ADDED: " + request);
            countSent++;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Response response = responses.peek();
            if (response != null && response.requester().equals(this)) {
                System.out.println("RESPONSE POLLED: by " + name + " " + responses.poll() + " (waiting: " + (countSent - ++countReceived) + ")");
            }
        }
    }

}
