package ass5;

import ass5.participants.Requester;
import ass5.participants.Service;

public class Main {

    public static void main(String[] args) {

        MessagesQueue queue = new MessagesQueue();

        Requester requester1 = new Requester(queue);
        Requester requester2 = new Requester(queue);
        Requester requester3 = new Requester(queue);
        Service service1 = new Service(queue);
        Service service2 = new Service(queue);
        Service service3 = new Service(queue);

        Thread requesterThread1 = new Thread(requester1);
        Thread requesterThread2 = new Thread(requester2);
        Thread requesterThread3 = new Thread(requester3);
        Thread serviceThread1 = new Thread(service1);
        Thread serviceThread2 = new Thread(service2);
        Thread serviceThread3 = new Thread(service3);

        requesterThread1.start();
        requesterThread2.start();
        requesterThread3.start();
        serviceThread1.start();
        serviceThread2.start();
        serviceThread3.start();

    }

}
