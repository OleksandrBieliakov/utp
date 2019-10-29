package ass5;

import ass5.requesters.Requester;
import ass5.services.Service;
import ass5.services.SumIntegersService;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        PriorityQueue<Request> requests = new PriorityQueue<>();
        PriorityQueue<Response> responses = new PriorityQueue<>();

        Requester requester1 = new Requester(requests, responses, "R1");
        Requester requester2 = new Requester(requests, responses, "R2");
        Requester requester3 = new Requester(requests, responses, "R3");
        Service adder1 = new SumIntegersService(requests, responses, "S1");
        Service adder2 = new SumIntegersService(requests, responses, "S2");


        Thread requesterThread1 = new Thread(requester1);
        Thread requesterThread2 = new Thread(requester2);
        Thread requesterThread3 = new Thread(requester3);
        Thread serviceThread1 = new Thread(adder1);
        Thread serviceThread2 = new Thread(adder2);

        requesterThread1.start();
        serviceThread1.start();
        requesterThread2.start();
        serviceThread2.start();
        requesterThread3.start();

    }

}
