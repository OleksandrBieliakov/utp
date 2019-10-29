package ass5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.PriorityQueue;

public class QueuesTest {

    private static final PriorityQueue<Request> REQUESTS = new PriorityQueue<>();
    private static final PriorityQueue<Response> RESPONSES = new PriorityQueue<>();

    private static final Request HIGH_REQ_1 = new Request(Priority.HIGH);
    private static final Request HIGH_REQ_2 = new Request(Priority.HIGH);
    private static final Request NORM_REQ_1 = new Request(Priority.NORMAL);
    private static final Request NORM_REQ_2 = new Request(Priority.NORMAL);
    private static final Request LOW_REQ_1 = new Request(Priority.LOW);
    private static final Request LOW_REQ_2 = new Request(Priority.LOW);

    private static final Response HIGH_RES_1 = new Response(HIGH_REQ_1);
    private static final Response HIGH_RES_2 = new Response(HIGH_REQ_2);
    private static final Response NORM_RES_1 = new Response(NORM_REQ_1);
    private static final Response NORM_RES_2 = new Response(NORM_REQ_2);
    private static final Response LOW_RES_1 = new Response(LOW_REQ_1);
    private static final Response LOW_RES_2 = new Response(LOW_REQ_2);


    @Test
    public void requestsQueueTest() {
        REQUESTS.add(LOW_REQ_1);
        REQUESTS.add(NORM_REQ_1);
        REQUESTS.add(HIGH_REQ_1);
        REQUESTS.add(LOW_REQ_2);
        REQUESTS.add(NORM_REQ_2);
        REQUESTS.add(HIGH_REQ_2);
        Assert.assertEquals(6, REQUESTS.size());

        Assert.assertEquals(Priority.HIGH, Objects.requireNonNull(REQUESTS.poll()).priority());
        Assert.assertEquals(Priority.HIGH, Objects.requireNonNull(REQUESTS.poll()).priority());
        Assert.assertEquals(Priority.NORMAL, Objects.requireNonNull(REQUESTS.poll()).priority());
        Assert.assertEquals(Priority.NORMAL, Objects.requireNonNull(REQUESTS.poll()).priority());
        Assert.assertEquals(Priority.LOW, Objects.requireNonNull(REQUESTS.poll()).priority());
        Assert.assertEquals(Priority.LOW, Objects.requireNonNull(REQUESTS.poll()).priority());
    }

    @Test
    public void responsesQueueTest() {
        RESPONSES.add(LOW_RES_1);
        RESPONSES.add(NORM_RES_1);
        RESPONSES.add(HIGH_RES_1);
        RESPONSES.add(LOW_RES_2);
        RESPONSES.add(NORM_RES_2);
        RESPONSES.add(HIGH_RES_2);
        Assert.assertEquals(6, RESPONSES.size());

        Assert.assertEquals(Priority.HIGH, Objects.requireNonNull(RESPONSES.poll()).priority());
        Assert.assertEquals(Priority.HIGH, Objects.requireNonNull(RESPONSES.poll()).priority());
        Assert.assertEquals(Priority.NORMAL, Objects.requireNonNull(RESPONSES.poll()).priority());
        Assert.assertEquals(Priority.NORMAL, Objects.requireNonNull(RESPONSES.poll()).priority());
        Assert.assertEquals(Priority.LOW, Objects.requireNonNull(RESPONSES.poll()).priority());
        Assert.assertEquals(Priority.LOW, Objects.requireNonNull(RESPONSES.poll()).priority());
    }

}
