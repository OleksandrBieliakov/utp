package ass5.services;

import ass5.Request;
import ass5.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

abstract public class Service implements Runnable {

    static final List<String> WRONG_ARGS = new ArrayList<>(Collections.singletonList("WRONG ARGS"));

    private PriorityQueue<Request> requests;
    private PriorityQueue<Response> responses;

    private String name;

    Service(PriorityQueue<Request> requests, PriorityQueue<Response> responses, String name) {
        this.requests = requests;
        this.responses = responses;
        this.name = name;
    }

    @Override
    public final void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Request request = null;
            if (!requests.isEmpty()) request = requests.poll();
            System.out.println("REQUEST POLLED: by " + name + " " + request);
            if (request != null) {
                Response response = fulfilRequest(request);
                responses.add(response);
                System.out.println("RESPONSE ADDED: by " + name + " " + response);
            }
        }
    }

    abstract public Response fulfilRequest(Request request);

}
