package ass5.messages;

import ass5.participants.Service;

public class Response extends Message {

    private int result;
    private Service service;

    public Response(Request request, int result, Service service) {
        super(request.getRequester(), request.getPriority());
        this.result = result;
        this.service = service;
    }

    public int getResult() {
        return result;
    }

    public String toString() {
        return super.toString() + " done by " + service;
    }

}
