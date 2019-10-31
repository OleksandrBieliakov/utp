package ass5;

import ass5.messages.Priority;
import ass5.messages.Request;
import ass5.participants.Requester;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

    private static MessagesQueue queue = new MessagesQueue();

    private static final Requester REQUESTER = new Requester(queue);

    private static Request REQUEST1;
    private static Request REQUEST2;
    private static Request REQUEST3;
    private static Request REQUEST4;
    private static Request REQUEST5;
    private static Request REQUEST6;

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queueTest() {
        REQUEST1 = new Request(REQUESTER, Priority.LOW, 1, 1);
        sleep();
        REQUEST2 = new Request(REQUESTER, Priority.NORMAL, 1, 1);
        sleep();
        REQUEST3 = new Request(REQUESTER, Priority.HIGH, 1, 1);
        sleep();
        REQUEST4 = new Request(REQUESTER, Priority.LOW, 1, 1);
        sleep();
        REQUEST5 = new Request(REQUESTER, Priority.NORMAL, 1, 1);
        sleep();
        REQUEST6 = new Request(REQUESTER, Priority.HIGH, 1, 1);

        queue.addMessage(REQUEST1);
        queue.addMessage(REQUEST2);
        queue.addMessage(REQUEST3);
        queue.addMessage(REQUEST4);
        queue.addMessage(REQUEST5);
        queue.addMessage(REQUEST6);

        Assert.assertEquals(REQUEST3, queue.pollRequest());
        Assert.assertEquals(REQUEST6, queue.pollRequest());
        Assert.assertEquals(REQUEST2, queue.pollRequest());
        Assert.assertEquals(REQUEST5, queue.pollRequest());
        Assert.assertEquals(REQUEST1, queue.pollRequest());
        Assert.assertEquals(REQUEST4, queue.pollRequest());
    }

}
