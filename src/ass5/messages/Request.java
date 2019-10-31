package ass5.messages;

import ass5.participants.Requester;

public class Request extends Message {

    private int val1;
    private int val2;

    public Request(Requester requester, Priority priority, int val1, int val2) {
        super(requester, priority);
        this.val1 = val1;
        this.val2 = val2;
    }

    public int getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

}
